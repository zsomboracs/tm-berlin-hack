
package com.hotels.domain.international;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Event {

    private String id;
    private String domainId;
    private String name;
    private String url;
    private Boolean externalUrl;
    private Eventdate eventdate;
    private String dayOfWeek;
    private String timezone;
    private String localeventdate;
    private List<Image> images = null;
    private Onsale onsale;
    private Offsale offsale;
    private Properties properties;
    private Venue venue;
    private List<Category> categories = null;
    private List<Attraction> attractions = null;
    private PriceRanges priceRanges;
    private String currency;
    private Dooropening dooropening;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDomainId() {
        return domainId;
    }

    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getExternalUrl() {
        return externalUrl;
    }

    public void setExternalUrl(Boolean externalUrl) {
        this.externalUrl = externalUrl;
    }

    public Eventdate getEventdate() {
        return eventdate;
    }

    public void setEventdate(Eventdate eventdate) {
        this.eventdate = eventdate;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getLocaleventdate() {
        return localeventdate;
    }

    public void setLocaleventdate(String localeventdate) {
        this.localeventdate = localeventdate;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public Onsale getOnsale() {
        return onsale;
    }

    public void setOnsale(Onsale onsale) {
        this.onsale = onsale;
    }

    public Offsale getOffsale() {
        return offsale;
    }

    public void setOffsale(Offsale offsale) {
        this.offsale = offsale;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public List<Attraction> getAttractions() {
        return attractions;
    }

    public void setAttractions(List<Attraction> attractions) {
        this.attractions = attractions;
    }

    public PriceRanges getPriceRanges() {
        return priceRanges;
    }

    public void setPriceRanges(PriceRanges priceRanges) {
        this.priceRanges = priceRanges;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Dooropening getDooropening() {
        return dooropening;
    }

    public void setDooropening(Dooropening dooropening) {
        this.dooropening = dooropening;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
