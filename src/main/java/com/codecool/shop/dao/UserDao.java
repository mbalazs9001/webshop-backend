package com.codecool.shop.dao;

import com.codecool.shop.model.User;

public interface UserDao {

    int add(User user);
    User find(int id);
    User find(String email);
    void remove(int id);

}
