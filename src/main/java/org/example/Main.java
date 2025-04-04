package org.example;

import java.util.Date;
/*WENDI HAN et ELMOHTASSEM GABRIEL*/
public class Main {
    public static void main(String[] args) {

        Personne personne1 = new Personne("PERS-1","nom-PERS-1","adresse-PERS-1","contact-PERS-1");
        System.out.println(personne1.ObtenirInfos());
        Passager passager1 = new Passager("PASS-1","nom-PASS-1","adresse-PASS-1", "contact-PASS-1", "passeport-PASS-1");
        Employe employe1=new Employe("EMP-1","nom-EMP-1","adresse-EMP-1","contact-EMP-1",11,"DateEmbauche-EMP-1");
        System.out.println(employe1.ObtenirRoles());
        Vol vol1 = new Vol("VOL-1", "Paris", "Tokyo", new Date(2025- 4 -26), new Date(2025- 4 -27));
        Reservation reservation1 =new Reservation("RES-1", passager1,vol1);
        Aeroport aeroport_cdg = new Aeroport("Charles de Gaulle", "Paris", "Principal aéroport de Paris");
        Aeroport aeroport_orly = new Aeroport("Orly", "Paris", "Second aéroport de Paris");

        Avion avion = new Avion("F-GSPQ", "Airbus A320", 180);


    }
}
