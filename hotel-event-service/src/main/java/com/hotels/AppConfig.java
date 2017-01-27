package com.hotels;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.google.gson.Gson;
import com.ticketmaster.api.discovery.DiscoveryApi;

@Configuration
public class AppConfig extends WebMvcConfigurerAdapter {

    @Value("${API_KEY}")
    private String apikey;

    @Bean
    public DiscoveryApi discoveryApi() {
        return new DiscoveryApi(apikey);
    }

    @Bean
    public Gson gson() {
        return new Gson();
    }

    @Bean
    public RestTemplate restTemplate() {
        List<HttpMessageConverter<?>> converters = new ArrayList<>();
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.propertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        converters.add(new MappingJackson2HttpMessageConverter(builder.build()));
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(converters);
        return restTemplate;
    }

    @Bean
    public HttpEntity httpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity(headers);
    }

}
