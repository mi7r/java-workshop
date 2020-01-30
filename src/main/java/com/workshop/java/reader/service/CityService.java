package com.workshop.java.reader.service;

import com.workshop.java.reader.dto.CityDTO;

import java.util.Set;

public interface CityService {
    Set<CityDTO> findAllCitiesByName(String name);

    Set<CityDTO> findAllCitiesByCountryCode(String code);
}
