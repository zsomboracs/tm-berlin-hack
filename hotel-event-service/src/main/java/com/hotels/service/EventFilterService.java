package com.hotels.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import com.ticketmaster.discovery.model.Event;
import com.ticketmaster.discovery.model.PriceRanges;
import com.ticketmaster.discovery.model.Venue;

@Component
public class EventFilterService {

    public Stream<Event> filter(Stream<Event> eventStream) {
        return eventStream.filter(e -> Optional.ofNullable(e.getAttractions()).map(List::size).orElse(0) == 1)
                .filter(e -> Optional.ofNullable(e.getVenues()).map(List::size).orElse(0) == 1)
                .filter(e -> e.getVenues().stream().findFirst().map(Venue::getLocation).map(Venue.Location::getLatitude).isPresent())
                .filter(e -> e.getVenues().stream().findFirst().map(Venue::getLocation).map(Venue.Location::getLongitude).isPresent())
                .filter(e -> Optional.ofNullable(e.getPriceRanges()).map(List::size).orElse(0) > 0)
                .filter(e -> e.getPriceRanges().stream().findFirst().map(PriceRanges::getMin).isPresent())
                .filter(e -> e.getPriceRanges().stream().findFirst().map(PriceRanges::getCurrency).isPresent());
    }
}
