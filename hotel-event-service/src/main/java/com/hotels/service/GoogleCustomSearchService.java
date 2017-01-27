package com.hotels.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.hotels.domain.google.customsearch.image.Item;
import com.hotels.domain.google.customsearch.image.Response;

@Component
public class GoogleCustomSearchService {

    private static final double IMAGE_SIZE_RATION = 300.0d / 200.0d;

    @Value("${GOOGLE_CUSTOM_SEARCH_API_URL}")
    private String apiUrl;
    @Value("${GOOGLE_API_KEY}")
    private String apiKey;
    @Value("${GOOGLE_CSE_KEY}")
    private String apiCseKey;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private HttpEntity httpEntity;

    private Map<String, String> cache = new ConcurrentHashMap<>();

    private String getDestinationImageSearchUrl(String destination) {
        return UriComponentsBuilder
            .fromUriString(apiUrl)
            .queryParam("key", apiKey)
            .queryParam("cx", apiCseKey)
            .queryParam("searchType", "image")
            .queryParam("fileType", "jpg")
            .queryParam("imgSize", "medium")
            .queryParam("alt", "json")
            .queryParam("q", destination)
            .queryParam("imgtype", "photo")
            .toUriString();
    }

    public String getImageUrl(String destination, boolean updateCache) {
        try {
            return destination == null ? null :
                (updateCache ? cache.computeIfPresent(destination, (k, v) -> getImageUrlFromApi(k)) : cache.computeIfAbsent(destination, this::getImageUrlFromApi));
        } catch (Exception ex) {
            //TODO log excetpion
            return null;
        }
    }

    private String getImageUrlFromApi(String destination) {
        HttpEntity<Response> response = restTemplate.exchange(getDestinationImageSearchUrl(destination), HttpMethod.GET, httpEntity, Response.class);

        Optional<String> firstImageUrl = Optional.ofNullable(response.getBody().getItems()).orElse(Collections.emptyList())
            .stream()
            .findFirst()
            .map(Item::getLink);

        return firstImageUrl.orElse(null);
    }
}
