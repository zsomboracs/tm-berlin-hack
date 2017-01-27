package com.hotels.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hotels.domain.machine.Artist;
import com.hotels.domain.machine.FullArtistData;
import com.hotels.service.EventFilterService;
import com.hotels.service.SearchOperationProvider;
import com.ticketmaster.api.discovery.DiscoveryApi;
import com.ticketmaster.api.discovery.response.PagedResponse;
import com.ticketmaster.discovery.model.Attraction;
import com.ticketmaster.discovery.model.Date;
import com.ticketmaster.discovery.model.Event;
import com.ticketmaster.discovery.model.Events;
import com.ticketmaster.discovery.model.Image;

@Controller
public class ArtistController {

    private static final String FILE_NAME = "artists.ser";

    @Autowired
    private DiscoveryApi discoveryApi;
    @Autowired
    private SearchOperationProvider searchOperationProvider;
    @Autowired
    private EventFilterService eventFilterService;

    private List<FullArtistData> artists;

//    @PostConstruct
//    private void init() {
//        try {
//            //File file = new File(getClass().getClassLoader().getResource(FILE_NAME).getFile());
//            FileInputStream fileIn = new FileInputStream(FILE_NAME);
//            ObjectInputStream in = new ObjectInputStream(fileIn);
//            artists = (List<FullArtistData>) in.readObject();
//            in.close();
//            fileIn.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }

    @GetMapping("/artists")
    @ResponseBody
    public List<Artist> getArtists(
            @RequestParam(value = "startDate") String startDate,
            @RequestParam(value = "endDate") String endDate) {

        LocalDate startDateLD = LocalDate.parse(startDate);
        LocalDate endDateLD = LocalDate.parse(endDate);

        return artists.stream()
                .filter(a -> hasEnoughEvents(a.getEventDates(),
                        LocalDateTime.of(startDateLD.getYear(), startDateLD.getMonth(), startDateLD.getDayOfMonth(), 0, 0, 0),
                        LocalDateTime.of(endDateLD.getYear(), endDateLD.getMonth(), endDateLD.getDayOfMonth(), 0, 0, 0)))
                .map(FullArtistData::getArtist)
                .collect(Collectors.toList());
    }

    @GetMapping("/initialize")
    @ResponseBody
    public String getInitialize() throws IOException {

        List<Event> events = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            System.out.println("Getting index: " + i);
            addEvents(events, i);
        }

        List<Event> filteredEvents = eventFilterService.filter(events.stream()).collect(Collectors.toList());

        Map<Attraction, Integer> attractionMap = new HashMap<>();
        for (Event event : filteredEvents) {
            Attraction attraction = event.getAttractions().get(0);
            attractionMap.put(attraction, Optional.ofNullable(attractionMap.get(attraction)).orElse(0) + 1);
        }

        List<Artist> result = attractionMap.entrySet().stream()
                .sorted(Map.Entry.<Attraction, Integer>comparingByValue().reversed())
                .limit(100)
                .map(entry -> this.getArtist(entry.getKey()))
                .collect(Collectors.toList());

        List<FullArtistData> fullArtistDataList = new ArrayList<>();

        int i = 0;
        for (Artist artist : result) {
            System.out.println("Getting events for artists ... " + i);
            PagedResponse<Events> page = discoveryApi.searchEvents(searchOperationProvider.getBaseOperation().attractionId(artist.getId()));
            List<LocalDateTime> eventDates = eventFilterService.filter(page.getContent().getEvents().stream())
                    .map(Event::getDates)
                    .map(Date::getStart)
                    .map(Date.Start::getDateTime)
                    .map(joda -> LocalDateTime.ofInstant(joda.toDate().toInstant(), ZoneId.systemDefault()))
                    .sorted()
                    .collect(Collectors.toList());
            fullArtistDataList.add(new FullArtistData.Builder().withSummaryArtistData(artist).withEventDates(eventDates).build());
            i++;
        }

        artists = fullArtistDataList;

//        try {
//            FileOutputStream fileOut = new FileOutputStream("artists.ser");
//            ObjectOutputStream out = new ObjectOutputStream(fileOut);
//            out.writeObject(fullArtistDataList);
//            out.close();
//            fileOut.close();
//            System.out.printf("Serialized data is saved in artists.ser");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        return "Done!";
    }

    private boolean hasEnoughEvents(List<LocalDateTime> eventDates, LocalDateTime startDate, LocalDateTime endDate) {
        return eventDates.stream()
                .filter(eventDate -> eventDate.isAfter(startDate) || eventDate.isEqual(startDate))
                .filter(eventDate -> eventDate.isBefore(endDate))
                .count() > 5;
    }

    private void addEvents(List<Event> events, int i) {
        try {
            PagedResponse<Events> page = discoveryApi.searchEvents(searchOperationProvider.getBaseOperation().pageNumber(i));
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
