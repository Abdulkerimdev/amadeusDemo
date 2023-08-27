package com.amadeus.demo.Service;

import com.amadeus.demo.Model.Flight;
import com.amadeus.demo.Repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService {
    @Autowired
    private FlightRepository flightRepository;

    public List<Flight> getAllFlights() {
        return flightRepository.findAll();
    }

    public Optional<Flight> getFlightById(Long id) {
        return flightRepository.findById(id);
    }

    public Flight createFlight(Flight flight) {
        return flightRepository.save(flight);
    }

    public Flight updateFlight(Long id, Flight flightDetails) {
        Flight flight = flightRepository.findById(id).orElse(null);
        if (flight != null) {
            flight.setDepartureAirport(flightDetails.getDepartureAirport());
            flight.setArrivalAirport(flightDetails.getArrivalAirport());
            flight.setDepartureDateTime(flightDetails.getDepartureDateTime());
            flight.setReturnDateTime(flightDetails.getReturnDateTime());
            flight.setPrice(flightDetails.getPrice());

            return flightRepository.save(flight);
        }
        return null;
    }

    public void deleteFlight(Long id) {
        flightRepository.deleteById(id);
    }
}

