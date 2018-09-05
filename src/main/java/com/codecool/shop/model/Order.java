package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Order {

    private int id;
    private int userId;

    private String paymentId;
    private String status;
    private String date;

    private List<ProductOrder> productOrders = new ArrayList<>();

    public Order(int userId) {
        this.userId = userId;
    }

    public Order(int id, int userId) {
        this.id = id;
        this.userId = userId;
    }

    public Order(int id, int userId, List<ProductOrder> productOrders) {
        this.id = id;
        this.userId = userId;
        this.productOrders = productOrders;
    }

    public Order(int id, int userId, String paymentId, String status, String date) {
        this.id = id;
        this.userId = userId;
        this.paymentId = paymentId;
        this.status = status;
        this.date = date;
    }

    public Order(int id, int userId, String paymentId, String status, String date, List<ProductOrder> productOrders) {
        this.id = id;
        this.userId = userId;
        this.paymentId = paymentId;
        this.status = status;
        this.date = date;
        this.productOrders = productOrders;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public String getStatus() {
        return status;
    }

    public String getDate() {
        return date;
    }

    public List<ProductOrder> getProductOrders() {
        return Collections.unmodifiableList(productOrders);
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void addProductOrder(ProductOrder productOrder) {
        productOrders.add(productOrder);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", paymentId=" + paymentId +
                ", status='" + status + '\'' +
                ", date=" + date +
                ", productOrders=" + productOrders +
                '}';
    }
}
