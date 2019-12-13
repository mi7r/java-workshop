package com.workshop.java.reader.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CountryLanguage {

    @EmbeddedId
    private CountryLanguageKey countryLanguageKey;

    private boolean isOfficial;

    private Double percentage;

    @ManyToOne
    @MapsId(value = "countryCode")
    @JoinColumn(name = "country_code")
    private Country country;
}
