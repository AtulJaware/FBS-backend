package com.fbs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
import com.fbs.exception.FlightNotFoundException;
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
		assertEquals(mockFlight, response.getBody());
	}

	@Test
	public void testSearchFlight_NoFlightsFound() throws FlightNotFoundException {
		// Arrange
		String source = "Source";
		String destination = "Destination";
		String date = "2023-09-25"; // Replace with a valid date

		when(flightService.flightSearch(source, destination, date)).thenThrow(new FlightNotFoundException("Not Found"));

		// Act
		assertThrows(FlightNotFoundException.class, () -> flightController.searchFlight(source, destination, date));
	}

	@Test
	public void testSearchFlight_Success() throws FlightNotFoundException {
		// Arrange
		String source = "Source";
		String destination = "Destination";
		String date = "2023-09-25"; // Replace with a valid date

		List<Flight> mockFlightList = new ArrayList<>();
		mockFlightList.add(new Flight());
		when(flightService.flightSearch(source, destination, date)).thenReturn(mockFlightList);

		// Act
		ResponseEntity<Object> response = flightController.searchFlight(source, destination, date);

		// Assert
		verify(flightService, times(1)).flightSearch(source, destination, date);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(mockFlightList, response.getBody());
	}

}