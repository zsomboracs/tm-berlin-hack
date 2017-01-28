
package com.hotels.client.domain;

import java.util.HashMap;
import java.util.Map;

public class Badging {

    private HotelBadge hotelBadge;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public HotelBadge getHotelBadge() {
        return hotelBadge;
    }

    public void setHotelBadge(HotelBadge hotelBadge) {
        this.hotelBadge = hotelBadge;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
