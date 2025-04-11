package org.example;

public class PersonnelCabine extends Employe {
    private String qualification;

    public PersonnelCabine(String identifiant, String nom, String adresse, String contact, int NumeroEmploye, String DateEmbauche) {
        super(identifiant, nom, adresse, contact, NumeroEmploye, DateEmbauche);
    }
    public void affecterVol(Vol vol) {
        vol.getEquipageCabine().add(this);
    }
    public void obtenirVol(int numeroVol) {
        for (Vol vol : Vol.listeVolsPlanifies) {
            if (vol.getNumeroVol() == numeroVol) {
                System.out.println(vol);
                break;
            }
        }
    }
}
