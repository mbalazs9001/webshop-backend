package com.codecool.shop.controller;

import com.codecool.shop.dao.UserDao;
import com.codecool.shop.dao.implementation.UserDaoPSQL;
import com.codecool.shop.model.PasswordStorage;
import com.codecool.shop.model.User;
import com.codecool.shop.model.UserView;
import com.codecool.shop.utils.AuthGuard;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = {"/webshop/login"})
public class UserLoginController extends HttpServlet {

    private Gson gson = new Gson();
    private AuthGuard authGuard = new AuthGuard();
    private UserDao userDataStore = new UserDaoPSQL();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");
        PrintWriter out = resp.getWriter();
        User user = userDataStore.find(email);

        if (user == null) {
            out.print(gson.toJson(null));
        } else {
            boolean passwordIsValid = PasswordStorage.verifyPassword(password, user.getPassword());
            if (passwordIsValid) {
                UserView userView = new UserView(user, authGuard.createToken(user));
                out.print(gson.toJson(userView));
            } else {
                out.print(gson.toJson(null));
            }
        }
    }
}