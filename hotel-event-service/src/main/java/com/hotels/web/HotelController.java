package com.hotels.web;

import java.io.IOException;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import com.hotels.domain.hotel.HotelSearchResult;
import com.hotels.service.HotelService;

@Controller
public class HotelController {

    @Inject
    private Gson gson;

    @Inject
    private HotelService hotelService;

    @GetMapping("/hotels")
    @ResponseBody
    public HotelSearchResult getHotels(HotelSearchForm form) throws IOException {
        return hotelService.search(form);
    }
}
