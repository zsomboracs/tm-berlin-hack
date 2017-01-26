
package com.hotels.domain.international;

import java.util.HashMap;
import java.util.Map;

public class IncludingTicketFees {

    private Double min;
    private Double max;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
