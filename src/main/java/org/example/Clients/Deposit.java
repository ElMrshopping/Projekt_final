package org.example.Clients;
import com.itextpdf.kernel.validation.context.XrefTableValidationContext;
import org.example.AppResources.Generate_Recu;
import org.example.AppResources.Verification;
import org.example.Database.DataBaseManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Deposit {
    private final Connection conn;
    private final Verification verification;
    private final Generate_Recu generate_recu;
    public Deposit(){
        this.conn = DataBaseManager.getInstance().getConnection();
        this.verification = new Verification();
        this.generate_recu = new Generate_Recu();
    }
    public Deposit(Connection connection , Verification verification , Generate_Recu generate_recu){
        this.conn = connection;
        this.verification = verification;
        this.generate_recu = generate_recu;
    }
    public boolean deposit(String iban , int montant) {
        boolean verification_iban = verification.verifyIban(iban);
        if (verification_iban) {
            String req = "SELECT solde FROM Compte WHERE IBAN = ?";
            String sql = "UPDATE Compte SET solde= ? WHERE iban = ?";
                if (conn == null) {
                    System.out.println("Connexion impossible a la Base de Donnees");
                    return false;
                }
                try(PreparedStatement statement = conn.prepareStatement(sql);
                PreparedStatement statement2 = conn.prepareStatement(req))
                {
                    statement2.setString(1,iban);
                   ResultSet result = statement2.executeQuery();
                if(result.next()) {
                    int ancien_solde = result.getInt("solde");
                    int nouveau_solde = ancien_solde + montant;
                    statement.setInt(1, nouveau_solde);
                    statement.setString(2, iban);
                    statement.executeUpdate();
                    boolean resultat = generate_recu.generer_recu(montant , iban);
                    if (resultat) {
                        System.out.println("Recu depot !");
                    }
                    return true;
                }
            }
            catch (Exception e){
                System.out.println(e.getMessage());
                return false;
            }
        }
        else {
            System.out.println("Iban invalide !");
            return false;
        }
        return false;
    }
}

