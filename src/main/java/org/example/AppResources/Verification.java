package org.example.AppResources;

import org.example.Database.DataBaseManager;
import org.mindrot.jbcrypt.BCrypt;
import org.sqlite.SQLiteException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Verification {
    private final  Connection conn;
    public Verification(){
        this.conn = DataBaseManager.getInstance().getConnection();
    }
    public Verification(Connection conn){
        this.conn = conn;
    }
    public boolean verifyIban(String iban) {
        boolean result = false;
        String sql = "SELECT iban FROM Compte WHERE iban = ?";
        if (conn != null) {
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, iban);
                ResultSet rs = statement.executeQuery();
                if (!rs.next()) {
                    return result;
                }
                return !result;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return result;
    }
    public boolean verifyPin(String email , String iban) {
        boolean result = false;
        String sql = "SELECT pin FROM Client WHERE email = ?";
        if (conn != null) {
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, email);
                ResultSet res = statement.executeQuery();
                if (!res.next()) {
                    return result;
                }
                String iban_dbb = res.getString("iban");
                return BCrypt.checkpw(iban_dbb , iban);

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return result;
    }
}
