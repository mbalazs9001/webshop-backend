package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ModelAssembler;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.utils.QueryProcessor;
import com.codecool.shop.model.Order;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class OrderDaoPSQL implements OrderDao {

    ModelAssembler<Order> assembler = rs -> new Order(
            rs.getInt("id"),
            rs.getInt("user_id"),
            rs.getString("payment_id"),
            rs.getString("status"),
            rs.getString("date"),
            new ProductOrderDaoPSQL().getByOrder(rs.getInt("id"))
    );

    @Override
    public int createOrder(int userId) {
        if (findActive(userId) != null) {
            throw new IllegalStateException("WARNING: LIMIT REACHED. There is already an open order in the database!");
        }
        return QueryProcessor.fetchOne(
                "INSERT INTO orders (user_id) VALUES (?::INTEGER) RETURNING id;",
                rs -> rs.getInt("id"),
                String.valueOf(userId)
        );
    }

    @Override
    public Order findActive(int userId) {
        return QueryProcessor.fetchOne(
                "SELECT id, user_id FROM orders WHERE user_id = ?::INTEGER AND status = 'NEW';",
                rs -> {
                    return new Order(
                            rs.getInt("id"), rs.getInt("user_id"),
                            new ProductOrderDaoPSQL().getByOrder(rs.getInt("id"))
                    );
                },
                String.valueOf(userId)
        );
    }

    @Override
    public void remove(int id) {
        QueryProcessor.executeUpdate("DELETE FROM orders WHERE id = ?::INTEGER;", String.valueOf(id));
    }

    @Override
    public List<Order> getAllCompleted(int userId) {
        return QueryProcessor.fetchAll(
                "SELECT * FROM orders WHERE user_id = ?::INTEGER AND status != 'NEW';",
                assembler, String.valueOf(userId)
        );
    }

    @Override
    public void finalizeOrder(int id, String paymentId) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        QueryProcessor.executeUpdate(
                "UPDATE orders SET status = 'PAID', payment_id = ?, date = ? " +
                           "WHERE id = ?::INTEGER AND status = 'NEW';",
                paymentId, LocalDateTime.now().format(formatter), String.valueOf(id)
        );
    }

    @Override
    public Order findById(int id) {
        return QueryProcessor.fetchOne(
                "SELECT * FROM orders WHERE id = ?::INTEGER;",
                assembler, String.valueOf(id)
        );
    }
}
