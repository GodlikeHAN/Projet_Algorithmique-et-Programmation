package org.example;

import okhttp3.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class RechercheVol {
    private static final String API_KEY = "YOUR_API_KEY";
    public static String API_URL = "https://test.api.amadeus.com/v2/shopping/flight-offers";

    public static List<Vol> rechercheVol(String origin, String destination, String date) throws IOException {
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

        if (!response.isSuccessful()) {
            throw new IOException("API error: " + response.code());
        }
        String jsonResponse = response.body().string();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> rootMap = mapper.readValue(jsonResponse, Map.class);
        List<Map<String, Object>> dataList = (List<Map<String, Object>>) rootMap.get("data");
        List<Vol> result = new ArrayList<>();

        if (dataList != null) {
            for (Map<String, Object> data : dataList) {
                List<Map<String, Object>> itineraries = (List<Map<String, Object>>) data.get("itineraries");
                if (itineraries == null) {
                    continue;
                }
                for (Map<String, Object> itinerary : itineraries) {
                    List<Map<String, Object>> segments = (List<Map<String, Object>>) itinerary.get("segments");
                    if (segments == null) {
                        continue;
                    }
                    for (Map<String, Object> segment : segments) {
                        Map<String, Object> departure = (Map<String, Object>) segment.get("departure");
                        Map<String, Object> arrival = (Map<String, Object>) segment.get("arrival");

                        if (departure == null || arrival == null) {
                            continue;
                        }

                        String originCode = (String) departure.get("iataCode");
                        String departureAt = (String) departure.get("at");

                        String destCode = (String) arrival.get("iataCode");
                        String arrivalAt = (String) arrival.get("at");

                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                        Date depTime = null;
                        Date arrTime = null;
                        try {
                            if (departureAt != null) {
                                depTime = sdf.parse(departureAt);
                            }
                            if (arrivalAt != null) {
                                arrTime = sdf.parse(arrivalAt);
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        Vol vol = new Vol("MockVolNum", originCode, destCode, depTime, arrTime);
                        result.add(vol);
                    }
                }
            }
        }
        return result;
    }
}