package org.example.Clients;

import org.example.AppResources.Verification;
import org.example.Database.DataBaseManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Withdrawal {
    private final Connection connection;
    private final Verification verification;

    public Withdrawal() {
        this.connection = DataBaseManager.getInstance().getConnection();
        this.verification = new Verification();
    }

    public Withdrawal(Connection connection, Verification verification) {
        this.connection = connection;
        this.verification = verification;
    }

    public boolean retrait(String iban, int pin) {
        boolean resultVerification = verification.verifyPinWithiban(iban, pin);
        if (connection != null) {
            if (resultVerification) {
                Scanner sc = new Scanner(System.in);
                System.out.println("Wie viel wollen Sie auszahlen ?");
                int montant = sc.nextInt();
                if (verification.verifySoldo(montant, iban)) {
                    String sql = "UPDATE Compte SET solde = solde - ? WHERE iban = ?";
                    try (PreparedStatement statement = connection.prepareStatement(sql)) {
                        statement.setInt(1, montant);
                        statement.setString(2, iban);
                        statement.executeUpdate();
                        return true;
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
        return false;
    }

}
