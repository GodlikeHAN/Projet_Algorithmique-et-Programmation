package org.example;

import java.io.*;
import java.text.ParseException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            // 1.
            InputStream is = Main.class.getClassLoader().getResourceAsStream("test_flights.csv");
            if (is == null) {
                throw new IOException("cannot find document: test_flights.csv");
            }

            // 2.
            File tempFile = File.createTempFile("temp_flights", ".csv");
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                 BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    writer.write(line);
                    writer.newLine();
                }
            }

            // 3.
            List<Flight> flights = FlightFileManager.importFlights(tempFile.getAbsolutePath());

            // 4.
            System.out.println("=== plane imported ===");
            flights.forEach(System.out::println);

            // 5.
            flights.add(new Flight("TO999", "Lyon", "Berlin", "27-12", "08:30"));

            // 6.
            FlightFileManager.exportFlights(flights, "updated_flights.csv");
            System.out.println("plane data has updated！");

        } catch (IOException | ParseException e) {
            System.err.println("plane data has a problem: " + e.getMessage());
            e.printStackTrace();
        }
    }
}