package com.workshop.java.reader.service;

import com.workshop.java.reader.dto.CountryDTO;

public interface CountryService {

    CountryDTO findCountryByCode(String code);
}
