package com.hotels.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HotelController {

    @GetMapping("/hotel")
    @ResponseBody
    public String getHotels() {
        return "Up and running!";
    }
}
