package com.hotels.web;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HotelController {

    @GetMapping("/")
    @ResponseBody
    public String index() throws IOException {
        return "Up and running!";
    }

    @GetMapping("/hotel")
    @ResponseBody
    public String getHotels() throws IOException {
        return "Up and running for hotels!";
    }
}
