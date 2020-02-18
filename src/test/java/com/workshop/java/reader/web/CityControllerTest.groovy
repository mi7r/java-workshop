package com.workshop.java.reader.web

import com.fasterxml.jackson.databind.ObjectMapper
import com.workshop.java.reader.dto.CityDTO
import com.workshop.java.reader.exception.CityNotFoundException
import com.workshop.java.reader.service.CityService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class CityControllerTest extends Specification {

    CityService cityService;

    CityController cityController;

    MockMvc mockMvc;

    ObjectMapper mapper = new ObjectMapper()

    CityDTO londonUK
    CityDTO londonCanada
    CityDTO gdynia

    String jsonLondonUK
    String jsonLondonCanada
    String jsonGdynia
    String jsonException

    void setup() {
        cityService = Mock(CityService);
        cityController = new CityController(cityService);

        mockMvc = MockMvcBuilders.standaloneSetup(cityController)
                .setControllerAdvice(new ErrorHandler())
                .alwaysDo(MockMvcResultHandlers.print())
                .build()

        londonUK = new CityDTO("London", "England", 7285000, "United Kingdom")
        londonCanada = new CityDTO("London", "Ontario", 339917, "Canada")
        gdynia = new CityDTO("Gdynia", "pomorskie", 253521, "Poland")

        jsonLondonUK = mapper.writeValueAsString(londonUK)
        jsonLondonCanada = mapper.writeValueAsString(londonCanada)
        jsonGdynia = mapper.writeValueAsString(gdynia)
        jsonException = mapper.writeValueAsString(new CityNotFoundException().localizedMessage)
    }

    void 'get all cities with given name with multiple result'() {
        given:
        cityService.findAllCitiesByName("london") >> [londonUK, londonCanada]

        and:
        def response = [jsonLondonCanada, jsonLondonUK].toString()

        expect:
        mockMvc.perform(get("/city/name/{name}", "london")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(response))

    }

    void 'get all cities with given name with single result'() {
        given:
        cityService.findAllCitiesByName("gdynia") >> [gdynia]

        and:
        def response = [jsonGdynia].toString()

        when:
        mockMvc.perform(get("/city/name/{name}", "gdynia")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(response))

        then:
        noExceptionThrown()
    }

    void 'get all cities method throw CityNotFoundException'() {
        given:
        cityService.findAllCitiesByName("wrongname") >> { throw new CityNotFoundException() }

        when:
        mockMvc.perform(get("/city/name/{name}", "wrongname"))

        then:
        HttpStatus.INTERNAL_SERVER_ERROR
        status().reason("INVALID_CITY_NAME")

    }
}
