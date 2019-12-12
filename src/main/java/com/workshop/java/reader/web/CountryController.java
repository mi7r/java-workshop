package com.workshop.java.reader.web;

import com.workshop.java.reader.domain.Country;
import com.workshop.java.reader.service.CountryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@AllArgsConstructor
public class CountryController {
    private final CountryService countryService;

    @GetMapping("/{code}")
    public Optional<Country> findCountryByCode(@PathVariable String code){
        return countryService.findCountryByCode(code);
    }

}
