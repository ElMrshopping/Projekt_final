package org.example.Database;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataBaseManager {

    private static DataBaseManager instance;
    private Connection connection;

    private DataBaseManager() {
        try {
                Properties properties = new Properties();
                InputStream inputStream = DataBaseManager.class.getClassLoader().getResourceAsStream("db.properties");

                if (inputStream == null) {
                    System.out.println("Fichier db.properties introuvable !");
                    return;
                }
                properties.load(inputStream);
                inputStream.close();
                String driver = properties.getProperty("db.driver");
                String url = properties.getProperty("db.url");
                String username = properties.getProperty("db.username");
                String password = properties.getProperty("db.password");
                Class.forName(driver);
               connection = DriverManager.getConnection(url, username, password);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static synchronized DataBaseManager getInstance(){
        if (instance == null){
            instance = new DataBaseManager();
        }
        return instance;
    }
    public Connection getConnection(){
        return connection;
    }
}
