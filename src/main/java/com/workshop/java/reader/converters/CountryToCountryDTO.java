package com.workshop.java.reader.converters;

import com.workshop.java.reader.domain.Country;
import com.workshop.java.reader.dto.CountryDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CountryToCountryDTO implements Converter<Country, CountryDTO> {

    @Override
    public CountryDTO convert(Country source) {
        return CountryDTO.builder()
                .name(source.getName())
                .continent(source.getContinent())
                .population(source.getPopulation())
                .lifeExpectancy(source.getLifeExpectancy())
                .build();
    }
}
