package com.workshop.java.reader.web;

import com.workshop.java.reader.dto.CountryDTO;
import com.workshop.java.reader.exception.CountryCodeNotFoundException;
import com.workshop.java.reader.service.CountryService;
import org.hibernate.exception.JDBCConnectionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

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
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void findCountryByCodeThrowCountryCodeNotFoundException() throws Exception {
        when(countryService.findCountryByCode(anyString()))
                .thenThrow(CountryCodeNotFoundException.class);

        mockMvc.perform(get("/{code}", "ZZZ")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(status().reason("INVALID_COUNTRY_CODE"));
    }

    @Test
    void checkDatabaseConnectionIsClosedAndStatusCode500IsReturned() throws Exception {
        when(countryController.findCountryByCode(anyString())).thenThrow(JDBCConnectionException.class);

        mockMvc.perform(get("/{code}", "USA")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(status().reason("INTERNAL_ERROR"));
    }
}