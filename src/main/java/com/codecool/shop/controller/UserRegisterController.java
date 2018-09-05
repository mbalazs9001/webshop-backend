package com.codecool.shop.controller;


import com.codecool.shop.dao.UserDao;
import com.codecool.shop.dao.implementation.UserDaoPSQL;
import com.codecool.shop.model.PasswordStorage;
import com.codecool.shop.model.User;
import com.codecool.shop.utils.RequestJSONProcessor;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/webshop/register"})
public class UserRegisterController extends HttpServlet {

    private Gson gson = new Gson();
    private UserDao userDataStore = new UserDaoPSQL();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String input = RequestJSONProcessor.requestJsonProcessor(req);
        User newUser = gson.fromJson(input, User.class);
        newUser.setPassword(PasswordStorage.createHash(newUser.getPassword()));
        userDataStore.add(newUser);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String reply = gson.toJson(userDataStore.find(email) == null);
        resp.getWriter().print(reply);
    }
}
