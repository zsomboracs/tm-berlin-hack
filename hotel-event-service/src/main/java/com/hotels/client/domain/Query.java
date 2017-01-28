
package com.hotels.client.domain;

import java.util.HashMap;
import java.util.Map;

public class Query {

    private Destination destination;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
