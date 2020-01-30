package com.workshop.java.reader.service;

import com.workshop.java.reader.converters.CityToCityDTO;
import com.workshop.java.reader.domain.City;
import com.workshop.java.reader.dto.CityDTO;
import com.workshop.java.reader.exception.CityNotFoundException;
import com.workshop.java.reader.repository.CityRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final CityToCityDTO converter;

    @Override
    public Set<CityDTO> findAllCitiesByName(String name) {
        Set<City> cities = new HashSet<>();

        cityRepository.findAllByName(convertToTitleCase(name)).iterator().forEachRemaining(cities::add);
        if (cities.isEmpty()) {
            throw new CityNotFoundException();
        }

        return cities
                .stream()
                .map(converter::convert)
                .collect(Collectors.toSet());
    }

    private String convertToTitleCase(String text) {
        StringBuilder convertText = new StringBuilder(text.length());
        boolean nextWord = true;

        for (char c : text.toCharArray()) {
            if (Character.isSpaceChar(c)) {
                nextWord = true;
            } else if (nextWord) {
                c = Character.toTitleCase(c);
                nextWord = false;
            }
            convertText.append(c);
        }

        return convertText.toString();
    }

    @Override
    public Set<CityDTO> findAllCitiesByCountryCode(String code) {
        Set<City> cities = new HashSet<>();

        cityRepository.findAllByCountryCode(code.toUpperCase()).iterator().forEachRemaining(cities::add);
        if (cities.isEmpty()){
            throw new CityNotFoundException();
        }

        return cities.stream()
                .map(converter::convert)
                .collect(Collectors.toSet());
    }
}
