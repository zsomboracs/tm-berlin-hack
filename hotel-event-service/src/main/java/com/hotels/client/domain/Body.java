
package com.hotels.client.domain;

import java.util.HashMap;
import java.util.Map;

public class Body {

    private String header;
    private Query query;
    private SearchResults searchResults;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    public SearchResults getSearchResults() {
        return searchResults;
    }

    public void setSearchResults(SearchResults searchResults) {
        this.searchResults = searchResults;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
