package com.workshop.java.reader.service;

import com.workshop.java.reader.converters.CountryToCountryDTO;
import com.workshop.java.reader.domain.City;
import com.workshop.java.reader.domain.Country;
import com.workshop.java.reader.domain.CountryLanguage;
import com.workshop.java.reader.domain.CountryLanguageKey;
import com.workshop.java.reader.dto.CountryDTO;
import com.workshop.java.reader.dto.CountryGeographicalDTO;
import com.workshop.java.reader.exception.CountryCodeNotFoundException;
import com.workshop.java.reader.exception.RegionNotFoundException;
import com.workshop.java.reader.repository.CountryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;
    private final CountryToCountryDTO converter;

    @Override
    public CountryDTO findCountryByCode(String code) {
        Optional<Country> countryOptional = countryRepository.findById(code.toUpperCase());

        if (!countryOptional.isPresent()) {
            throw new CountryCodeNotFoundException();
        }
        Country country = countryOptional.get();

        String language = getOfficialLanguageWithHighestPercentage(country);

        return CountryDTO.builder()
                .name(country.getName())
                .continent(country.getContinent())
                .population(country.getPopulation())
                .lifeExpectancy(country.getLifeExpectancy())
                .language(language)
                .build();
    }

    @Override
    public CountryGeographicalDTO checkGeographyDataByCode(String code) {
        Optional<Country> countryOptional = countryRepository.findById(code.toUpperCase());
        if (!countryOptional.isPresent()) {
            throw new CountryCodeNotFoundException();
        }

        Country country = countryOptional.get();

        return CountryGeographicalDTO.builder()
                .name(country.getName())
                .surfaceArea(country.getSurfaceArea())
                .population(country.getPopulation())
                .continent(country.getContinent())
                .region(country.getRegion())
                .languages(getAllLanguagesUsedInCountry(country))
                .cities(getAllCitiesInCountry(country))
                .build();
    }

    @Override
    public Set<CountryDTO> findAllCountriesInGivenRegion(String region) {
        Set<Country> countries = new HashSet<>();

        countryRepository.findAllByRegion(convertToTitleCase(region)).iterator().forEachRemaining(countries::add);

        if (countries.isEmpty()) {
            throw new RegionNotFoundException();
        }

        return countries.stream().map(country -> CountryDTO.builder()
                .name(country.getName())
                .continent(country.getContinent())
                .population(country.getPopulation())
                .lifeExpectancy(country.getLifeExpectancy())
                .language(getOfficialLanguageWithHighestPercentage(country))
                .build())
                .collect(Collectors.toSet());
    }

    private String getOfficialLanguageWithHighestPercentage(Country country) {
        return country.getCountryLanguages()
                .stream()
                .filter(CountryLanguage::isOfficial)
                .sorted(Comparator.comparing(CountryLanguage::getPercentage).reversed())
                .map(CountryLanguage::getCountryLanguageKey)
                .map(CountryLanguageKey::getLanguage)
                .findFirst()
                .orElse("No language found.");

    }

    private List<String> getAllLanguagesUsedInCountry(Country country) {
        return country.getCountryLanguages()
                .stream()
                .sorted(Comparator.comparing(CountryLanguage::getPercentage).reversed())
                .map(CountryLanguage::getCountryLanguageKey)
                .map(CountryLanguageKey::getLanguage)
                .collect(Collectors.toList());
    }

    private List<String> getAllCitiesInCountry(Country country) {
        return country.getCities()
                .stream()
                .map(City::getName)
                .collect(Collectors.toList());
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
}
