package org.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class Vol {
    private String numeroVol;
    private String origine;
    private String destination;
    private Date dateHeureDepart;
    private Date dateHeureArrivee;
    private String etat;
    private Avion avion;
    private Pilote pilote;
    private List<PersonnelCabine> equipageCabine;
    List<Reservation> reservations;

    public String getNumeroVol() { return numeroVol; }
    public String getOrigine() { return origine; }
    public String getDestination() { return destination; }
    public Date getDateHeureDepart() { return dateHeureDepart; }
    public Date getDateHeureArrivee() { return dateHeureArrivee; }
    public String getEtat() { return etat; }
    public Avion getAvion() { return avion; }
    public void setAvion(Avion avion) { this.avion = avion; }

    public Vol(String numeroVol, String origine, String destination, Date dateHeureDepart, Date dateHeureArrivee) {
        this.numeroVol = numeroVol;
        this.origine = origine;
        this.destination = destination;
        this.dateHeureDepart = dateHeureDepart;
        this.dateHeureArrivee = dateHeureArrivee;
        this.etat = "En planification";
        this.equipageCabine = new ArrayList<>();
        this.reservations = new ArrayList<>();
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

    public void planifierVol() {
        if (avion != null && pilote != null && !equipageCabine.isEmpty()) {
            this.etat = "Planifié";
        }
    }

    public void annulerVol() {
        this.etat = "Annulé";
        reservations.forEach(r -> r.getPassager().annulerReservation(r.getNumeroReservation()));
        if (avion != null) {
            avion.affecterVol(null);
        }
    }

}