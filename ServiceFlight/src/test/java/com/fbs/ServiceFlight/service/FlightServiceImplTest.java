package com.fbs.ServiceFlight.service;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.fbs.entity.Flight;
import com.fbs.exception.FlightNotFound;
import com.fbs.repository.FlightRepository;
import com.fbs.service.FlightServiceImpl;

public class FlightServiceImplTest { 

    @InjectMocks
    private FlightServiceImpl flightService;

    @Mock
    private FlightRepository flightRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddFlight() {
        // Arrange
        Flight flightToAdd = new Flight();
        when(flightRepository.save(flightToAdd)).thenReturn(flightToAdd);

        // Act
        Flight addedFlight = flightService.addFlight(flightToAdd);

        // Assert
        assertNotNull(addedFlight);
    } 

    @Test
    public void testUpdateFlight() {
        // Arrange
        Flight flightToUpdate = new Flight();
        flightToUpdate.setId(1L);

        when(flightRepository.findById(1L)).thenReturn(Optional.of(flightToUpdate));
        when(flightRepository.save(flightToUpdate)).thenReturn(flightToUpdate);

        // Act
        Flight updatedFlight = flightService.updateFlight(flightToUpdate);

        // Assert
        assertNotNull(updatedFlight);
        assertEquals(1L, updatedFlight.getId());
    }

    @Test
    public void testUpdateFlightNotFound() {
        // Arrange
        Flight flightToUpdate = new Flight();
        flightToUpdate.setId(1L);
 
        when(flightRepository.findById(1L)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(FlightNotFound.class, () -> flightService.updateFlight(flightToUpdate));
    }

    @Test
    public void testDeleteFlight() {
        // Arrange
        long flightIdToDelete = 1L;
        Flight flightToDelete = new Flight();
        flightToDelete.setId(flightIdToDelete);

        when(flightRepository.findById(flightIdToDelete)).thenReturn(Optional.of(flightToDelete));

        // Act
        String result = flightService.deleteFlight(flightIdToDelete);

        // Assert
        assertEquals("Successfully Deleted", result);
    }

    @Test
    public void testDeleteFlightNotFound() {
        // Arrange
        long flightIdToDelete = 1L;

        when(flightRepository.findById(flightIdToDelete)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(FlightNotFound.class, () -> flightService.deleteFlight(flightIdToDelete));
    }

    @Test
    public void testGetFlightById() {
        // Arrange
        long flightId = 1L;
        Flight flight = new Flight();
        flight.setId(flightId);

        when(flightRepository.findById(flightId)).thenReturn(Optional.of(flight));

        // Act
        Flight retrievedFlight = flightService.getFlightById(flightId);

        // Assert
        assertNotNull(retrievedFlight);
        assertEquals(flightId, retrievedFlight.getId());
    }

    @Test
    public void testGetFlightByIdNotFound() {
        // Arrange
        long flightId = 1L;

        when(flightRepository.findById(flightId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(FlightNotFound.class, () -> flightService.getFlightById(flightId));
    }

    @Test
    public void testGetAllFlights() {
        // Arrange
        List<Flight> flightList = new ArrayList<>();
        flightList.add(new Flight());
        flightList.add(new Flight());

        when(flightRepository.findAll()).thenReturn(flightList);

        // Act
        List<Flight> retrievedFlights = flightService.getAllFlights();

        // Assert
        assertNotNull(retrievedFlights);
        assertEquals(flightList.size(), retrievedFlights.size());
    }
}
