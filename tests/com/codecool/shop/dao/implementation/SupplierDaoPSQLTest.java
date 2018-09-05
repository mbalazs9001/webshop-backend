package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.utils.DatabaseConnectionHandler;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class SupplierDaoPSQLTest {

    private SupplierDaoPSQL dao = new SupplierDaoPSQL();
    private Supplier testSupplier;

    @BeforeAll
    static void configConnection() {
        DatabaseConnectionHandler.setConfigPath("./src/main/java/com/codecool/shop/config/testConfig.json");
    }

    @BeforeEach
    void setUp() {
        testSupplier = new Supplier("Amazon", "Webshop");
    }

    @Test
    void add() {
        int id = dao.add(testSupplier);
        testSupplier.setId(id);
        assertEquals(testSupplier.toString(), dao.find(id).toString());
        dao.remove(id);
    }

    @Test
    void find() {
        assertEquals(
                new Supplier(3, "Milka", "Tasty chocolate from Austria.").toString(),
                dao.find(3).toString()
        );
    }

    @Test
    void remove() {
        int id = dao.add(testSupplier);
        dao.remove(id);
        assertNull(dao.find(id));
    }

    @Test
    void getAll() {
        List<Supplier> suppliers = new ArrayList<>(Arrays.asList(
                new Supplier(1, "Samsung", "Cutting edge electronics."),
                new Supplier(2, "General Electronics", "Office of the year."),
                new Supplier(3, "Milka", "Tasty chocolate from Austria.")
        ));
        assertEquals(suppliers.toString(), dao.getAll().toString());
    }
}