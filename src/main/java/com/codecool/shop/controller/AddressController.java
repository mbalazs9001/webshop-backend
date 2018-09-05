package com.codecool.shop.controller;

import com.codecool.shop.dao.AddressDao;
import com.codecool.shop.dao.implementation.AddressDaoPSQL;
import com.codecool.shop.model.Address;
import com.codecool.shop.utils.AuthGuard;
import com.codecool.shop.utils.RequestJSONProcessor;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(urlPatterns = {"/webshop/address"})
public class AddressController extends HttpServlet {

    private Gson gson = new Gson();
    private AuthGuard authGuard = new AuthGuard();
    private AddressDao addressDataStore = new AddressDaoPSQL();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = authGuard.processToken(req, resp);
        if (userId == -1) return;
        PrintWriter out = resp.getWriter();
        out.println(gson.toJson(addressDataStore.find(userId)));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = authGuard.processToken(req, resp);
        if (userId == -1) return;
        PrintWriter out = resp.getWriter();
        String addressJson = RequestJSONProcessor.requestJsonProcessor(req);
        Address address = gson.fromJson(addressJson, Address.class);
        address.setUserId(userId);
        out.println(addressDataStore.add(address));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userId = authGuard.processToken(req, resp);
        String addressJson = RequestJSONProcessor.requestJsonProcessor(req);
        Address address = gson.fromJson(addressJson, Address.class);
        if (userId == -1 || userId != address.getUserId()) return;
        addressDataStore.modify(address);
    }
}
