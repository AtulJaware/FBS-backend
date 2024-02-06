package com.fbs;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import com.fbs.entity.Flight;
import com.fbs.exception.FlightNotFoundException;
import com.fbs.repository.FlightRepository;
import com.fbs.service.FlightServiceImpl;

class FlightServiceImplTest {

	@InjectMocks
	private FlightServiceImpl flightService;

	@Mock
	private FlightRepository flightRepository;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testAddFlight_Success() {
		// Arrange
		Flight mockFlight = new Flight();
		when(flightRepository.save(mockFlight)).thenReturn(mockFlight);

		// Act
		Flight result = flightService.addFlight(mockFlight);

		// Assert
		assertNotNull(result);
		assertEquals(mockFlight, result);
	}

	@Test
	public void testFlightSearch_Success() {
		// Arrange
		String source = "Source";
		String destination = "Destination";
		String date = "2023-09-25";

		List<Flight> mockFlightList = new ArrayList<>();
		mockFlightList.add(new Flight());
		when(flightRepository.findBySourceDestinationAndDate(source, destination, date))
				.thenReturn(Optional.of(mockFlightList));

		// Act and Assert
		List<Flight> result = flightService.flightSearch(source, destination, date);
		assertNotNull(result);
		assertEquals(mockFlightList, result);
	}

	@Test
	public void testFlightSearch_NoFlightsFound() {
		// Arrange
		String source = "Source";
		String destination = "Destination";
		String date = "2023-09-25"; // Replace with a valid date

		when(flightRepository.findBySourceDestinationAndDate(source, destination, date)).thenReturn(Optional.empty());

		// Act and Assert
		assertThrows(FlightNotFoundException.class, () -> flightService.flightSearch(source, destination, date));
	}

}
