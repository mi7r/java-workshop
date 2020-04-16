package com.workshop.java.reader.web;

import com.workshop.java.reader.dto.CountryDTO;
import com.workshop.java.reader.dto.CountryGeographicalDTO;
import com.workshop.java.reader.service.CountryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@AllArgsConstructor
public class CountryController {

    private final CountryService countryService;

    @GetMapping("/{code}")
    public CountryDTO findCountryByCode(@PathVariable String code) {
        return countryService.findCountryByCode(code);
    }

    @GetMapping("/geo/{code}")
    public CountryGeographicalDTO checkGeographyDataByCode(@PathVariable String code){
        return countryService.checkGeographyDataByCode(code);
    }

    @GetMapping("/region/{region}")
    public Set<CountryDTO> findAllCountriesInGivenRegion(@PathVariable String region){
        return countryService.findAllCountriesInGivenRegion(region);
    }
}
