package org.example;

import okhttp3.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class FlightSearch {
    private static final String API_KEY = "YOUR_API_KEY";
    private static final String API_URL = "https://test.api.amadeus.com/v2/shopping/flight-offers";

    public static List<Vol> searchFlights(String origin, String destination, String date) throws IOException {
        OkHttpClient client = new OkHttpClient();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(API_URL).newBuilder();
        urlBuilder.addQueryParameter("originLocationCode", origin);
        urlBuilder.addQueryParameter("destinationLocationCode", destination);
        urlBuilder.addQueryParameter("departureDate", date);

        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .addHeader("Authorization", "Bearer " + API_KEY)
                .build();

        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            String jsonResponse = response.body().string();
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> data = mapper.readValue(jsonResponse, Map.class);
            return new ArrayList<>();
        }
        return null;
    }
}