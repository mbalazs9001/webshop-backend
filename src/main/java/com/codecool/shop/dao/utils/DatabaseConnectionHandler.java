package com.codecool.shop.dao.utils;

import com.codecool.shop.config.Config;
import com.codecool.shop.config.DataReader;

import java.sql.*;

public class DatabaseConnectionHandler {

    private static String configPath;

    private Connection connection;

    private static DatabaseConnectionHandler instance;

    private DatabaseConnectionHandler() {
        try {
            if (configPath == null) {
                configPath = "./src/main/java/com/codecool/shop/config/productionConfig.json";
            }
            Config config = getConfig(configPath);
            connection = DriverManager.getConnection(config.path, config.username, config.password);
        } catch (SQLException e) {
            System.out.println("Could not establish database connection.");
            e.printStackTrace();
        }
    }

    public static void setConfigPath(String configPath) {
        DatabaseConnectionHandler.configPath = configPath;
    }

    private Config getConfig(String path) {
        return new DataReader(path).getData(Config.class);
    }

    public static synchronized Connection getConnection() {
        if (instance == null) {
            instance = new DatabaseConnectionHandler();
        }
        return instance.connection;
    }
}
