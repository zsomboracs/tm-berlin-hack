
package com.hotels.client.domain;

import java.util.HashMap;
import java.util.Map;

public class Messaging {

    private String popularity;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
