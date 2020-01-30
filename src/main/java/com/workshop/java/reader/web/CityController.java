package com.workshop.java.reader.web;

import com.workshop.java.reader.dto.CityDTO;
import com.workshop.java.reader.service.CityService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@AllArgsConstructor
public class CityController {

    private final CityService cityService;

    @GetMapping("/city/name/{name}")
    public Set<CityDTO> findAllCitiesByName(@PathVariable String name){
        return cityService.findAllCitiesByName(name);
    }

    @GetMapping("/city/code/{code}")
    public Set<CityDTO> finAllCitiesByCountryCode(@PathVariable String code){
        return cityService.findAllCitiesByCountryCode(code);
    }
}
