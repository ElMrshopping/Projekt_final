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
    public boolean verifyEmailExiste(String email) {
        boolean result = false;
        String sql = "SELECT email FROM Client WHERE email = ?";
        if (conn != null) {
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setString(1, email);
                ResultSet res = statement.executeQuery();
                if (!res.next()) {
                    return result;
                }
                else  {
                    return !result;
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return result;
    }
    public boolean verifyPinWithiban(String iban ,  String pin){
        boolean result = false;
        String req = "SELECT pin_client FROM Compte WHERE iban = ? ;";
        if (conn != null) {
            try (PreparedStatement statement = conn.prepareStatement(req)){
                statement.setString(1 , iban);
                ResultSet resultSet = statement.executeQuery();
                if (!resultSet.next()) {
                    return result;
                }
                String hash_pin = resultSet.getString("pin_client");
                if (BCrypt.checkpw(pin , hash_pin)){
                    return !result;
                }
                return result;

            }
            catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return result;
    }
    public boolean verifySoldo(int montant , String iban) {
        boolean result = false;
        String req = "SELECT solde FROM Compte WHERE iban = ?";
        if (conn != null) {
            try (PreparedStatement statement = conn.prepareStatement(req)){
                statement.setString(1 , iban);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()){
                    int solde = resultSet.getInt(1);
                    if (solde < montant){
                        return result;
                    }
                    return !result;
                }
            }
            catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
        return result;
    }
    public boolean verifyTelefon(String telefon) {
        boolean result = telefon.matches("^\\+?\\d{10,15}$");
        return result;
    }
}
