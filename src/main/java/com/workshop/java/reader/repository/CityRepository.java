package com.workshop.java.reader.repository;

import com.workshop.java.reader.domain.City;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CityRepository extends CrudRepository<City, String> {
    Optional<City> findCityByName(String name);
}
