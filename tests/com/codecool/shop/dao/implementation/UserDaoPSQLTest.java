package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.utils.DatabaseConnectionHandler;
import com.codecool.shop.model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDaoPSQLTest {

    private UserDaoPSQL dao = new UserDaoPSQL();
    private User testUser;

    @BeforeAll
    static void configConnection() {
        DatabaseConnectionHandler.setConfigPath("./src/main/java/com/codecool/shop/config/testConfig.json");
    }

    @BeforeEach
    void setupTestUser() {
        testUser = new User(
                "Alistair Theirin",
                "mabari",
                "calenhad@gmail.com",
                "222-333-555"
        );
    }

    @Test
    void find() {
        assertEquals(
                new User(1,"John Doe", "admin", "admin@admin.org", "123456789").toString(),
                dao.find(1).toString()
        );
    }

    @Test
    void add() {
        int id = dao.add(testUser);
        testUser.setId(id);
        assertEquals(testUser.toString(), dao.find(id).toString());
        dao.remove(id);
    }

    @Test
    void remove() {
        int id = dao.add(testUser);
        dao.remove(id);
        assertNull(dao.find(id));
    }
}