package com.workshop.java.reader.service;

import com.workshop.java.reader.domain.Country;
import com.workshop.java.reader.domain.CountryLanguage;
import com.workshop.java.reader.domain.CountryLanguageKey;
import com.workshop.java.reader.dto.CountryDTO;
import com.workshop.java.reader.exception.CountryCodeNotFoundException;
import com.workshop.java.reader.repository.CountryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    @Override
    public CountryDTO findCountryByCode(String code) {
        Optional<Country> countryOptional = countryRepository.findById(code);

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

    private String getOfficialLanguageWithHighestPercentage(Country country) {
        return country.getCountryLanguages()
                .stream()
                .filter(CountryLanguage::isOfficial)
                .sorted(Comparator.comparing(CountryLanguage::getPercentage))
                .map(CountryLanguage::getCountryLanguageKey)
                .map(CountryLanguageKey::getLanguage)
                .findFirst()
                .orElse("No language found.");

    }
}
