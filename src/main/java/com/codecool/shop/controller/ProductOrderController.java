package com.codecool.shop.controller;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.ProductOrderDao;
import com.codecool.shop.dao.implementation.OrderDaoPSQL;
import com.codecool.shop.dao.implementation.ProductOrderDaoPSQL;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.ProductOrder;
import com.codecool.shop.utils.AuthGuard;
import com.codecool.shop.utils.RequestJSONProcessor;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/webshop/product-order")
public class ProductOrderController extends HttpServlet {

    private ProductOrderDao productOrderDao = new ProductOrderDaoPSQL();
    private OrderDao orderDao = new OrderDaoPSQL();
    private AuthGuard authGuard = new AuthGuard();
    private Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = authGuard.processToken(req, resp);
        if (userId == -1) return;
        String respJSON = RequestJSONProcessor.requestJsonProcessor(req);
        ProductOrder productOrder = gson.fromJson(respJSON, ProductOrder.class);
        int poId = productOrderDao.add(productOrder);
        resp.getWriter().print(poId);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = authGuard.processToken(req, resp);
        String respJSON = RequestJSONProcessor.requestJsonProcessor(req);
        ProductOrder productOrder = gson.fromJson(respJSON, ProductOrder.class);
        Order order = orderDao.findById(productOrder.getOrderId());
        if (userId == -1 || userId != order.getUserId()) return;
        productOrderDao.updateQuantity(productOrder.getId(), productOrder.getQuantity());
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = authGuard.processToken(req, resp);
        int orderId = Integer.valueOf(req.getParameter("id"));
        ProductOrder productOrder = productOrderDao.find(orderId);
        Order order = orderDao.findById(productOrder.getOrderId());
        if (userId == -1 || userId != order.getUserId()) return;
        productOrderDao.remove(orderId);
    }
}
