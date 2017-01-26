package com.hotels.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class InternationalTicketMasterApiService {

    @Value("${API_KEY}")
    private String apikey;
    @Value("${INTERNATIONAL_API_URL}")
    private String apiUrl;

    private UriComponentsBuilder baseBuilder;

    @PostConstruct
    private void init() {
        baseBuilder = UriComponentsBuilder
                .fromUriString(apiUrl)
                .queryParam("apikey", apikey);
    }

    public String getEventsApiUrl() {
        return baseBuilder
                .path("events")
                .queryParam("sort_by", "popularity")
                .queryParam("category_ids", "10001")
                .queryParam("rows", "500")
                .build()
                .toUriString();
    }

    public String getAttractionEventsApiUrl(Integer attractionId) {
        return baseBuilder
                .path("events")
                .queryParam("sort_by", "popularity")
                .queryParam("category_ids", "10001")
                .queryParam("attraction_ids", attractionId)
                .queryParam("rows", "500")
                .build()
                .toUriString();
    }

    public String getAttractionsApiUrl(List<Integer> attractionIds) {
        return baseBuilder
                .path("attractions")
                .queryParam("attraction_ids", attractionIds)
                .queryParam("category_ids", "10001")
                .queryParam("rows", "500")
                .build()
                .toUriString();
    }

}
