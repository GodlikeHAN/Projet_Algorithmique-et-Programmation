package org.example;

import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class FlightFileManagerTest {
    private static final String TEST_CSV = "src/test/resources/test_flights.csv";
    private static final String SAMPLE_CSV =
            "Code,Dep,Arriv,Date,Hour\n" +
                    "TO350,Paris,Rome,25-12,10:15\n" +
                    "TO761,Lyon,Progu,25-12,12:25\n" +
                    "TO235,Paris,Nice,25-12,12:25";

    @Test
    void testImportExport() throws IOException, ParseException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TEST_CSV))) {
            writer.write(SAMPLE_CSV);
        }


        List<Flight> flights = FlightFileManager.importFlights(TEST_CSV);
        assertEquals(3, flights.size());
        assertEquals("TO350", flights.get(0).getCode());
        assertEquals("Paris", flights.get(0).getDeparture());


        String exportPath = "src/test/resources/export_flights.csv";
        FlightFileManager.exportFlights(flights, exportPath);


        List<Flight> exportedFlights = FlightFileManager.importFlights(exportPath);
        assertEquals(flights.size(), exportedFlights.size());
        assertEquals(flights.get(0).toString(), exportedFlights.get(0).toString());
    }
}