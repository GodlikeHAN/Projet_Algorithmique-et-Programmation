package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Passager extends Personne {
    private String passeport;
    private List<Reservation> reservations;

    public Passager(String identifiant, String nom, String adresse, String contact, String passeport) {
        super(identifiant, nom, adresse, contact);
        this.passeport = passeport;
        this.reservations = new ArrayList<>();
    }

    public void reserverVol(Vol vol) {
        String identifiant = "RES-"+ Math.abs(new Random().nextInt(1000));

        Reservation reservation = new Reservation(identifiant, this, vol);
        reservations.add(reservation);
        vol.ajouterReservation(reservation);
    }

    public void annulerReservation(String numeroReservation) {
        Reservation reservationASupprimer = null;

        for (Reservation r : reservations) {
            if (r.getNumeroReservation().equals(numeroReservation)) {
                reservationASupprimer = r;
                break;
            }
        }

        if (reservationASupprimer != null) {
            reservations.remove(reservationASupprimer);
            reservationASupprimer.getVol().reservations.remove(reservationASupprimer);
        }
    }

    public List<Reservation> obtenirReservations() {
        return new ArrayList<>(reservations);
    }

}
