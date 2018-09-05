package com.codecool.shop.controller;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.SupplierDaoPSQL;
import com.codecool.shop.model.Supplier;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = {"/webshop/supplier"})
public class SupplierController extends HttpServlet {

    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SupplierDao supplierDataStore = new SupplierDaoPSQL();
        List<Supplier> suppliers = supplierDataStore.getAll();
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(suppliers));
    }

}