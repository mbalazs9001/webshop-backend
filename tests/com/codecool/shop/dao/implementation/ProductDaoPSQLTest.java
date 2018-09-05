package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.utils.DatabaseConnectionHandler;
import com.codecool.shop.model.Product;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductDaoPSQLTest {

    private ProductDao dao = new ProductDaoPSQL();
    private Product testProduct;

    @BeforeAll
    static void configConnection() {
        DatabaseConnectionHandler.setConfigPath("./src/main/java/com/codecool/shop/config/testConfig.json");
    }

    @BeforeEach
    void setUp() {
        testProduct = new Product(
                "Snickers", "Chocolate bar with nuts.", 2, "USD",
                "yum", 3, 3
        );
    }

    @Test
    void add() {
        int id = dao.add(testProduct);
        testProduct.setId(id);
        assertEquals(testProduct.toString(), dao.find(id).toString());
        dao.remove(id);
    }

    @Test
    void find() {
        Product expected = new Product(
                2, "Milka Dark", "Dark chocolate from the Alps.", 1, "USD",
                "def", 3, 3
        );
        assertEquals(expected.toString(), dao.find(2).toString());
    }

    @Test
    void remove() {
        int id = dao.add(testProduct);
        dao.remove(id);
        assertNull(dao.find(id));
    }

    @Test
    void getAll() {
        List<Product> expected = new ArrayList<>(Arrays.asList(
                new Product(
                        1, "Samsung Galaxy S8", "A phone.", 500, "USD",
                        "abc", 1, 1
                ),
                new Product(
                        2, "Milka Dark", "Dark chocolate from the Alps.", 1, "USD",
                        "def", 3, 3
                ),
                new Product(
                        3, "UltraBook 9001", "Cutting edge laptop for programmers who take it seriously.",
                        5000, "USD", "gmo", 2, 2
                )
        ));
        assertEquals(expected.toString(), dao.getAll().toString());
    }

    @Test
    void getBySupplier() {
    }

    @Test
    void getByProductCategory() {
    }

    @Test
    void getBySupplierAndCategory() {
    }
}