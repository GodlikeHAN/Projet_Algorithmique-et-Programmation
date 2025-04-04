package org.example;

public class PersonnelCabine extends Employe {
    private String qualification;

    public PersonnelCabine(String identifiant, String nom, String adresse, String contact, int NumeroEmploye, String DateEmbauche) {
        super(identifiant, nom, adresse, contact, NumeroEmploye, DateEmbauche);
    }
}
