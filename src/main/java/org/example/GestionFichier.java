package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GestionFichier {
    public static List<Vol> importVolsFromCSV(String filePath) {
        List<Vol> vols = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                Vol vol = new Vol(data[0], data[1], data[2], new Date(data[3]), new Date(data[4]));
                vols.add(vol);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return vols;
    }

    public static void saveReservationToCSV(Reservation reservation, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            String data = String.format("%s,%s,%s,%s",
                    reservation.getNumeroReservation(),
                    reservation.getPassager().getIdentifiant(),
                    reservation.getVol().getNumeroVol(),
                    reservation.getStatut());
            writer.write(data);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}