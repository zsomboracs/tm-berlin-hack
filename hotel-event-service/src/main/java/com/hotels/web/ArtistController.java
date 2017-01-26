package com.hotels.web;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hotels.IdMapping;
import com.hotels.domain.machine.Artist;
import com.ticketmaster.api.discovery.DiscoveryApi;
import com.ticketmaster.api.discovery.operation.SearchEventsOperation;
import com.ticketmaster.api.discovery.response.PagedResponse;
import com.ticketmaster.discovery.model.Attraction;
import com.ticketmaster.discovery.model.Event;
import com.ticketmaster.discovery.model.Events;
import com.ticketmaster.discovery.model.Image;

@Controller
public class ArtistController {

    private static final String FILE_NAME = "artists.json";

    @Autowired
    private DiscoveryApi discoveryApi;
    @Autowired
    private Gson gson;

    private List<Artist> artists;

    @PostConstruct
    private void init() throws IOException {
        File file = new File(getClass().getClassLoader().getResource(FILE_NAME).getFile());
        String content = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
        artists = gson.fromJson(content, new TypeToken<ArrayList<Artist>>() {
        }.getType());
    }

    @GetMapping("/artists")
    @ResponseBody
    public List<Artist> getArtists() throws IOException {
        return artists;
    }

    @GetMapping("/initialize")
    @ResponseBody
    public String getInitialize() throws IOException {

        List<Event> events = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            System.out.println("Getting index: " + i);
            addEvents(events, i);
        }

        List<Event> filteredEvents = events.stream()
                .filter(e -> Optional.ofNullable(e.getAttractions()).map(List::size).orElse(0) == 1)
                .filter(e -> Optional.ofNullable(e.getPriceRanges()).map(List::size).orElse(0) > 0)
                .collect(Collectors.toList());

        Map<Attraction, Integer> attractionMap = new HashMap<>();
        for (Event event : filteredEvents) {
            Attraction attraction = event.getAttractions().get(0);
            attractionMap.put(attraction, Optional.ofNullable(attractionMap.get(attraction)).orElse(0) + 1);
        }

        List<Artist> result = attractionMap.entrySet().stream()
                .filter(a -> Objects.nonNull(a.getKey().getImages()))
                .filter(a -> !a.getKey().getImages().isEmpty())
                .sorted(Map.Entry.<Attraction, Integer>comparingByValue().reversed())
                .limit(100)
                .map(entry -> this.getArtist(entry.getKey()))
                .collect(Collectors.toList());

        String jsonResult = gson.toJson(result);

        Files.write(Paths.get(FILE_NAME), jsonResult.getBytes());

        return "Done!";
    }

    private void addEvents(List<Event> events, int i) {
        try {
            PagedResponse<Events> page = discoveryApi.searchEvents(
                    new SearchEventsOperation()
                            .pageSize(IdMapping.MAX_PAGE_SIZE)
                            .pageNumber(i)
                            .classificationId(IdMapping.MUSIC_CATEGORY_ID));
            events.addAll(page.getContent().getEvents());
        } catch (Exception e) {
            System.out.println("Socket timeout .. trying again ...");
            addEvents(events, i);
        }
    }

    private Artist getArtist(Attraction attraction) {
        return new Artist(attraction.getId(), attraction.getName(), getImageUrl(attraction.getImages()));
    }

    private String getImageUrl(List<Image> images) {
        return images.stream()
                .filter(i -> i.getRatio().equals("3_2"))
                .filter(i -> i.getWidth() == 305)
                .filter(i -> i.getHeight() == 203)
                .map(Image::getUrl)
                .findAny()
                .orElse(null);
    }
}
