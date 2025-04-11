package org.example;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class FlightSearchTest {
    private MockWebServer mockWebServer;
    private RechercheVol flightSearch;

    @BeforeEach
    void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
        RechercheVol.API_URL = mockWebServer.url("/v2/shopping/flight-offers").toString();
        flightSearch = new RechercheVol();
    }

    @AfterEach
    void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    void testSearchFlights_Success() throws IOException {
        String mockResponse = """
            {
                "data": [{
                    "itineraries": [{
                        "segments": [{
                            "departure": {"iataCode": "JFK", "at": "2024-12-25T10:00:00"},
                            "arrival": {"iataCode": "LAX", "at": "2024-12-25T13:00:00"}
                        }]
                    }]
                }]
            }
            """;

        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(mockResponse)
                .addHeader("Content-Type", "application/json"));

        List<Vol> result = flightSearch.rechercheVol("JFK", "LAX", "2024-12-25");

        assertFalse(result.isEmpty());
        Vol firstFlight = result.get(0);

        assertEquals("JFK", firstFlight.getPrimaryOrigin());
        assertEquals("LAX", firstFlight.getPrimaryDestination());

        assertEquals("JFK", firstFlight.getOrigine()[0]);
        assertEquals("LAX", firstFlight.getDestination()[0]);
    }
    
}