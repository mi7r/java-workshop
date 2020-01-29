package com.workshop.java.reader.web;

import com.workshop.java.reader.dto.CityDTO;
import com.workshop.java.reader.service.CityService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CityController {

    private final CityService cityService;

    @GetMapping("/city/{name}")
    public CityDTO findCityByName(@PathVariable String name){
        return cityService.findCityByName(name);
    }
}
