package org.example;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FlightFileManagerTest {

    private static final String SAMPLE_CSV =
            "Code,Dep,Arriv,Date,Hour\n" +
                    "TO350,Paris,Rome,25-12,10:15\n" +
                    "TO761,Lyon,Progu,25-12,12:25\n" +
                    "TO235,Paris,Nice,25-12,12:25";

    @Test
    void testImportExport() throws IOException, ParseException {
        // 1. CSV
        Path tempInput = Path.of("target/test_flights.csv");
        Files.createDirectories(tempInput.getParent());
        Files.writeString(tempInput, SAMPLE_CSV);

        // 2. importFlights()
        List<Flight> flights = FlightFileManager.importFlights(tempInput.toString());
        assertEquals(3, flights.size());
        assertEquals("TO350", flights.get(0).getCode());
        assertEquals("Paris", flights.get(0).getDeparture());

        // 3. exportFlights()
        Path exportPath = Path.of("target/export_flights.csv");
        FlightFileManager.exportFlights(flights, exportPath.toString());

        // 4.
        List<Flight> exportedFlights = FlightFileManager.importFlights(exportPath.toString());
        assertEquals(flights.size(), exportedFlights.size());
        assertEquals(flights.get(0).toString(), exportedFlights.get(0).toString());
    }
}
