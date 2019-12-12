package com.workshop.java.reader.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Country {

    @Id
    private String code;

    private String continent;
    private int population;
    private int lifeExpectancy;
}
