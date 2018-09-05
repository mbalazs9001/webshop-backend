package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductOrderDao;
import com.codecool.shop.dao.utils.DatabaseConnectionHandler;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductOrder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductOrderDaoPSQLTest {

    private ProductOrderDao dao = new ProductOrderDaoPSQL();
    private ProductOrder testProductOrder;

    @BeforeAll
    static void configConnection() {
        DatabaseConnectionHandler.setConfigPath("./src/main/java/com/codecool/shop/config/testConfig.json");
    }

    @BeforeEach
    void setUp() {
        testProductOrder = new ProductOrder(2, 2);
    }

    @Test
    void add() {
        int id = dao.add(testProductOrder);
        testProductOrder = new ProductOrder(
                id, 2, 2, 1, new Product(
                2, "Milka Dark", "Dark chocolate from the Alps.",
                1, "USD", "def", 3, 3
        )
        );
        assertEquals(testProductOrder.toString(), dao.find(id).toString());
        dao.remove(id);
    }

    @Test
    void updateQuantity() {
        int id = dao.add(testProductOrder);
        testProductOrder = new ProductOrder(
                id, 2, 2, 100, new Product(
                        2, "Milka Dark", "Dark chocolate from the Alps.",
                1, "USD", "def", 3, 3
            )
        );
        dao.updateQuantity(id, 100);
        assertEquals(testProductOrder.toString(), dao.find(id).toString());
        dao.remove(id);
    }

    @Test
    void remove() {
        int id = dao.add(testProductOrder);
        dao.remove(id);
        assertNull(dao.find(id));
    }

    @Test
    void find() {
        assertEquals(
                new ProductOrder(7, 4, 3, 1, new Product(
                        3, "UltraBook 9001", "Cutting edge laptop for programmers who take it seriously.",
                        5000, "USD", "gmo", 2, 2
                )).toString(),
                dao.find(7).toString()
        );
    }

    @Test
    void getByOrder() {
    }
}