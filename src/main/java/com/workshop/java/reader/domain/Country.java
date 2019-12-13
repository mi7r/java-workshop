package com.workshop.java.reader.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Country {

    @Id
    @JsonIgnore
    private String code;

    private String name;

    private String continent;

    private int population;

    private int lifeExpectancy;
}