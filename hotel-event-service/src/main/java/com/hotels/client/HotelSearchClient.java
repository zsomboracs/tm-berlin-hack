package com.hotels.client;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import javax.inject.Inject;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import com.hotels.client.domain.HotelSearchResult;

@Repository
public class HotelSearchClient {

    private static final String ISO_DATE_PATTERN = "yyyy-MM-dd";
    private static final DateTimeFormatter ISO_DATE_FORMATTER = DateTimeFormatter.ofPattern(ISO_DATE_PATTERN);

    @Inject
    private Gson gson;

    @Inject
    private CloseableHttpClient httpClient;

    public Optional<HotelSearchResult> getSearchResult(String coordinates, String destination, LocalDate checkIn, LocalDate checkOut, int adults) throws IOException {
        CloseableHttpResponse httpResponse = null;
        HotelSearchResult result = null;
        try {
            HttpGet getRequest = new HttpGet("https://www.hotels.com/search/listings.json?pos=HCOM_US&resolved-location="
                + URLEncoder.encode(destination, "UTF-8")
                + "&q-destination="
                + coordinates
                + "&q-check-in="
                + ISO_DATE_FORMATTER.format(checkIn)
                + "&q-check-out="
                + ISO_DATE_FORMATTER.format(checkOut)
                + "&q-rooms=1&q-room-0-adults="
                + adults
                + "&q-room-0-children=0"
                + "&sort-order=DISTANCE_FROM_LANDMARK"
                + "&pn=1"
                + "&f-star-rating=5,4,3"
                + "&start-index=0"
                + "&pageSize=50");

            httpResponse = httpClient.execute(getRequest);
            JsonReader jsonReader = new JsonReader(new InputStreamReader(httpResponse.getEntity().getContent()));
            result = gson.fromJson(jsonReader, HotelSearchResult.class);
        } catch (Exception ex) {
            //TODO log error
        } finally {
            if (httpResponse != null) {
                try {
                    httpResponse.close();
                } catch (Exception ex) {
                    //TODO log error
                }
            }
        }
        return Optional.ofNullable(result);
    }
}
