package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ModelAssembler;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.dao.utils.QueryProcessor;
import com.codecool.shop.model.User;

public class UserDaoPSQL implements UserDao {

    ModelAssembler<User> assembler = rs -> new User(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getString("password"),
            rs.getString("email"),
            rs.getString("phone")
    );

    @Override
    public int add(User user) {
        return QueryProcessor.fetchOne(
                "INSERT INTO users (name, email, password, phone) VALUES (?, ?, ?, ?) RETURNING id;",
                rs -> rs.getInt("id"),
                user.getName(), user.getEmail(), user.getPassword(), user.getPhone()
        );
    }

    @Override
    public User find(int id) {
        return QueryProcessor.fetchOne("SELECT * FROM users WHERE id = ?::INTEGER;", assembler, String.valueOf(id));
    }

    @Override
    public User find(String email) {
        return QueryProcessor.fetchOne("SELECT * FROM users WHERE email = ?;", assembler, email);
    }

    @Override
    public void remove(int id) {
        QueryProcessor.executeUpdate("DELETE FROM users WHERE id = ?::INTEGER;", String.valueOf(id));
    }
}
