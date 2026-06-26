package org.example.AppResources;

import org.example.Database.DataBaseManager;
import org.sqlite.SQLiteException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Verification {
    public boolean verifyIban(String iban) {
        boolean result = false;
        String sql = "SELECT iban FROM Compte WHERE iban = ?";
        Connection conn = DataBaseManager.getConnection();
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
    public boolean verifyPin(int pin) {
        boolean result = false;
        String sql = "SELECT pin FROM Client WHERE pin = ?";
        Connection conn = DataBaseManager.getConnection();
        if (conn != null) {
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setInt(1, pin);
                ResultSet res = statement.executeQuery();
                if (!res.next()) {
                    return result;
                }
                return !result;

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return result;
    }
}
