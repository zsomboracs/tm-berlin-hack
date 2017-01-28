
package com.hotels.client.domain;

import java.util.HashMap;
import java.util.Map;

public class GuestReviews {

    private Double rating;
    private Integer total;
    private String totalText;
    private Integer scale;
    private String badge;
    private String badgeText;
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

    public String getTotalText() {
        return totalText;
    }

    public void setTotalText(String totalText) {
        this.totalText = totalText;
    }

    public Integer getScale() {
        return scale;
    }

    public void setScale(Integer scale) {
        this.scale = scale;
    }

    public String getBadge() {
        return badge;
    }

    public void setBadge(String badge) {
        this.badge = badge;
    }

    public String getBadgeText() {
        return badgeText;
    }

    public void setBadgeText(String badgeText) {
        this.badgeText = badgeText;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
