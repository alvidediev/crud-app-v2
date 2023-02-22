package ru.dediev.jdbc.config;

import java.sql.*;

import static ru.dediev.jdbc.util.Constants.*;

public class DatabaseConnection {

    private static DatabaseConnection instance;
    private Connection connection;

    private DatabaseConnection() {
        try {
            Class.forName(DRIVER);
            this.connection = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static DatabaseConnection getInstance() {
        try {
            if (instance == null) {
                instance = new DatabaseConnection();
            } else if (instance.getConnection().isClosed()) {
                instance = new DatabaseConnection();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return instance;
    }
}
