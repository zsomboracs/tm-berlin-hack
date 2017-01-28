
package com.hotels.client.domain;

import java.util.HashMap;
import java.util.Map;

public class RatePlan {

    private Price price;
    private Features features;
    private String type;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public Features getFeatures() {
        return features;
    }

    public void setFeatures(Features features) {
        this.features = features;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
