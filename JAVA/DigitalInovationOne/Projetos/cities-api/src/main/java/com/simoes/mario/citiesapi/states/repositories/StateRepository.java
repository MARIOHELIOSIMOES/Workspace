package com.simoes.mario.citiesapi.states.repositories;


import com.simoes.mario.citiesapi.states.entities.State;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepository extends JpaRepository<State, Long> {
}
