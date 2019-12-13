package com.workshop.java.reader.service;

import com.workshop.java.reader.domain.Country;
import com.workshop.java.reader.exception.CountryCodeNotFoundException;
import com.workshop.java.reader.repository.CountryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    @Override
    public Optional<Country> findCountryByCode(String code) {
        Optional<Country> countryOptional = countryRepository.findById(code);

        if (!countryOptional.isPresent()) {
            throw new CountryCodeNotFoundException();
        }
        return countryOptional;
    }
}
