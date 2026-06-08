package org.example.AppResources;

import org.example.Client;
import org.example.Clients.Deposit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.Scanner;

public class Client_Print {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    Scanner sc = new Scanner(System.in);
    public Client print () throws IOException {
        System.out.println("Fuer die Registrierung baruchen wir Ihre Daten !");
        System.out.println("Nachname: ");
        String nachname = reader.readLine();
        System.out.println("Vorname: ");
        String vorname = reader.readLine();
        System.out.println("Telefonnummer:");
        String telefonnummer = reader.readLine();
        System.out.println("Email: ");
        String email = reader.readLine();
        System.out.println("Pincode:");
        int pincode = sc.nextInt();
        System.out.println("Geburtsjahr:");
        int geburtsjahr = sc.nextInt();
        System.out.println("Geburtsmonth:");
        int geburtsmonth = sc.nextInt();
        System.out.println("Geburtsday:");
        int geburtsday = sc.nextInt();
        LocalDate geburtsdatum = LocalDate.of(geburtsjahr, geburtsmonth, geburtsday);
        while (nachname.isEmpty() || vorname.isEmpty() || email.isEmpty() || geburtsdatum.toString().isEmpty() )
        {
            System.out.print("Nachname: ");
            nachname = reader.readLine();
            System.out.print("Vorname: ");
            vorname = reader.readLine();
            System.out.print("Telefonnummer: ");
            telefonnummer = reader.readLine();
            System.out.print("Email: ");
            email = reader.readLine();
            System.out.print("Pincode: ");
            pincode = sc.nextInt();
            System.out.print("Geburtsdatum: ");
            System.out.println("Geburtsjahr:");
            geburtsjahr = sc.nextInt();
            System.out.println("Geburtsmonth:");
            geburtsmonth = sc.nextInt();
            System.out.println("Geburtsday:");
            geburtsday = sc.nextInt();
            geburtsdatum = LocalDate.of(geburtsjahr, geburtsmonth, geburtsday);

        }
        return new Client(nachname , vorname ,telefonnummer , pincode , email, geburtsdatum);
    }
    public void option(){
        try {
            System.out.println("Was wollen Sie heute machen ?");
            System.out.println("Bitte eine Option auswählen !");
            System.out.println("1.Einzahlung");
            System.out.println("2.Auszahlung");
            String option = reader.readLine();
            switch (option) {
                case "1":
                    System.out.println("Iban eingeben bitte :");
                    String iban = reader.readLine();
                    new Deposit().deposit(iban);
                    break;
            }
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
