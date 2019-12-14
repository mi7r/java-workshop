package com.workshop.java.reader.web;

import com.workshop.java.reader.domain.Country;
import com.workshop.java.reader.dto.CountryDTO;
import com.workshop.java.reader.exception.CountryCodeNotFoundException;
import com.workshop.java.reader.repository.CountryRepository;
import com.workshop.java.reader.service.CountryService;
import com.workshop.java.reader.service.CountryServiceImpl;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CountryControllerTest {

    @Mock
    CountryService countryService;

    CountryController countryController;

    MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        countryController = new CountryController(countryService);
        mockMvc = MockMvcBuilders.standaloneSetup(countryController)
                .setControllerAdvice(new ErrorHandler()).build();
    }

    @Test
    void findCountryByCode() throws Exception {
        CountryDTO countryDTO = new CountryDTO(
                "Canada", "North America", 31147000, 79, "English");

        when(countryService.findCountryByCode(anyString()))
                .thenReturn(countryDTO);

        mockMvc.perform(get("/{code}", "CAN")
                .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    void findCountryByCodeThrowCountryCodeNotFoundException() throws Exception {
        when(countryService.findCountryByCode(anyString()))
                .thenThrow(CountryCodeNotFoundException.class);

        mockMvc.perform(get("/{code}", "ZZZ")
                .contentType("application/json"))
                .andExpect(status().isInternalServerError());
    }
}