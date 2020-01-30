package com.workshop.java.reader.repository;

import com.workshop.java.reader.domain.City;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CityRepository extends CrudRepository<City, String> {

    List<City> findAllByName(String name);

    List<City> findAllByCountryCode(String name);
}
