package com.simoes.mario.citiesapi.countries.repository;

import com.simoes.mario.citiesapi.countries.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
}
