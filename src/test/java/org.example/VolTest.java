package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;

class VolTest {
    @Test
    void testPlanifierVol() {
        Avion avion = new Avion("F-ABCD", "Boeing 737", 150);
        Pilote pilote = new Pilote("P1", "John", "Paris", "123", 1001, "2020-01-01", "LIC123", 5000);
        Vol vol = new Vol("V001", "Paris", "London", new Date(), new Date());
        vol.affecterPilote(pilote);
        vol.setAvion(avion);
        vol.planifierVol();
        assertEquals("Planifié", vol.getEtat());
    }
}

class ReservationTest {
    @Test
    void testReservationCreation() {
        Passager passager = new Passager("PA1", "Alice", "Paris", "456", "P123");
        Vol vol = new Vol("V002", "Paris", "Rome", new Date(), new Date());
        Reservation reservation = new Reservation("R001", passager, vol);
        assertEquals("Confirmée", reservation.getStatut());
    }
}