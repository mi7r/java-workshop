package com.workshop.java.reader.service;

import com.workshop.java.reader.dto.CountryDTO;
import com.workshop.java.reader.dto.CountryGeographicalDTO;

import java.util.Set;

public interface CountryService {

    CountryDTO findCountryByCode(String code);

    CountryGeographicalDTO checkGeographyDataByCode(String code);

    Set<CountryDTO> findAllCountriesInGivenRegion(String region);


}
