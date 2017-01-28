
package com.hotels.client.domain;

import java.util.HashMap;
import java.util.Map;

public class Urls {

    private String pdpDescription;
    private String pdpMap;
    private String pdpReviews;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getPdpDescription() {
        return pdpDescription;
    }

    public void setPdpDescription(String pdpDescription) {
        this.pdpDescription = pdpDescription;
    }

    public String getPdpMap() {
        return pdpMap;
    }

    public void setPdpMap(String pdpMap) {
        this.pdpMap = pdpMap;
    }

    public String getPdpReviews() {
        return pdpReviews;
    }

    public void setPdpReviews(String pdpReviews) {
        this.pdpReviews = pdpReviews;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
