package com.workshop.java.reader.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class City {

    @Id
    private int id;

    private String name;
    private String district;
    private int population;

    @ManyToOne
    @MapsId(value = "countryCode")
    @JoinColumn(name = "country_code")
    private Country country;

}
