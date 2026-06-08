package org.example;

import org.example.AppResources.Client_Print;
import org.example.Clients.Account;
import org.example.Clients.Registration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class ATM{
    public static void main(String[] args) throws IOException {
       System.out.println("Willkommen bei Express Bank");
       System.out.println("Sind Sie schon registriert ?");
       System.out.println("Bitte wählen Sie eine Antwort : Ja/Nein ?");
       BufferedReader reader = null;
       reader = new BufferedReader(new InputStreamReader(System.in));
       String antwort = reader.readLine();
       boolean kontroller = false;
       if (antwort.equalsIgnoreCase("Nein")) {
           try {
               Client client = new Client_Print().print();
               if (client != null) {
                   boolean registration = new Registration().enregistrement(client);
                   if (!registration) {
                       System.out.println("Sie sind Sie schon registriert !");
                   }
                   else  {
                       System.out.println("Registration erfolgreich !");
                       System.out.println("Um Ihr BankKonto zu öffen , brauchen wir zusätzliche Informationen von Ihnen ! ");
                       System.out.println("Welche Typ von Konto wollen Sie ? Sparkonto/GiroKonto/Depotkonto");
                       String type = reader.readLine();
                       if (!type.isEmpty()) {
                           String iban = new Account().createAccount(type , LocalDateTime.now() , client);
                           System.out.println("Hier ist Ihr Iban: " + iban);
                           kontroller = true;
                       }
                       if (kontroller) {
                           new Client_Print().option();
                       }


                   }
               }
           }
           catch (SQLException e){
               System.out.println(e.getMessage());
           }

       }
       else if (antwort.equalsIgnoreCase("Ja")) {
           new Client_Print().option();

       }
    }
}