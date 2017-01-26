
package com.hotels.domain.international;

import java.util.HashMap;
import java.util.Map;

public class PriceRanges {

    private ExcludingTicketFees excludingTicketFees;
    private IncludingTicketFees includingTicketFees;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public ExcludingTicketFees getExcludingTicketFees() {
        return excludingTicketFees;
    }

    public void setExcludingTicketFees(ExcludingTicketFees excludingTicketFees) {
        this.excludingTicketFees = excludingTicketFees;
    }

    public IncludingTicketFees getIncludingTicketFees() {
        return includingTicketFees;
    }

    public void setIncludingTicketFees(IncludingTicketFees includingTicketFees) {
        this.includingTicketFees = includingTicketFees;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
