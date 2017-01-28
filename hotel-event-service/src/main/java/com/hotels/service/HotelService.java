package com.hotels.service;

import java.io.IOException;

import org.omg.CORBA.Object;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.hotels.domain.hotel.HotelSearchResult;
import com.hotels.web.HotelSearchForm;

/**
 * Created by Peter_Kedvessy on 1/25/2017.
 */
@Service
public class HotelService {

    @Autowired
    private RestTemplate restTemplateHotel;
    @Autowired
    private HttpEntity httpEntity;

    @Value("${TICKETMACHINE_API}")
    private String apiUrl;

    public HotelSearchResult search(HotelSearchForm form) throws IOException {
        HttpEntity<HotelSearchResult> response = restTemplateHotel.exchange(getUrl(form), HttpMethod.GET, httpEntity, HotelSearchResult.class);
        return response.getBody();
    }

    private String getUrl(HotelSearchForm form) {
        return UriComponentsBuilder
            .fromUriString(apiUrl)
            .queryParam("adults", form.getAdults())
            .queryParam("checkIn", form.getCheckIn())
            .queryParam("checkOut", form.getCheckOut())
            .queryParam("destination", form.getDestination())
            .queryParam("latitude", form.getLatitude())
            .queryParam("longitude", form.getLongitude())
            .toUriString();
    }
}
