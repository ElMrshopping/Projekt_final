package org.example.Clients;

import org.example.AppResources.GenerateIban;
import org.example.AppResources.Verification;
import org.example.Client;
import org.example.Database.DataBaseManager;
import org.sqlite.SQLiteException;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Account {
    public String createAccount(String type , LocalDateTime created_time , Client client){
        String iban_generate = new GenerateIban().generateIban();
        String insert = "INSERT INTO Compte (iban , type , created_at , pin_client) VALUES (?,?,?,?)";
        try (Connection conn = DataBaseManager.getConnection();
             PreparedStatement statement2 = conn.prepareStatement(insert)){
                statement2.setString(1,iban_generate);
                statement2.setString(2,type);
                DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String date_time = created_time.format(format);
                statement2.setString(3,date_time);
                statement2.setInt(4,client.getPin());
                if (statement2.executeUpdate()>0){
                    System.out.println("Konto erfolgreich geöffnet !");
                }
        }
        catch(SQLiteException e) {
            System.out.println(e.getMessage());
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return iban_generate;
    }
}
