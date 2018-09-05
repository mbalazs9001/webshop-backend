package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductDaoPSQL;
import com.codecool.shop.model.Product;
import com.codecool.shop.utils.AuthGuard;
import com.codecool.shop.utils.RequestJSONProcessor;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/webshop/product")
public class ProductController extends HttpServlet {

    private Gson gson = new Gson();
    private AuthGuard authGuard = new AuthGuard();
    private ProductDao productDataStore = new ProductDaoPSQL();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productId = req.getParameter("product-id");
        if (productId != null) {
            PrintWriter out = resp.getWriter();
            Product product = productDataStore.find(Integer.valueOf(productId));
            out.println(gson.toJson(product));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (authGuard.processToken(req, resp) == -1) return;
        String input = RequestJSONProcessor.requestJsonProcessor(req);
        Product newProduct = gson.fromJson(input, Product.class);
        productDataStore.add(newProduct);
        System.out.println(newProduct);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productId = req.getParameter("product-id");
        if (authGuard.processToken(req, resp) == -1 || productId == null) return;
        productDataStore.remove(Integer.valueOf(productId));
        System.out.println("Product number " + productId + " was removed from the store.");
    }
}
