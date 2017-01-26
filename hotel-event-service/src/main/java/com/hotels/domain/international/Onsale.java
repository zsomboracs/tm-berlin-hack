
package com.hotels.domain.international;

import java.util.HashMap;
import java.util.Map;

public class Onsale {

    private String format;
    private String value;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
