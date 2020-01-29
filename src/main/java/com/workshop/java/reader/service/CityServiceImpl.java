package com.workshop.java.reader.service;

import com.workshop.java.reader.domain.City;
import com.workshop.java.reader.dto.CityDTO;
import com.workshop.java.reader.exception.CityNotFoundException;
import com.workshop.java.reader.repository.CityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    @Override
    public CityDTO findCityByName(String name) {
        Optional<City> cityOptional = cityRepository.findCityByName(name);
        if (!cityOptional.isPresent()){
            throw new CityNotFoundException();
        }

        City city = cityOptional.get();

        return CityDTO.builder()
                .name(city.getName())
                .population(city.getPopulation())
                .district(city.getDistrict())
                .country(city.getCountry().getName())
                .build();
    }
}
