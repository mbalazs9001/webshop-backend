package com.codecool.shop.model;

public class UserView {

    private int id;
    private String name;
    private String email;
    private String phone;
    private String token;

    public UserView(User user, String token) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.token = token;
    }
}
