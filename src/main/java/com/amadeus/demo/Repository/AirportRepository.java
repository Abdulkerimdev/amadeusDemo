package com.amadeus.demo.Repository;

import com.amadeus.demo.Model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport, Long> {
}