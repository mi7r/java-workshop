package com.workshop.java.reader.converters;

import com.workshop.java.reader.domain.City;
import com.workshop.java.reader.dto.CityDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CityToCityDTO implements Converter<City, CityDTO> {

    @Override
    public CityDTO convert(City source) {
        return CityDTO.builder()
                .name(source.getName())
                .country(source.getCountry().getName())
                .district(source.getDistrict())
                .population(source.getPopulation())
                .build();
    }
}
