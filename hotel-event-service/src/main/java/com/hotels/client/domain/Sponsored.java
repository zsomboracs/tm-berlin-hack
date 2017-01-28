
package com.hotels.client.domain;

import java.util.HashMap;
import java.util.Map;

public class Sponsored {

    private String impressionTrackingUrl;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getImpressionTrackingUrl() {
        return impressionTrackingUrl;
    }

    public void setImpressionTrackingUrl(String impressionTrackingUrl) {
        this.impressionTrackingUrl = impressionTrackingUrl;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
