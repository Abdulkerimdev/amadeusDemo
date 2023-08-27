package com.amadeus.demo.ScheduledJob;

import com.amadeus.demo.Model.Airport;
import com.amadeus.demo.Model.Flight;
import com.amadeus.demo.Repository.AirportRepository;
import com.amadeus.demo.Repository.FlightRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
@Log4j2
@Component
public class FlightScheduledJob {
    private final FlightRepository flightRepository;
    private final AirportRepository airportRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public FlightScheduledJob(FlightRepository flightRepository, AirportRepository airportRepository, RestTemplate restTemplate) {
        this.flightRepository = flightRepository;
        this.airportRepository = airportRepository;
        this.restTemplate = restTemplate;
    }

    @Scheduled(cron = "0 * * * * ?")
    public void fetchAndSaveFlightData() {
        String apiUrl = "http://localhost:8080/mock-api/flights";

        Flight[] flights = restTemplate.getForObject(apiUrl, Flight[].class);
        if (flights != null) {
            for (Flight flight : flights) {
                Airport departureAirport = flight.getDepartureAirport();
                Airport arrivalAirport = flight.getArrivalAirport();

                airportRepository.save(departureAirport);
                airportRepository.save(arrivalAirport);

                flight.setDepartureAirport(departureAirport);
                flight.setArrivalAirport(arrivalAirport);
            }

            flightRepository.saveAll(Arrays.asList(flights));
            System.out.println("Flight data fetched and saved successfully.");
        }
        else {
            log.info("Failed to fetch flight data.");
        }
    }
}
