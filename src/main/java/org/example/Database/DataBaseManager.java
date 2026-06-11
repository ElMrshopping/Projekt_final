package org.example.Database;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataBaseManager {
    private static Connection connection;

    public static Connection getConnection()  {
        try {
            if (connection == null) {
                Properties properties = new Properties();
                InputStream inputStream = DataBaseManager.class.getClassLoader().getResourceAsStream("db.properties");
                properties.load(inputStream);
                String driver = properties.getProperty("driver");
                String url = properties.getProperty("url");
                String username = properties.getProperty("username");
                String password = properties.getProperty("password");
                Class.forName(driver);
                connection = DriverManager.getConnection(url, username, password);
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }
}
