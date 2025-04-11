package org.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class Vol {
    private String numeroVol;
    private String[] origine = new String[2];
    private String[] destination = new String[2];
    private Date dateHeureDepart;
    private Date dateHeureArrivee;
    private String etat;
    private Avion avion;

    private Pilote pilote;
    private List<PersonnelCabine> equipageCabine;
    List<Reservation> reservations;
    public static ArrayList<Vol> listeVolsPlanifies = new ArrayList<>(); // Liste de vols planifiés

    public String getNumeroVol() { return numeroVol; }
    public String[] getOrigine() { return origine; }
    public String[] getDestination() { return destination; }
    public Date getDateHeureDepart() { return dateHeureDepart; }
    public Date getDateHeureArrivee() { return dateHeureArrivee; }
    public String getEtat() { return etat; }
    public Avion getAvion() { return avion; }
    public void setAvion(Avion avion) { this.avion = avion; }
    public List<PersonnelCabine> getEquipageCabine() {return equipageCabine;}

    public String getPrimaryOrigin() {
        return origine[0];
    }


    public String getPrimaryDestination() {
        return destination[0];
    }

    public void setPrimaryOrigin(String airportCode) {
        this.origine[0] = airportCode;
    }

    public void setPrimaryDestination(String airportCode) {
        this.destination[0] = airportCode;
    }
    public Vol(String numeroVol, String origine, String destination, Date dateHeureDepart, Date dateHeureArrivee) {
        this.numeroVol = numeroVol;
        this.setPrimaryOrigin(origine);
        this.setPrimaryDestination(destination);
        this.dateHeureDepart = dateHeureDepart;
        this.dateHeureArrivee = dateHeureArrivee;
        this.etat = "En planification";
        this.equipageCabine = new ArrayList<>();
        this.reservations = new ArrayList<>();
        listeVolsPlanifies.add(this);
    }

    public void affecterPilote(Pilote pilote) {
        this.pilote = pilote;
    }

    public void affecterPersonnelCabine(PersonnelCabine personnel) {
        equipageCabine.add(personnel);
    }

    public void ajouterReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public static List<Vol> planifierVol(String dateHeureDepart) {
        System.out.println("=========== Vols pour le " + dateHeureDepart + " ===============");
        List<Vol> volsDuJour = new ArrayList<>();
        for (Vol vol : Vol.listeVolsPlanifies) {

            if (vol.dateHeureDepart.toString().startsWith(dateHeureDepart)) {
                volsDuJour.add(vol);
            }
        }
        return volsDuJour;
    }

    public static void annulerVol(String numeroVol) {      // BUG : annuler vol 1 annule vol 1 et 2.
        //this.numeroVol = numeroVol;
        for (Vol v : listeVolsPlanifies) {
            if (v.numeroVol.equals(numeroVol)) {
                v.etat = "Annulé";
                System.out.println("Le vol " + numeroVol + " a été annulé.");
                return;
            }
        }
        System.out.println("Le vol " + numeroVol + " n'a pas été trouvé.");
    }

    public void modifierVol(String origine, String destination, Date dateHeureDepart, Date dateHeureArrivee, String etat) {
        this.origine[0] = origine;
        this.destination[0] = destination;
        this.dateHeureDepart = dateHeureDepart;
        this.dateHeureArrivee = dateHeureArrivee;
        this.etat = etat;

        System.out.println("Le vol " + numeroVol + " a été modifié.");
    }

}