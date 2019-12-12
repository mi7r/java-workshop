package com.workshop.java.reader.service;

import com.workshop.java.reader.domain.Country;

import java.util.Optional;

public interface CountryService {

    Optional<Country> findCountryByCode(String code);
}
