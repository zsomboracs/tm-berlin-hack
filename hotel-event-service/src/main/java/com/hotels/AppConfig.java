package com.hotels;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ticketmaster.api.discovery.DiscoveryApi;

@Configuration
public class AppConfig {

    @Value("${apikey}")
    private String apikey;

    @Bean
    public DiscoveryApi discoveryApi() {
        return new DiscoveryApi(apikey);
    }

}
