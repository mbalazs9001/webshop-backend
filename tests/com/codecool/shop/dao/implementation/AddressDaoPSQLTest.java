package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.AddressDao;
import com.codecool.shop.dao.utils.DatabaseConnectionHandler;
import com.codecool.shop.model.Address;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressDaoPSQLTest {

    private AddressDao dao = new AddressDaoPSQL();
    private Address testAddress;

    @BeforeAll
    static void configConnection() {
        DatabaseConnectionHandler.setConfigPath("./src/main/java/com/codecool/shop/config/testConfig.json");
    }

    @BeforeEach
    void setUp() {
        testAddress = new Address(1, "1234", "Hungary", "Budapest", "Somewhere");
    }

    @Test
    void add() {
        int id = dao.add(testAddress);
        testAddress.setId(id);
        assertEquals(testAddress.toString(), dao.find(id).toString());
        dao.remove(id);
    }

    @Test
    void find() {
        assertEquals(
                new Address(2, 1, "1060", "Hungary", "Budapest",
                        "Nagymező street 42.").toString(),
                dao.find(2).toString()
        );
    }

    @Test
    void remove() {
        int id = dao.add(testAddress);
        dao.remove(id);
        assertNull(dao.find(id));
    }

    @Test
    void modify() {
        int id = dao.add(testAddress);
        testAddress = new Address(id, 1, "12345", "Netherlands", "Amsterdam", "Somewhere");
        dao.modify(testAddress);
        assertEquals(testAddress.toString(), dao.find(id).toString());
        dao.remove(id);
    }
}