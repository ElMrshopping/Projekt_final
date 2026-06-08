package org.example.Clients;
import org.example.AppResources.Generate_Recu;
import org.example.AppResources.Verification;
import org.example.Client;
import org.example.Database.DataBaseManager;
import org.sqlite.SQLiteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Deposit {
    public void deposit(String iban) {
        boolean verification_iban = new Verification().verifyIban(iban);
        if (verification_iban) {
            System.out.println("Entrez le Montant :");
            Scanner sc = new Scanner(System.in);
            int montant = sc.nextInt();
            String req = "SELECT solde FROM Compte WHERE IBAN = '" + iban + "'";
            String sql = "UPDATE Compte SET solde= ? WHERE iban = ?";
            try (Connection conn = DataBaseManager.getConnection();
                 PreparedStatement statement = conn.prepareStatement(sql)){
                PreparedStatement statement2 = conn.prepareStatement(req);
                ResultSet result = statement2.executeQuery();
                if(result.next()) {
                    int ancien_solde = result.getInt("solde");
                    int nouveau_solde = ancien_solde + montant;
                    statement.setInt(1, nouveau_solde);
                    statement.setString(2, iban);
                    statement.executeUpdate();
                    boolean resultat = new Generate_Recu().generer_recu(montant , iban);
                    if (resultat) {
                        System.out.println("Recu depot !");
                    }
                }
            }
            catch (SQLiteException e){
                System.out.println(e.getMessage());
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
        else {
            System.out.println("Iban invalide !");
        }

    }
}

