package com.hotels.domain.machine;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class FullArtistData implements Serializable {

    private Artist artist;
    private List<LocalDateTime> eventDates;

    private FullArtistData(Builder builder) {
        artist = builder.artist;
        eventDates = builder.eventDates;
    }

    public Artist getArtist() {
        return artist;
    }

    public List<LocalDateTime> getEventDates() {
        return eventDates;
    }

    public static final class Builder {
        private Artist artist;
        private List<LocalDateTime> eventDates;

        public Builder() {
        }

        public Builder withSummaryArtistData(Artist val) {
            artist = val;
            return this;
        }

        public Builder withEventDates(List<LocalDateTime> val) {
            eventDates = val;
            return this;
        }

        public FullArtistData build() {
            return new FullArtistData(this);
        }
    }
}
