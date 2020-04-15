package com.workshop.java.reader.web;

import com.workshop.java.reader.dto.CityDTO;
import com.workshop.java.reader.exception.CityNotFoundException;
import com.workshop.java.reader.exception.CountryCodeNotFoundException;
import com.workshop.java.reader.service.CityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CityControllerTest {

    @Mock
    CityService cityService;

    CityController cityController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        cityController = new CityController(cityService);
        mockMvc = MockMvcBuilders.standaloneSetup(cityController)
                .setControllerAdvice(new ErrorHandler()).build();
    }

    @Test
    void findAllCitiesByNameWithPositiveResult() throws Exception {
        Set<CityDTO> cities = new HashSet<>();
        CityDTO londonGbr = new CityDTO("London", "England", 7285000, "United Kingdom");
        CityDTO londonCan = new CityDTO("London", "Ontario", 339917, "Canada");
        cities.add(londonGbr);
        cities.add(londonCan);

        when(cityService.findAllCitiesByName(anyString())).thenReturn(cities);

        mockMvc.perform(get("/city/name/{name}", "london")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void findAllCitiesByNameThrowCityNotFoundException() throws Exception {
        when(cityService.findAllCitiesByName(anyString()))
                .thenThrow(CityNotFoundException.class);

        mockMvc.perform(get("/city/name/{name}", "badcity")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(status().reason("INVALID_CITY_NAME"));

    }

    @Test
    void finAllCitiesByCountryCode() throws Exception {
        Set<CityDTO> cities = new HashSet<>();
        CityDTO gdynia = new CityDTO("Gdynia", "Pomorskie", 235521, "Poland");
        CityDTO koszalin = new CityDTO("Koszalin", "Zachodni-Pomorskie", 112375, "Poland");
        CityDTO wroclaw = new CityDTO("Wroclaw", "Dolnoslaskie", 636765, "Poland");
        cities.add(gdynia);
        cities.add(koszalin);
        cities.add(wroclaw);

        when(cityService.findAllCitiesByCountryCode(anyString())).thenReturn(cities);

        mockMvc.perform(get("/city/code/{code}", "POL")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void findAllCitiesByCountryCodeThrowCountryNotFoundException() throws Exception {
        when(cityService.findAllCitiesByCountryCode(anyString()))
                .thenThrow(CountryCodeNotFoundException.class);

        mockMvc.perform(get("/city/code/{code}", "ZZZ")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(status().reason("INVALID_COUNTRY_CODE"));
    }

    @Test
    void findByCountryCodeAndDistrict() {
    }
}