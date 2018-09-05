package com.codecool.shop.dao;

import com.codecool.shop.model.Order;

import java.util.List;

public interface OrderDao {

    Order findActive(int userId);
    Order findById(int id);
    int createOrder(int userId);
    void finalizeOrder(int id, String paymentId);
    void remove(int id);
    List<Order> getAllCompleted(int userId);

}
