package org.example;

import java.util.ArrayList;
import java.util.List;


class Aeroport {
    private String nom;
    private String ville;
    private String description;
    private List<Vol> vols;

    public Aeroport(String nom, String ville, String description) {
        this.nom = nom;
        this.ville = ville;
        this.description = description;
        this.vols = new ArrayList<>();
    }

    public String getNom() {
        return nom;
    }

    public String getVille() {
        return ville;
    }

    public String getDescription() {
        return description;
    }

    public void affecterVol(Vol vol) {
        if (!vols.contains(vol)) {
            vols.add(vol);
        }
    }

    public List<Vol> getVols() {
        return new ArrayList<>(vols);
    }

}
