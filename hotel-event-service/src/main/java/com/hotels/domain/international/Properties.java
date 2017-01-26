
package com.hotels.domain.international;

import java.util.HashMap;
import java.util.Map;

public class Properties {

    private Boolean cancelled;
    private Boolean rescheduled;
    private Boolean seatsAvail;
    private Boolean soldOut;
    private Boolean _package;
    private String system;
    private Boolean canceled;
    private Integer minimumAgeRequired;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Boolean getCancelled() {
        return cancelled;
    }

    public void setCancelled(Boolean cancelled) {
        this.cancelled = cancelled;
    }

    public Boolean getRescheduled() {
        return rescheduled;
    }

    public void setRescheduled(Boolean rescheduled) {
        this.rescheduled = rescheduled;
    }

    public Boolean getSeatsAvail() {
        return seatsAvail;
    }

    public void setSeatsAvail(Boolean seatsAvail) {
        this.seatsAvail = seatsAvail;
    }

    public Boolean getSoldOut() {
        return soldOut;
    }

    public void setSoldOut(Boolean soldOut) {
        this.soldOut = soldOut;
    }

    public Boolean getPackage() {
        return _package;
    }

    public void setPackage(Boolean _package) {
        this._package = _package;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public Boolean getCanceled() {
        return canceled;
    }

    public void setCanceled(Boolean canceled) {
        this.canceled = canceled;
    }

    public Integer getMinimumAgeRequired() {
        return minimumAgeRequired;
    }

    public void setMinimumAgeRequired(Integer minimumAgeRequired) {
        this.minimumAgeRequired = minimumAgeRequired;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
