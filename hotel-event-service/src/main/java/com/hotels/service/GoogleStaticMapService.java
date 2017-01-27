package com.hotels.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class GoogleStaticMapService {

    @Value("${GOOGLE_MAPS_API_KEY}")
    private String apiKey;

    @Value("${GOOGLE_MAPS_API}")
    private String apiUrl;

    public String getImageUrl(String city, String state) {
        return UriComponentsBuilder
            .fromUriString(apiUrl)
            .queryParam("key", apiKey)
            .queryParam("zoom", "8")
            .queryParam("center", getCenter(city, state))
            .queryParam("size", "300x200")
            .toUriString();
    }

    private String getCenter(String city, String state) {
        return city + (state == null ? "" : (", " + state));
    }
}
