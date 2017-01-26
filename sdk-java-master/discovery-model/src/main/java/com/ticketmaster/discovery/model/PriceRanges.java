package com.ticketmaster.discovery.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString(callSuper = true)
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PriceRanges extends ResourceSupport {

    private String type;
    private String currency;
    private Double min;
    private Double max;

}
