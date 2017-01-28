package com.hotels.api;

import java.util.List;

/**
 * Created by Peter_Kedvessy on 1/25/2017.
 */
public class HotelSearchResult {

    private String destination;
    private List<Hotel> result;

    public HotelSearchResult(String destination, List<Hotel> result) {
        this.destination = destination;
        this.result = result;
    }

    public String getDestination() {
        return destination;
    }

    public List<Hotel> getResult() {
        return result;
    }
}
