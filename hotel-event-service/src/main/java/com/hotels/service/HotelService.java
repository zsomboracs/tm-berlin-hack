package com.hotels.service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.hotels.api.Hotel;
import com.hotels.api.HotelSearchResult;
import com.hotels.client.HotelSearchClient;
import com.hotels.client.domain.Address;
import com.hotels.client.domain.Body;
import com.hotels.client.domain.Data;
import com.hotels.client.domain.Result;
import com.hotels.client.domain.SearchResults;
import com.hotels.web.HotelSearchForm;

/**
 * Created by Peter_Kedvessy on 1/25/2017.
 */
@Service
public class HotelService {

    private static final String FALLBACK_DESTINATION = "New York";
    private static final int MIN_ADUlTS = 1;
    private static final int MAX_ADUlTS = 8;

    @Inject
    private HotelSearchClient client;
    @Inject
    private DestinationService destinationService;

    public HotelSearchResult search(HotelSearchForm form) throws IOException {
        Optional<String> resolvedDestination = destinationService.resolveDestination(form.getLatitude(), form.getLongitude());
        Optional<String> fallbackDestination = Optional.ofNullable(form.getDestination());
        String destination = resolvedDestination.orElse(fallbackDestination.orElse(FALLBACK_DESTINATION));
        LocalDate checkIn = getValidCheckIn(form.getCheckIn(), LocalDate.now());
        LocalDate checkOut = getValidCheckOut(form.getCheckOut(), checkIn);
        String coordinates = getCoordinates(form.getLatitude(), form.getLongitude());
        Optional<com.hotels.client.domain.HotelSearchResult> searchResult
            = client.getSearchResult(coordinates, destination, checkIn, checkOut, getValidAdults(form.getAdults()));
        List<Result> results = searchResult.map(com.hotels.client.domain.HotelSearchResult::getData)
            .map(Data::getBody)
            .map(Body::getSearchResults)
            .map(SearchResults::getResults)
            .orElse(Collections.emptyList());

        return new HotelSearchResult(destination, results.stream().map(this::createHotel).filter(Objects::nonNull).collect(Collectors.toList()));
    }

    private Hotel createHotel(Result result) {
        try {
            Hotel hotel = new Hotel();
            hotel.setName(result.getName());
            hotel.setImage(result.getThumbnailUrl().replace("https", "http").replace("_l.jpg", "_b.jpg"));
            hotel.setPrice(result.getRatePlan().getPrice().getCurrent());
            hotel.setUrl("https://www.hotels.com" + result.getUrls().getPdpDescription() + "&pos=HCOM_US#rooms-and-rates-anchor");
            hotel.setStarRating(result.getStarRating());
            hotel.setGuestRating(result.getGuestReviews().getRating());
            hotel.setTripAdvisorRating(result.getTripAdvisorGuestReviews().getRating());
            hotel.setPriceAmount(result.getRatePlan().getPrice().getExactCurrent());
            hotel.setAddress(getAddress(result));
            return hotel;
        } catch (Exception ex) {
            return null;
        }
    }

    private int getValidAdults(int adults) {
        return Math.min(Math.max(adults, MIN_ADUlTS), MAX_ADUlTS);
    }

    private LocalDate getValidCheckIn(LocalDate checkIn, LocalDate today) {
        if (checkIn == null || checkIn.isBefore(today)) {
            return today;
        }
        return checkIn;
    }

    private LocalDate getValidCheckOut(LocalDate checkOut, LocalDate checkIn) {
        if (checkOut == null) {
            return checkIn.plus(1, ChronoUnit.DAYS);
        }
        return checkOut;
    }

    private String getCoordinates(double latitude, double longitude) {
        return Double.toString(latitude) + "+" + Double.toString(longitude);
    }

    private String getAddress(Result result) {
        List<String> addressElements = new ArrayList<>();
        Optional<Address> address = Optional.ofNullable(result.getAddress());
        address.map(Address::getStreetAddress).ifPresent(addressElements::add);
        address.map(Address::getLocality).ifPresent(addressElements::add);
        address.map(Address::getRegion).ifPresent(addressElements::add);
        address.map(Address::getCountryName).ifPresent(addressElements::add);

        return String.join(", ", addressElements);
    }
}
