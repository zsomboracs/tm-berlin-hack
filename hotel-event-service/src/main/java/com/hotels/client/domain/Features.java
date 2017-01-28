
package com.hotels.client.domain;

import java.util.HashMap;
import java.util.Map;

public class Features {

    private Boolean paymentPreference;
    private Boolean freeCancellation;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Boolean getPaymentPreference() {
        return paymentPreference;
    }

    public void setPaymentPreference(Boolean paymentPreference) {
        this.paymentPreference = paymentPreference;
    }

    public Boolean getFreeCancellation() {
        return freeCancellation;
    }

    public void setFreeCancellation(Boolean freeCancellation) {
        this.freeCancellation = freeCancellation;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
