package com.fbs.ServiceFlight.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fbs.controller.FlightController;
import com.fbs.entity.Flight;
import com.fbs.service.FlightServiceImpl;

class FlightControllerTest {

	    @InjectMocks
	    private FlightController flightController;

	    @Mock
	    private FlightServiceImpl flightService;

	    @BeforeEach
	    public void setUp() {
	        MockitoAnnotations.initMocks(this);
	    }

	    @Test
	    public void testAddFlight_Success() {
	        // Arrange
	        Flight mockFlight = new Flight();
	        when(flightService.addFlight(any(Flight.class))).thenReturn(mockFlight);

	        // Act
	        ResponseEntity<Object> response = flightController.addFlight(mockFlight);

	        // Assert
	        verify(flightService, times(1)).addFlight(mockFlight);
	        assertEquals(HttpStatus.CREATED, response.getStatusCode());
	    }
	    
	    
	    @Test
	    public void testUpdateFlight_Success() {
	        // Arrange
	        Flight mockFlight = new Flight();
	        when(flightService.updateFlight(any(Flight.class))).thenReturn(mockFlight);

	        // Act
	        ResponseEntity<Object> response = flightController.updateFlight(mockFlight);

	        // Assert
	        verify(flightService, times(1)).updateFlight(mockFlight);
	        assertEquals(HttpStatus.CREATED, response.getStatusCode());
	    }
	    
	    @Test
	    public void testDeleteFlight_Success() {
	        // Arrange
	        long flightId = 1L;

	        // Act
	        ResponseEntity<Object> response = flightController.deleteFlight(flightId);

	        // Assert
	        verify(flightService, times(1)).deleteFlight(flightId);
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals("Flight successfully deleted", response.getBody());
	    }
	    
	    
	    @Test
	    public void testGetFlightById_Success() {
	        // Arrange
	        long flightId = 1L;
	        Flight mockFlight = new Flight();
	        when(flightService.getFlightById(flightId)).thenReturn(mockFlight);

	        // Act
	        ResponseEntity<Object> response = flightController.getFlightById(flightId);

	        // Assert
	        verify(flightService, times(1)).getFlightById(flightId);
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(mockFlight, response.getBody());
	    }
	    
	    @Test
	    public void testGetAllFlights_Success() {
	        // Arrange
	        List<Flight> mockFlightList = new ArrayList<>();
	        mockFlightList.add(new Flight());
	        mockFlightList.add(new Flight());
	        when(flightService.getAllFlights()).thenReturn(mockFlightList);

	        // Act
	        ResponseEntity<Object> response = flightController.getAllFlights();

	        // Assert
	        verify(flightService, times(1)).getAllFlights();
	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(mockFlightList, response.getBody());
	    }
	    

	}