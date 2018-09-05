package com.codecool.shop.model;

public class Address {

    private int id;
    private int userId;
    private String zipcode;
    private String country;
    private String city;
    private String street;

    public Address(int id, int userId, String zipcode, String country, String city, String street) {
        this.id = id;
        this.userId = userId;
        this.zipcode = zipcode;
        this.country = country;
        this.city = city;
        this.street = street;
    }

    public Address(int userId, String zipcode, String country, String city, String street) {
        this.userId = userId;
        this.zipcode = zipcode;
        this.country = country;
        this.city = city;
        this.street = street;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", userId=" + userId +
                ", zipcode='" + zipcode + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                '}';
    }
}
