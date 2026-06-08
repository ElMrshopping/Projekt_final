package org.example.Clients;

import org.example.AppResources.Verification;
import org.example.Client;
import org.example.Database.DataBaseManager;
import org.sqlite.SQLiteException;

import java.sql.*;

public class Registration {
    public boolean enregistrement(Client client) throws SQLException{
        String insert = "INSERT INTO Client VALUES (?,?,?,?,?,?)";
        try(Connection conn = DataBaseManager.getConnection();
            PreparedStatement p = conn.prepareStatement(insert);)
        {
            boolean verification = new Verification().verifyPin(client.getPin());
            if(!verification){
             p.setInt(1,client.getPin());
             p.setString(2,client.getNom());
             p.setString(3,client.getPrenom());
             p.setString(4,client.getEmail());
             p.setString(5, client.getNumero_telephone());
             p.setString(6 , Date.valueOf(client.getDate_naissance()).toString());
             p.executeUpdate();
             return true;
            }
            else {
                return false;
            }
        }
        catch (SQLiteException e) {
            e.printStackTrace();
            return true;
        }
    }
}
