package com.amadeus.demo.Repository;

import com.amadeus.demo.Model.Airport;
import com.amadeus.demo.Model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findByDepartureAirportAndArrivalAirportAndDepartureDateTime(
            Airport departureAirport, Airport arrivalAirport, LocalDateTime departureDateTime);

    List<Flight> findByDepartureAirportAndArrivalAirport(
            Airport departureAirport, Airport arrivalAirport);

    List<Flight> findByDepartureAirportAndArrivalAirportAndDepartureDateTimeAndReturnDateTime(
            Airport departureAirport, Airport arrivalAirport, LocalDateTime departureDateTime, LocalDateTime returnDateTime);
}