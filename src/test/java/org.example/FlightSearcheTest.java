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
    private FlightSearch flightSearch;

    @BeforeEach
    void setUp() throws IOException {

        mockWebServer = new MockWebServer();
        mockWebServer.start();


        FlightSearch.API_URL = mockWebServer.url("/v2/shopping/flight-offers").toString();
        flightSearch = new FlightSearch();
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


        List<Vol> result = flightSearch.searchFlights("JFK", "LAX", "2024-12-25");


        assertFalse(result.isEmpty());
        Vol firstFlight = result.get(0);
        assertNotNull(firstFlight.getOrigine());
        assertNotNull(firstFlight.getDestination());
        assertEquals("JFK", firstFlight.getOrigine());
        assertEquals("LAX", firstFlight.getDestination());
    }

    @Test
    void testSearchFlights_Failure() {

        mockWebServer.enqueue(new MockResponse().setResponseCode(401));


        assertThrows(IOException.class, () -> {
            flightSearch.searchFlights("INVALID", "CODE", "2024-12-25");
        });
    }
}