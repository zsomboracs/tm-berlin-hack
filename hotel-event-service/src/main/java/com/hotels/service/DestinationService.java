package com.hotels.service;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.hotels.client.ResolveDestinationClient;

/**
 * Created by Peter_Kedvessy on 1/25/2017.
 */
@Service
public class DestinationService {

    private static final Pattern DESTINATION_PATTERN = Pattern.compile("type=\"radio\" value=\"(GEO_LOCATION.+?)\"");
    private static final int DESTINATION_PATTERN_DESTINATION_GROUP = 1;

    @Inject
    private ResolveDestinationClient client;

    public Optional<String> resolveDestination(double latitude, double longitude) {
        Optional<String> resolvedDestinationBody = client.getResolveDestinationBody(getCoordinatesAsString(latitude, longitude));
        Optional<String> resolvedDestination = Optional.empty();
        try {
            resolvedDestination = resolvedDestinationBody
                .map(DESTINATION_PATTERN::matcher)
                .filter(Matcher::find)
                .map(m -> m.group(DESTINATION_PATTERN_DESTINATION_GROUP))
                .map(d -> d.replace("&#x3a;", ":").replace("&#x7c;", "|"));
        } catch (Exception ex) {
            //TODO log error
        }
        return resolvedDestination;
    }

    private String getCoordinatesAsString(double latitude, double longitude) {
        return Double.toString(latitude) + "," + Double.toString(longitude);
    }

}
