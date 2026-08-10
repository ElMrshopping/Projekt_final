package org.example.Clients;

import org.example.AppResources.Verification;
import org.example.Client;
import org.example.Database.DataBaseManager;
import org.mindrot.jbcrypt.BCrypt;
import org.sqlite.SQLiteException;

import java.sql.*;

public class Registration {
    public boolean enregistrement(Client client) throws SQLException{
        String insert = "INSERT INTO Client VALUES (?,?,?,?,?,?)";
            Connection conn = DataBaseManager.getInstance().getConnection();
            if (conn == null){
                System.out.println("Connexion impossible !");
                return false;
            }

            try(PreparedStatement p = conn.prepareStatement(insert)) {
            boolean verification = new Verification().verifyPin(client.getEmail() ,client.getPin());
            if(!verification){
                String pin_hash = BCrypt.hashpw(client.getPin() , BCrypt.gensalt());
             p.setString(1,pin_hash);
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
        catch (SQLException e) {
            System.out.println(e.getMessage());
            return true;
        }
    }
}
