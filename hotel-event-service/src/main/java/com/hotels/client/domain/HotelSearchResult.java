
package com.hotels.client.domain;

import java.util.HashMap;
import java.util.Map;

public class HotelSearchResult {

    private Data data;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
