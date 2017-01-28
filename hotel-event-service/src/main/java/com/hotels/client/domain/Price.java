
package com.hotels.client.domain;

import java.util.HashMap;
import java.util.Map;

public class Price {

    private String current;
    private Double exactCurrent;
    private String info;
    private String summary;
    private Boolean fromPrice;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public Double getExactCurrent() {
        return exactCurrent;
    }

    public void setExactCurrent(Double exactCurrent) {
        this.exactCurrent = exactCurrent;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Boolean getFromPrice() {
        return fromPrice;
    }

    public void setFromPrice(Boolean fromPrice) {
        this.fromPrice = fromPrice;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
