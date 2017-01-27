package com.hotels.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hotels.service.GoogleCustomSearchService;
import com.hotels.service.GoogleStaticMapService;

@Controller
public class DestinationImageController {

    @Autowired
    private GoogleCustomSearchService googleCustomSearchService;
    @Autowired
    private GoogleStaticMapService googleStaticMapService;

    @GetMapping("/image")
    @ResponseBody
    public String getDestinationImage(String destination, boolean updateCache) {
        return googleCustomSearchService.getImageUrl(destination + " city", updateCache);
    }

    @GetMapping("/map")
    @ResponseBody
    public String getMap(String city, String state) {
        return googleStaticMapService.getImageUrl(city, state);
    }

}
