package com.workshop.java.reader.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Country {

    @Id
    @JsonIgnore
    private String code;

    private String name;

    private String continent;

    private int population;

    private int lifeExpectancy;

    private String region;

    private int surfaceArea;

    @OneToMany(mappedBy = "country")
    private List<CountryLanguage> countryLanguages;
}