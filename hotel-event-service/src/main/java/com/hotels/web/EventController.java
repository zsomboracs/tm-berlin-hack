package com.hotels.web;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hotels.domain.machine.Event;
import com.hotels.service.EventFilterService;
import com.hotels.service.GoogleCustomSearchService;
import com.hotels.service.GoogleStaticMapService;
import com.hotels.service.SearchOperationProvider;
import com.ticketmaster.api.discovery.DiscoveryApi;
import com.ticketmaster.api.discovery.response.PagedResponse;
import com.ticketmaster.discovery.model.Date;
import com.ticketmaster.discovery.model.Events;
import com.ticketmaster.discovery.model.PriceRanges;
import com.ticketmaster.discovery.model.Venue;

@Controller
public class EventController {

    @Autowired
    private DiscoveryApi discoveryApi;
    @Autowired
    private SearchOperationProvider searchOperationProvider;
    @Autowired
    private EventFilterService eventFilterService;
    @Autowired
    private GoogleCustomSearchService googleCustomSearchService;
    @Autowired
    private GoogleStaticMapService googleStaticMapService;

    @GetMapping("/events")
    @ResponseBody
    public List<Event> getEvents(@RequestParam(value = "artistId") String artistId,
            @RequestParam(value = "startDate") String startDate,
            @RequestParam(value = "endDate") String endDate) throws IOException {

        PagedResponse<Events> page = discoveryApi
                .searchEvents(searchOperationProvider.getBaseOperation().attractionId(artistId)
                        .startDateTime(startDate + "T00:00:00Z")
                        .endDateTime(endDate + "T00:00:00Z"));

        List<Event> result = eventFilterService
                .filter(Optional.ofNullable(page).map(PagedResponse<Events>::getContent).map(Events::getEvents).orElse(Collections.emptyList()).stream())
                .map(this::getEvent)
                .collect(Collectors.toList());

        return result;
    }

    private Event getEvent(com.ticketmaster.discovery.model.Event tmEvent) {
        Optional<Venue> venue = Optional.ofNullable(tmEvent.getVenues().get(0));
        Optional<PriceRanges> priceRanges = Optional.ofNullable(tmEvent.getPriceRanges().get(0));

        DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormat.forPattern("HH:mm:ss");

        return new Event.Builder()
                .withEventId(tmEvent.getId())
                .withCity(venue.map(Venue::getCity).map(Venue.City::getName).orElse(null))
                //.withCityImageUrl(googleCustomSearchService.getImageUrl(venue.map(Venue::getCity).map(Venue.City::getName).orElse(null)))
                .withCityImageUrl(googleStaticMapService.getImageUrl(venue.map(Venue::getCity).map(Venue.City::getName).orElse(null),
                    venue.map(Venue::getState).map(Venue.State::getStateCode).orElse(null)))
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
                .withStartDate(Optional.ofNullable(tmEvent.getDates()).map(Date::getStart).map(Date.Start::getDateTime).map(d -> dateFormatter.print(d)).orElse(null))
                .withStartTime(Optional.ofNullable(tmEvent.getDates()).map(Date::getStart).map(Date.Start::getDateTime).map(d -> timeFormatter.print(d)).orElse(null))
                .withLocalStartDate(Optional.ofNullable(tmEvent.getDates()).map(Date::getStart).map(Date.Start::getLocalDate).orElse(null))
                .withLocalStartTime(Optional.ofNullable(tmEvent.getDates()).map(Date::getStart).map(Date.Start::getLocalTime).orElse(null))
                .build();
    }

    private String getPrice(Double price, String currency) {
        return String.format("%.1f", price);
    }

}
