package org.example.Clients;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Iterator;
public class Balance {
    public void balance() throws Exception{
        System.out.println("Insert the ID card :");
        String id_card=null;
        Path depositPath = Paths.get("Fichier/deposit.csv");
        Path withdrawalPath = Paths.get("Fichier/withdrawal.csv");
        String line = null;
        String line2= null;
        BufferedReader bfr = null;
        BufferedReader bfr1 = null;
        BufferedReader bfr2 = null;
        ArrayList<String> depoList = new ArrayList<>();
        ArrayList<String> withList = new ArrayList<>();
        int coloneIndex = 1;
        String separator = "\\|";
        int counter = 2;
        try {
            bfr = new BufferedReader(new InputStreamReader(System.in));
            id_card = bfr.readLine();
            while(id_card == null || id_card.trim().isEmpty()) {
                System.out.println("ID card cannot be empty!");
                System.out.println("Insert the ID card :");
                id_card = bfr.readLine();
            }
            while(counter > 0)
            {
                if(id_card.contains("|") || id_card.contains(" ")) {
                    System.out.println("ID card cannot contain '|' or spaces!");
                    System.out.println("Insert the ID card :");
                    id_card = bfr.readLine();
                    counter++;
                }
                else{
                    if(id_card.length() < 9 || id_card.length() > 9 || !id_card.startsWith("DE") ) {
                        System.out.println("ID card must be at least 9 characters long and start with 'DE'!");
                        System.out.println("Insert the ID card :");
                        id_card = bfr.readLine();
                        counter++;
                    }
                    else{
                        System.out.println("ID card is valid!");
                        counter = 0;
                    }
                }
            }
            bfr1 = Files.newBufferedReader(depositPath);
            bfr2 = Files.newBufferedReader(withdrawalPath);
            while((line = bfr1.readLine()) != null){
                if(line.startsWith(id_card))
                {
                    depoList.add(line.split(separator)[coloneIndex]);
                }
            }
            while((line2 = bfr2.readLine()) != null){
                if(line2.startsWith(id_card))
                {
                    withList.add(line2.split(separator)[coloneIndex]);
                }
            }


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        finally{

        }
        bfr.close();
        bfr1.close();
        bfr2.close();
        Iterator<String> depIterator = depoList.iterator();
        Iterator<String> withIterator = withList.iterator();
        double balance = 0;
        double sumDep = 0;
        double sumWith = 0;
        while(depIterator.hasNext())
        {
            String dep = depIterator.next();
            sumDep += Double.parseDouble(dep.trim());
            System.out.println("sumDep: " + sumDep);
        }
        while(withIterator.hasNext())
        {
            String with = withIterator.next();
            sumWith += Double.parseDouble(with.trim());
        }
        balance = sumDep - sumWith;
        System.out.println("Your balance is :" + balance);
    }
}

