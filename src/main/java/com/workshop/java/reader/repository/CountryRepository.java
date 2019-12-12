package com.workshop.java.reader.repository;

import com.workshop.java.reader.domain.Country;
import org.springframework.data.repository.CrudRepository;

public interface CountryRepository extends CrudRepository<Country, String> {
}
