package com.hotels.web;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ticketmaster.api.discovery.DiscoveryApi;
import com.ticketmaster.api.discovery.operation.SearchEventsOperation;
import com.ticketmaster.api.discovery.response.PagedResponse;
import com.ticketmaster.discovery.model.Event;
import com.ticketmaster.discovery.model.Events;

@Controller
public class EventController {

    @Autowired
    private DiscoveryApi discoveryApi;

    @GetMapping("/events")
    @ResponseBody
    public List<Event> getEvents(String city) throws IOException {

        PagedResponse<Events> page = discoveryApi.searchEvents(new SearchEventsOperation().city(city));
        List<Event> events = page.getContent().getEvents();

        return events;
    }

}
