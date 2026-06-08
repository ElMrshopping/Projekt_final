package org.example.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Modele {
    public  void init(){
        try (Connection conn = DataBaseManager.getConnection()){
            String client = "CREATE TABLE IF NOT EXISTS Client(" +
                    "pin int PRIMARY KEY NOT NULL," +
                    "nom varchar(255) NOT NULL," +
                    "prenom varchar(255) NOT NULL," +
                    "email varchar(255) NOT NULL ," +
                    "telephone char(16) NOT NULL ," +
                    "date_naissance TEXT NOT NULL )";

            String compte = "CREATE TABLE IF NOT EXISTS Compte(" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "iban char(18) NOT NULL," +
                    "solde BIGINT DEFAULT 0," +
                    "type varchar(255) NOT NULL," +
                    "block boolean DEFAULT false ," +
                    "created_at TEXT NOT NULL," +
                    "pin_client int NOT NULL, " +
                    "FOREIGN KEY(pin_client) REFERENCES Client(pin)ON DELETE CASCADE )";
            Statement stmt = conn.createStatement();
             stmt.execute(client);
             stmt.execute(compte);

        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

}
