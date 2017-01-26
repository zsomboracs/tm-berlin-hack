package com.hotels.web;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.hotels.domain.international.Attraction;
import com.hotels.domain.international.Response;
import com.hotels.service.InternationalTicketMasterApiService;

@Controller
public class InternationalEventController {

//    @Autowired
//    private InternationalTicketMasterApiService internationalTicketMasterApiService;
//    @Autowired
//    private RestTemplate restTemplate;
//    @Autowired
//    private HttpEntity httpEntity;
//
//    @GetMapping("/international/events")
//    @ResponseBody
//    public String getEvents(String artist) throws Exception {
//        String url = internationalTicketMasterApiService.getEventsApiUrl();
//        HttpEntity<Response> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, Response.class);
//
//        Map<String, Integer> artistMap = new HashMap<>();
//
//        Set<Attraction> topArtists = response.getBody().getEvents().stream()
//                .filter(e -> Optional.ofNullable(e.getAttractions()).map(List::size).orElse(0) == 1)
//                .filter(e -> Optional.ofNullable(e.getCategories()).map(List::size).orElse(0) == 1)
//                .map(e -> e.getAttractions().get(0))
//                .collect(Collectors.toCollection(LinkedHashSet::new));
//
//
//
//
//        //        int index = (int) response.getBody().getEvents().stream()
//        //                .filter(e -> Optional.ofNullable(e.getAttractions()).map(List::size).orElse(0) == 1)
//        //                .filter(e -> Optional.ofNullable(e.getCategories()).map(List::size).orElse(0) == 1).count();
//        //
//        //        for (Event e : response.getBody().getEvents()) {
//        //            if (Optional.ofNullable(e.getAttractions()).map(List::size).orElse(0) == 1 &&
//        //                    Optional.ofNullable(e.getCategories()).map(List::size).orElse(0) == 1) {
//        //                String attraction = e.getAttractions().get(0).getName();
//        //                artistMap.put(attraction, Optional.ofNullable(artistMap.get(attraction)).orElse(0) + index);
//        //                index--;
//        //            }
//        //        }
//        //
//        //        List<Map.Entry<String, Integer>> sorted = artistMap.entrySet().stream()
//        //                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed()).collect(Collectors.toList());
//
//        return "It's working!";
//    }

}
