
package com.hotels.client.domain;

import java.util.HashMap;
import java.util.Map;

public class TripAdvisorGuestReviews {

    private Double rating;
    private Integer total;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
