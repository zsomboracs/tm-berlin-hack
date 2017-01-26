package com.hotels.service;

import org.springframework.stereotype.Component;

import com.hotels.IdMapping;
import com.ticketmaster.api.discovery.operation.SearchEventsOperation;

@Component
public class SearchOperationProvider {

    public SearchEventsOperation getBaseOperation() {
        return new SearchEventsOperation()
                .pageSize(IdMapping.MAX_PAGE_SIZE)
                .countryCode(IdMapping.US_COUNTRY_CODE)
                .classificationId(IdMapping.MUSIC_CATEGORY_ID);
    }
}
