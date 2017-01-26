
package com.hotels.domain.international;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Response {

    private List<Event> events = null;
    private Pagination pagination;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
