package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ModelAssembler;
import com.codecool.shop.dao.ProductOrderDao;
import com.codecool.shop.dao.utils.QueryProcessor;
import com.codecool.shop.model.ProductOrder;

import java.util.List;

public class ProductOrderDaoPSQL implements ProductOrderDao {

    private ModelAssembler<ProductOrder> assembler = rs -> new ProductOrder(
            rs.getInt("id"),
            rs.getInt("order_id"),
            rs.getInt("product_id"),
            rs.getInt("quantity"),
            new ProductDaoPSQL().find(rs.getInt("product_id"))
    );

    @Override
    public int add(ProductOrder productOrder) {
        return QueryProcessor.fetchOne(
                "INSERT INTO product_orders (order_id, product_id, quantity)" +
                        " VALUES (?::INTEGER, ?::INTEGER, 1) RETURNING id;",
                rs -> rs.getInt("id"),
                String.valueOf(productOrder.getOrderId()), String.valueOf(productOrder.getProductId())
        );
    }

    @Override
    public ProductOrder find(int id) {
        return QueryProcessor.fetchOne(
                "SELECT * FROM product_orders WHERE id = ?::INTEGER;",
                assembler, String.valueOf(id)
        );
    }

    @Override
    public void updateQuantity(int id, int quantity) {
        QueryProcessor.executeUpdate(
                "UPDATE product_orders SET quantity = ?::INTEGER WHERE id = ?::INTEGER;",
                String.valueOf(quantity), String.valueOf(id)
        );
    }

    @Override
    public void remove(int id) {
        QueryProcessor.executeUpdate("DELETE FROM product_orders WHERE id = ?::INTEGER;", String.valueOf(id));
    }

    @Override
    public List<ProductOrder> getByOrder(int orderId) {
        return QueryProcessor.fetchAll("SELECT * FROM product_orders WHERE order_id = ?::INTEGER;",
                assembler, String.valueOf(orderId));
    }
}
