package com.workshop.java.reader.web;

import com.workshop.java.reader.dto.CityDTO;
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
    void finAllCitiesByCountryCode() {
    }

    @Test
    void findByCountryCodeAndDistrict() {
    }
}