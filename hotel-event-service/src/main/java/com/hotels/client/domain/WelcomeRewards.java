
package com.hotels.client.domain;

import java.util.HashMap;
import java.util.Map;

public class WelcomeRewards {

    private String info;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
