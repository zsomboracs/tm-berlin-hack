package com.ticketmaster.discovery.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@ToString(callSuper = true)
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Sale extends BaseModel {

  @JsonProperty("public")
  private Public publicSale;


  @ToString(callSuper = true)
  @Getter
  @Setter
  @EqualsAndHashCode(callSuper = true)
  @NoArgsConstructor
  @AllArgsConstructor
  @JsonInclude(JsonInclude.Include.NON_EMPTY)
  public static class Public extends BaseModel {

    private DateTime startDateTime;
    private DateTime endDateTime;
    private Boolean startTBD;
  }
}
