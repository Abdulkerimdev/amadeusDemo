package com.amadeus.demo.Controller;

import com.amadeus.demo.Repository.FlightRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.amadeus.demo.Model.Flight;
import com.amadeus.demo.ScheduledJob.FlightScheduledJob;
import com.amadeus.demo.Service.FlightService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ContextConfiguration
@SpringBootTest
@AutoConfigureMockMvc
public class FlightControllerTest {

    @Mock
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private FlightScheduledJob scheduledJob;

    @Mock
    private FlightService flightService;
    @Test
    @WithMockUser("user")
    public void testGetAllFlights() throws Exception {
        List<Flight> flights = new ArrayList<>();

        when(flightService.getAllFlights()).thenReturn(flights);

        mockMvc.perform(MockMvcRequestBuilders.get("/flights"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(flights.size()));
    }

    @Test
    public void testTriggerJobManually() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/flights/trigger-job"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Job triggered manually"));
    }

    @Test
    public void testGetFlightById() throws Exception {
        Flight flight = new Flight();
        flight.setId(11L);

        when(flightService.getFlightById(anyLong())).thenReturn(Optional.of(flight));

        mockMvc.perform(MockMvcRequestBuilders.get("/flights/{id}", 11L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(flight.getId()));
    }

    @Test
    public void testCreateFlight() throws Exception {
        Flight flight = new Flight();
        // Set flight properties

        when(flightService.createFlight(any(Flight.class))).thenReturn(flight);

        mockMvc.perform(MockMvcRequestBuilders.post("/flights")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(flight)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(flight.getId()));
    }

    @Test
    public void testUpdateFlight() throws Exception {
        Flight existingFlight = new Flight();
        // Set existingFlight properties

        Flight updatedFlight = new Flight();
        // Set updatedFlight properties

        when(flightService.updateFlight(anyLong(), any(Flight.class))).thenReturn(updatedFlight);

        mockMvc.perform(MockMvcRequestBuilders.put("/flights/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedFlight)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(updatedFlight.getId()));
    }

    @Test
    public void testDeleteFlight() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/flights/{id}", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}

