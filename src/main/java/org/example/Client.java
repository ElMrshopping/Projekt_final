package org.example;

import java.sql.Date;
import java.time.LocalDate;

public class Client {
    private String nom;
    private String numero_telephone;
    private int pin;
    private String email;
    private LocalDate date_naissance;
    public Client(String nom, String prenom, String numero_telephone, int pin, String email, LocalDate date_naissance) {
        this.nom = nom;
        this.prenom = prenom;
        this.numero_telephone = numero_telephone;
        this.pin = pin;
        this.email = email;
        this.date_naissance = date_naissance;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setNumero_telephone(String numero_telephone) {
        this.numero_telephone = numero_telephone;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDate_naissance(LocalDate date_naissance) {
        this.date_naissance = date_naissance;
    }

    private String prenom;

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNumero_telephone() {
        return numero_telephone;
    }

    public int getPin() {
        return pin;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getDate_naissance() {
        return date_naissance;
    }
}
