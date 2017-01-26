package com.hotels.web;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hotels.IdMapping;
import com.hotels.domain.machine.Event;
import com.ticketmaster.api.discovery.DiscoveryApi;
import com.ticketmaster.api.discovery.operation.SearchEventsOperation;
import com.ticketmaster.api.discovery.response.PagedResponse;
import com.ticketmaster.discovery.model.Date;
import com.ticketmaster.discovery.model.Events;
import com.ticketmaster.discovery.model.PriceRanges;
import com.ticketmaster.discovery.model.Venue;

@Controller
public class EventController {

    @Autowired
    private DiscoveryApi discoveryApi;

    @GetMapping("/events")
    @ResponseBody
    public List<Event> getEvents(@RequestParam(value = "artistId") String artistId) {

        PagedResponse<Events> page = null;
        try {
            page = discoveryApi.searchEvents(new SearchEventsOperation()
                    .attractionId(artistId)
                    .countryCode(IdMapping.US_COUNTRY_CODE)
                    .pageSize(IdMapping.MAX_PAGE_SIZE)
                    .classificationId(IdMapping.MUSIC_CATEGORY_ID));
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Event> result = page.getContent().getEvents().stream()
                .filter(e -> Optional.ofNullable(e.getVenues()).map(List::size).orElse(0) == 1)
                .filter(e -> e.getVenues().stream().findFirst().map(Venue::getLocation).map(Venue.Location::getLatitude).isPresent())
                .filter(e -> e.getVenues().stream().findFirst().map(Venue::getLocation).map(Venue.Location::getLongitude).isPresent())
                .filter(e -> Optional.ofNullable(e.getPriceRanges()).map(List::size).orElse(0) > 0)
                .filter(e -> e.getPriceRanges().stream().findFirst().map(PriceRanges::getMin).isPresent())
                .filter(e -> e.getPriceRanges().stream().findFirst().map(PriceRanges::getCurrency).isPresent())
                .map(this::getEvent)
                .collect(Collectors.toList());

        return result;
    }

    private Event getEvent(com.ticketmaster.discovery.model.Event tmEvent) {
        Optional<Venue> venue = Optional.ofNullable(tmEvent.getVenues().get(0));
        Optional<PriceRanges> priceRanges = Optional.ofNullable(tmEvent.getPriceRanges().get(0));
        return new Event.Builder()
                .withEventId(tmEvent.getId())
                .withCity(venue.map(Venue::getCity).map(Venue.City::getName).orElse(null))
                .withCountry(venue.map(Venue::getCountry).map(Venue.Country::getName).orElse(null))
                .withCountryCode(venue.map(Venue::getCountry).map(Venue.Country::getCountryCode).orElse(null))
                .withState(venue.map(Venue::getState).map(Venue.State::getName).orElse(null))
                .withStateCode(venue.map(Venue::getState).map(Venue.State::getStateCode).orElse(null))
                .withLatitude(venue.map(Venue::getLocation).map(Venue.Location::getLatitude).orElse(null))
                .withLongitude(venue.map(Venue::getLocation).map(Venue.Location::getLongitude).orElse(null))
                .withMinPrice(priceRanges.map(pr -> getPrice(pr.getMin(), pr.getCurrency())).orElse(null))
                .withMaxPrice(priceRanges.map(pr -> getPrice(pr.getMax(), pr.getCurrency())).orElse(null))
                .withVenueName(venue.map(Venue::getName).orElse(null))
                .withVenueAddress(venue.map(Venue::getAddress).map(Venue.Address::getLine1).orElse(null))
                .withUrl(tmEvent.getUrl())
                .withStartDate(Optional.ofNullable(tmEvent.getDates()).map(Date::getStart).map(Date.Start::getLocalDate).orElse(null))
                .withStartTime(Optional.ofNullable(tmEvent.getDates()).map(Date::getStart).map(Date.Start::getLocalTime).orElse(null))
                .build();
    }

    private String getPrice(Double price, String currency) {
        if (currency.equals("USD")) {
            return String.format("$%.1f", price);
        }
        return String.format("%.1f %s", price, currency);
    }

}
