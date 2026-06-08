package org.example.AppResources;


import java.util.Random;

public class GenerateIban {
    public String generateIban() {
        Random rand = new Random();
        String bankcode = "";
        for (int i=0; i<8; i++) {
            bankcode = bankcode + rand.nextInt(10);
        }
        String konto = "";
        for (int i=0; i<10; i++) {
            konto = konto + rand.nextInt(10);
        }
        String zaehlen = bankcode + konto + "131400";
        int rest = 0;
        for (char c : zaehlen.toCharArray()) {
            rest = (rest * 10 + Character.getNumericValue(c)) % 97;
        }
        int pruefziffer = 98 - rest;
        return "DE" + String.format("%02d", pruefziffer) + bankcode + konto;
    }
}
