package com.workshop.java.reader.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CountryDTO {

    private String name;
    private String continent;
    private int population;
    private int lifeExpectancy;
    private String language;
}
