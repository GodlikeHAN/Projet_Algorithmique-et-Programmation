package org.example;

public class Pilote extends Employe {
    private String licence;
    private int heuresDeVol;

    public Pilote(String identifiant, String nom, String adresse, String contact, int numeroEmploye, String dateEmbauche, String licence, int heuresDeVol) {
        super(identifiant, nom, adresse, contact, numeroEmploye, dateEmbauche);
        this.licence = licence;
        this.heuresDeVol = heuresDeVol;
    }

    public void affecterVol(Vol vol) {
        vol.affecterPilote(this);
    }

    public Vol obtenirVol() {
        return null;
    }
}