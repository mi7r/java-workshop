package com.workshop.java.reader.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
@Builder
public class CountryGeographicalDTO {
    private String name;
    private String continent;
    private String region;
    private int population;
    private int surfaceArea;
    private List<String> languages;

}
