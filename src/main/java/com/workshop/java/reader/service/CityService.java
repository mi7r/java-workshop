package com.workshop.java.reader.service;

import com.workshop.java.reader.dto.CityDTO;

public interface CityService {
    CityDTO findCityByName(String name);
}
