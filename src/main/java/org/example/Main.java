package org.example;

import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("===== SYSTÈME DE GESTION DE VOL =====");

        // Création d'une personne
        Personne personne1 = new Personne("PERS-1", "Jean Dupont", "12 rue Paris", "0600000000");
        System.out.println("Infos personne: " + personne1.ObtenirInfos());

        // Création d’un employé pilote
        Pilote pilote1 = new Pilote("PIL-1", "Marie P", "adresse pilote", "0601010101", 101, "2020-01-01");
        System.out.println("Rôle employé (pilote) : " + pilote1.ObtenirRoles());

        // Création de personnel cabine
        PersonnelCabine cabine1 = new PersonnelCabine("CAB-1", "Julien C", "adresse cabine", "0602020202", 102, "2019-05-01");
        System.out.println("Rôle employé (cabine) : " + cabine1.ObtenirRoles());

        // Création d’un passager
        Passager passager1 = new Passager("PASS-1", "Léa V", "adresse passager", "0610101010", "X123456");

        // Création d’un vol
        Date depart = new Date(2025 - 1900, 3, 26, 10, 0); // Avril (mois 3)
        Date arrivee = new Date(2025 - 1900, 3, 27, 2, 0);
        Vol vol1 = new Vol(1, "Paris", "Tokyo", depart, arrivee);
        Vol.listeVolsPlanifies.add(vol1);

        // Affectation du pilote et du personnel cabine
        vol1.affecterPilote(pilote1);
        vol1.affecterPersonnelCabine(cabine1);

        // Affectation du vol au personnel
        cabine1.affecterVol(vol1);

        // Création et affectation de l'avion
        Avion avion1 = new Avion("Q-NDGD-365", "Airbus A320", 180);
        if (avion1.verifierDisponibilite(depart)) {
            avion1.affecterVol(vol1);
            System.out.println("Avion affecté au vol : " + avion1.getImmatriculation());
        } else {
            System.out.println("Avion indisponible pour cette date.");
        }

        // Affectation du vol à un aéroport
        Aeroport aeroportCDG = new Aeroport("Charles de Gaulle", "Paris", "Principal aéroport de Paris");
        aeroportCDG.affecterVol(vol1);

        // Réservation d’un vol par le passager
        passager1.reserverVol(vol1);

        // Affichage des réservations
        List<Reservation> reservations = passager1.obtenirReservations();
        System.out.println("Réservations du passager :");
        for (Reservation r : reservations) {
            System.out.println("- Réservation " + r.getNumeroReservation() + " pour le vol " + r.getVol().getNumeroVol());
        }

        // Annulation d'une réservation
        if (!reservations.isEmpty()) {
            String resId = reservations.get(0).getNumeroReservation();
            passager1.annulerReservation(resId);
            System.out.println("Réservation " + resId + " annulée.");
        }

        // Planification des vols
        List<Vol> volsPlanifies = Vol.planifierVol("Sam 26 Avril");
        System.out.println("Vols planifiés le 26 avril :");
        for (Vol v : volsPlanifies) {
            System.out.println("→ Vol #" + v.getNumeroVol() + " de " + v.getOrigine()[0] + " à " + v.getDestination()[0]);
        }

        // Obtenir infos d’un vol 
        cabine1.obtenirVol(1);

        // Annulation du vol
        Vol.annulerVol(1);
        System.out.println("État du vol après annulation : " + vol1.getEtat());
    }
}
