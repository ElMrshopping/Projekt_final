package org.example.Clients;

import org.example.AppResources.Verification;
import org.example.Database.DataBaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Balance {
    private final Connection connection;
    private final Verification verification;

    public Balance(){
        this.connection = DataBaseManager.getInstance().getConnection();
        this.verification = new Verification();
    }

    public Balance(Connection connection , Verification verification ){
        this.connection = connection;
        this.verification = verification;
    }
    public boolean checksolde(String iban){
        if (connection != null) {
            boolean verifcation_check = verification.verifyIban(iban);
            if (verifcation_check) {
                String sql = "SELECT solde FROM Compte WHERE iban = ?";
                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setString(1 , iban);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if (resultSet.next()){
                        int solde = resultSet.getInt("solde");
                        System.out.println("Aktuel Soldo :" + solde);
                    }
                }
                catch (SQLException e){
                    System.out.println(e.getMessage());
                }
            }
            return true;
        }
        else {
            return false;
        }
    }
}