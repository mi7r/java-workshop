package com.workshop.java.reader.web;

import com.workshop.java.reader.domain.Country;
import com.workshop.java.reader.repository.CountryRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CountryController {
    private final CountryRepository countryRepository;

    @GetMapping("/{code}")
    public Country findCountryByCode(@PathVariable String code){
        return countryRepository.findById(code).orElseThrow(RuntimeException::new);
    }

}
