package com.workshop.java.reader.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class CityDTO {
    private String name;
    private String district;
    private int population;
    private String country;
}
