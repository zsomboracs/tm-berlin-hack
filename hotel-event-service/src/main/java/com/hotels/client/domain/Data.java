
package com.hotels.client.domain;

import java.util.HashMap;
import java.util.Map;

public class Data {

    private Body body;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
