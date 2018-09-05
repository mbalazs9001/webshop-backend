package com.codecool.shop.controller;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.implementation.OrderDaoPSQL;
import com.codecool.shop.model.Address;
import com.codecool.shop.model.Order;
import com.codecool.shop.utils.AuthGuard;
import com.codecool.shop.utils.OrderUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;


@WebServlet(urlPatterns = {"/webshop/order"})
public class OrderController extends HttpServlet {

    private Gson gson = new Gson();
    private AuthGuard authGuard = new AuthGuard();
    private OrderDao orderDaoDataStore = new OrderDaoPSQL();

    /*
     * Returns the active shopping cart of the user if there is one,
     * creates a new one otherwise and returns that.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = authGuard.processToken(req, resp);
        if (userId == -1) return;
        PrintWriter out = resp.getWriter();
        if (req.getParameter("get-all") == null) {
            Order order = orderDaoDataStore.findActive(userId);
            if (order == null) {
                order = new Order(orderDaoDataStore.createOrder(userId), userId);
            }
            out.println(gson.toJson(order));
        } else {
            out.println(gson.toJson(orderDaoDataStore.getAllCompleted(userId)));
        }
    }

    /*
     * Finalizes the order by adding the payment ID, then logs it and sends an email.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = authGuard.processToken(req, resp);
        if (userId == -1) return;
        int orderId = Integer.parseInt(req.getParameter("id"));
        String paymentId = req.getParameter("paymentId");

        orderDaoDataStore.finalizeOrder(orderId, paymentId);
        Order fullOrder = orderDaoDataStore.findById(orderId);
        OrderUtils.logOrder(fullOrder);
        OrderUtils.sendMail(fullOrder);
    }
}
