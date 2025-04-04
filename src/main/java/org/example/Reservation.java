package org.example;

import java.util.Date;

class Reservation {
    private String numeroReservation;
    private Passager passager;
    private Vol vol;
    private Date dateReservation;
    private String statut;

    public String getNumeroReservation() { return numeroReservation; }
    public Passager getPassager() { return passager; }
    public Vol getVol() { return vol; }
    public Date getDateReservation() { return dateReservation; }
    public String getStatut() { return statut; }

    public Reservation(String numeroReservation, Passager passager, Vol vol) {
        this.numeroReservation = numeroReservation;
        this.passager = passager;
        this.vol = vol;
        this.dateReservation = new Date();
        this.statut = "Confirmée";
    }

    public void confirmerReservation() {
        this.statut = "Confirmée";
    }

    public void modifierReservation(Vol nouveauVol) {
        this.vol = nouveauVol;
    }

}