package org.example;

import java.util.Date;

public class Employe extends Personne {
    private int numeroEmploye;
    private String dateEmbauche;

    public Employe(String identifiant, String nom, String adresse, String contact, int numeroEmploye, String dateEmbauche) {
        super(identifiant, nom, adresse, contact);
        this.numeroEmploye = numeroEmploye;
        this.dateEmbauche = dateEmbauche;
    }

    public String obtenirRole() {
        return this.getClass().getSimpleName();
    }

    public int getNumeroEmploye() {
        return numeroEmploye;
    }

    public String getDateEmbauche() {
        return dateEmbauche;
    }
}