package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.utils.DatabaseConnectionHandler;
import com.codecool.shop.model.ProductCategory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductCategoryDaoPSQLTest {

    private ProductCategoryDaoPSQL dao = new ProductCategoryDaoPSQL();
    private ProductCategory testCategory;

    @BeforeAll
    static void configConnection() {
        DatabaseConnectionHandler.setConfigPath("./src/main/java/com/codecool/shop/config/testConfig.json");
    }

    @BeforeEach
    void setupTestCategory() {
        testCategory = new ProductCategory(
                "Console",
                "Electronics",
                "Gaming platform for pure gamers with no keyboard."
        );
    }

    @Test
    void add() {
        int id = dao.add(testCategory);
        testCategory.setId(id);
        assertEquals(testCategory.toString(), dao.find(id).toString());
        dao.remove(id);
    }

    @Test
    void find() {
        assertEquals(
                new ProductCategory(2, "Laptop", "Electronics", "Portable powerhouses for sale.").toString(),
                dao.find(2).toString()
        );
    }

    @Test
    void remove() {
        int id = dao.add(testCategory);
        dao.remove(id);
        assertNull(dao.find(id));
    }

    @Test
    void getAll() {
        List<ProductCategory> productCategories = new ArrayList<>(Arrays.asList(
                new ProductCategory(1, "Smarthphone", "Electronics", "Phones that think instead of you."),
                new ProductCategory(2, "Laptop", "Electronics", "Portable powerhouses for sale."),
                new ProductCategory(3, "Chocolate", "Snacks", "Tasty sweets to lighten the mood.")
        ));
        assertEquals(productCategories.toString(), dao.getAll().toString());
    }
}