package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.utils.DatabaseConnectionHandler;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductOrder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class OrderDaoPSQLTest {

    private OrderDao dao = new OrderDaoPSQL();
    private Order testOrder;

    @BeforeAll
    static void configConnection() {
        DatabaseConnectionHandler.setConfigPath("./src/main/java/com/codecool/shop/config/testConfig.json");
    }

    @BeforeEach
    void setUp() {
        testOrder = new Order(30);
    }

    @Test
    void add() {
        int id = dao.createOrder(testOrder);
        testOrder.setId(id);
        dao.remove(id);
    }

    @Test
    void testAddingDuplicatesRestricted() {
        assertThrows(IllegalStateException.class, () -> dao.createOrder(new Order(1)));
    }

    @Test
    void findActive() {
        Order expected = new Order(1, 3, new ArrayList<>(Arrays.asList(
                new ProductOrder(1, 1, 1, 3, new Product(
                        1, "Samsung Galaxy S8", "A phone.", 500, "USD",
                        "abc", 1, 1
                )),
                new ProductOrder(4, 1, 2, 7, new Product(
                        2, "Milka Dark", "Dark chocolate from the Alps.", 1, "USD",
                        "def", 3, 3
                )),
                new ProductOrder(5, 1, 3, 10, new Product(
                        3, "UltraBook 9001", "Cutting edge laptop for programmers who take it seriously.",
                        5000, "USD", "gmo", 2, 2
                ))
        )));
        assertEquals(expected.toString(), dao.findActive(3).toString());
    }

    @Test
    void remove() {
        int id = dao.createOrder(testOrder);
        dao.remove(id);
        assertNull(dao.findActive(30));
    }

    @Test
    void getAllCompleted() {
        List<Order> expected = new ArrayList<>(Arrays.asList(
                new Order(6, 3, 43434234, "PAID", "2018-02-16 13:32:24", new ArrayList<>(Arrays.asList(
                        new ProductOrder(10, 6, 2, 1000, new Product(
                                2, "Milka Dark", "Dark chocolate from the Alps.", 1, "USD",
                                "def", 3, 3
                        )),
                        new ProductOrder(11, 6, 1, 2, new Product(
                                1, "Samsung Galaxy S8", "A phone.", 500, "USD",
                                "abc", 1, 1
                        ))
                ))),
                new Order(7, 3, 8984234, "PAID", "2018-05-01 21:12:10", new ArrayList<>(Arrays.asList(
                        new ProductOrder(12, 7, 3, 10, new Product(
                                3, "UltraBook 9001", "Cutting edge laptop for programmers who take it seriously.",
                                5000, "USD", "gmo", 2, 2
                        )),
                        new ProductOrder(13, 7, 2, 5000, new Product(
                                2, "Milka Dark", "Dark chocolate from the Alps.", 1, "USD",
                                "def", 3, 3
                        ))
                )))
        ));
        assertEquals(expected.toString(), dao.getAllCompleted(3).toString());
    }

    @Test
    void update() {
        int id = dao.createOrder(testOrder);
        testOrder.setId(id);
        testOrder.setPaymentId(1337);
        dao.finalizeOrder(testOrder);
        Order orderInDB = dao.findById(id);
        assertEquals(1337, orderInDB.getPaymentId());
        assertEquals("PAID", orderInDB.getStatus());
        assertTrue(orderInDB.getDate().length() >= 18 && orderInDB.getDate().length() <= 19);
        dao.remove(id);
    }
}