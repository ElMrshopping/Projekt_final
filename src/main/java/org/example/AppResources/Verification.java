package org.example.AppResources;

import org.example.Database.DataBaseManager;
import org.sqlite.SQLiteException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Verification {
    public boolean verifyIban(String iban) {
        boolean result = false;
        String sql = "SELECT iban FROM Compte WHERE iban = ?";
        try (Connection conn = DataBaseManager.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)){
            statement.setString(1, iban);
            ResultSet rs = statement.executeQuery();
            if (!rs.next()) {
               return result;
            }
            return !result;
        }
        catch (SQLiteException e){
            System.out.println(e.getMessage());
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return result;
    }
    public boolean verifyPin(int pin) {
        boolean result = false;
        String sql = "SELECT pin FROM Client WHERE pin = ?";
        try (Connection conn = DataBaseManager.getConnection();
        PreparedStatement statement = conn.prepareStatement(sql)){
            statement.setInt(1,pin);
            ResultSet res = statement.executeQuery();
            if (!res.next()){
                return result;
            }
            return !result;

        }
        catch (SQLiteException e){
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
}
