package org.example.Database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DataBaseManager {
    private static final String JDBC_URL = "jdbc:sqlite:ATMdb";
    private static final String USER = "bobt";
    private static final String PASSWORD = "root";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
    }
}
