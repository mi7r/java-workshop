package com.workshop.java.reader.service;

import com.workshop.java.reader.domain.Country;
import com.workshop.java.reader.dto.CountryDTO;
import com.workshop.java.reader.exception.CountryCodeNotFoundException;
import com.workshop.java.reader.repository.CountryRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class CountryServiceImplTest {

    CountryServiceImpl countryService;

    @Mock
    CountryRepository countryRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        countryService = new CountryServiceImpl(countryRepository);
    }

    @Test
    public void getCountryByCountryCodeWithPositiveResult() {
        Country country = new Country();
        country.setCode("BHR");
        Optional<Country> countryOptional = Optional.of(country);

        when(countryRepository.findById(anyString())).thenReturn(countryOptional);

        CountryDTO countryToReturn = countryService.findCountryByCode("BHR");

        assertNotNull(countryToReturn);
        verify(countryRepository, times(1)).findById(anyString());
        verify(countryRepository, never()).findAll();
    }

    @Test(expected = CountryCodeNotFoundException.class)
    public void getCountryByCountryCodeThrowCountryCodeNotFoundException(){
        CountryDTO countryDTO = countryService.findCountryByCode("ZZZ");
    }

}