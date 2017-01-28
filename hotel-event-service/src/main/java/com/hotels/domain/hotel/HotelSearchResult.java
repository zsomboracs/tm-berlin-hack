package com.hotels.domain.hotel;

import java.util.List;

/**
 * Created by Peter_Kedvessy on 1/25/2017.
 */
public class HotelSearchResult {

    private String destination;
    private List<Hotel> result;

    public HotelSearchResult() {

    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public List<Hotel> getResult() {
        return result;
    }

    public void setResult(List<Hotel> result) {
        this.result = result;
    }
}
