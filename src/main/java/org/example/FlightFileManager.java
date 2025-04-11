package org.example;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class FlightFileManager {
    private static final String CSV_HEADER = "Code,Dep,Arriv,Date,Hour";
    private static final String CSV_DELIMITER = ",";

    public static List<Flight> importFlights(String filePath) throws IOException, ParseException {
        List<Flight> flights = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            reader.readLine();

            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(CSV_DELIMITER);
                if (data.length == 5) {
                    flights.add(new Flight(
                            data[0].trim(),
                            data[1].trim(),
                            data[2].trim(),
                            data[3].trim(),
                            data[4].trim()
                    ));
                }
            }
        }
        return flights;
    }


    public static void exportFlights(List<Flight> flights, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(CSV_HEADER);
            writer.newLine();

            for (Flight flight : flights) {
                writer.write(String.join(CSV_DELIMITER,
                        flight.getCode(),
                        flight.getDeparture(),
                        flight.getArrival(),
                        new SimpleDateFormat("dd-MM").format(flight.getDate()),
                        flight.getHour()
                ));
                writer.newLine();
            }
        }
    }
}