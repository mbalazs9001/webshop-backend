package com.codecool.shop.dao;

import com.codecool.shop.model.Address;

public interface AddressDao {
    int add(Address address);
    Address find(int userId);
    void remove(int id);
    void modify(Address address);

}
