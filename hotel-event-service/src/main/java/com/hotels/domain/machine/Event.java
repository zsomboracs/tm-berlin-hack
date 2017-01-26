package com.hotels.domain.machine;

public class Event {

    private String eventId;

    private String startDate;
    private String startTime;

    private String localStartDate;
    private String localStartTime;

    private String city;
    private String countryCode;
    private String country;
    private String stateCode;
    private String state;

    private String latitude;
    private String longitude;

    private String venueName;
    private String venueAddress;

    private String minPrice;
    private String maxPrice;

    private String url;

    public String getEventId() {
        return eventId;
    }

    public String getCity() {
        return city;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getCountry() {
        return country;
    }

    public String getStateCode() {
        return stateCode;
    }

    public String getState() {
        return state;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getVenueName() {
        return venueName;
    }

    public String getVenueAddress() {
        return venueAddress;
    }

    public String getMinPrice() {
        return minPrice;
    }

    public String getMaxPrice() {
        return maxPrice;
    }

    public String getUrl() {
        return url;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getLocalStartDate() {
        return localStartDate;
    }

    public String getLocalStartTime() {
        return localStartTime;
    }

    private Event(Builder builder) {
        eventId = builder.eventId;
        startDate = builder.startDate;
        startTime = builder.startTime;
        localStartDate = builder.localStartDate;
        localStartTime = builder.localStartTime;
        city = builder.city;
        countryCode = builder.countryCode;
        country = builder.country;
        stateCode = builder.stateCode;
        state = builder.state;
        latitude = builder.latitude;
        longitude = builder.longitude;
        venueName = builder.venueName;
        venueAddress = builder.venueAddress;
        minPrice = builder.minPrice;
        maxPrice = builder.maxPrice;
        url = builder.url;
    }

    public static final class Builder {
        private String eventId;
        private String startDate;
        private String startTime;
        private String localStartDate;
        private String localStartTime;
        private String city;
        private String countryCode;
        private String country;
        private String stateCode;
        private String state;
        private String latitude;
        private String longitude;
        private String venueName;
        private String venueAddress;
        private String minPrice;
        private String maxPrice;
        private String url;

        public Builder() {
        }

        public Builder withEventId(String val) {
            eventId = val;
            return this;
        }

        public Builder withStartDate(String val) {
            startDate = val;
            return this;
        }

        public Builder withStartTime(String val) {
            startTime = val;
            return this;
        }

        public Builder withLocalStartDate(String val) {
            localStartDate = val;
            return this;
        }

        public Builder withLocalStartTime(String val) {
            localStartTime = val;
            return this;
        }

        public Builder withCity(String val) {
            city = val;
            return this;
        }

        public Builder withCountryCode(String val) {
            countryCode = val;
            return this;
        }

        public Builder withCountry(String val) {
            country = val;
            return this;
        }

        public Builder withStateCode(String val) {
            stateCode = val;
            return this;
        }

        public Builder withState(String val) {
            state = val;
            return this;
        }

        public Builder withLatitude(String val) {
            latitude = val;
            return this;
        }

        public Builder withLongitude(String val) {
            longitude = val;
            return this;
        }

        public Builder withVenueName(String val) {
            venueName = val;
            return this;
        }

        public Builder withVenueAddress(String val) {
            venueAddress = val;
            return this;
        }

        public Builder withMinPrice(String val) {
            minPrice = val;
            return this;
        }

        public Builder withMaxPrice(String val) {
            maxPrice = val;
            return this;
        }

        public Builder withUrl(String val) {
            url = val;
            return this;
        }

        public Event build() {
            return new Event(this);
        }
    }
}
