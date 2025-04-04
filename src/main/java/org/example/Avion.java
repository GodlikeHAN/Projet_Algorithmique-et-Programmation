package org.example;

import java.util.Date;

class Avion {
    private String immatriculation;
    private String modele;
    private int capacite;
    private Vol volAffecte;

    public String getImmatriculation() { return immatriculation; }
    public String getModele() { return modele; }
    public int getCapacite() { return capacite; }

    public Avion(String immatriculation, String modele, int capacite) {
        this.immatriculation = immatriculation;
        this.modele = modele;
        this.capacite = capacite;
    }

    public void affecterVol(Vol vol) {
        if (verifierDisponibilite(vol.getDateHeureDepart())) {
            this.volAffecte = vol;
            vol.setAvion(this);
        }
    }

    public boolean verifierDisponibilite(Date date) {
        return volAffecte == null || !volAffecte.getDateHeureDepart().equals(date);
    }


}