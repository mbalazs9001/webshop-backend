package com.codecool.shop.dao;

import com.codecool.shop.model.ProductOrder;

import java.util.List;

public interface ProductOrderDao {

    int add(ProductOrder productOrder);
    ProductOrder find(int id);
    void updateQuantity(int id, int quantity);
    void remove(int id);
    List<ProductOrder> getByOrder(int orderId);

}
