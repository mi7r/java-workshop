package com.workshop.java.reader.repository;

import com.workshop.java.reader.domain.Country;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CountryRepository extends CrudRepository<Country, String> {

    List<Country> findAllByRegion(String region);
}
