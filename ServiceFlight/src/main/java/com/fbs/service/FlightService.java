package com.fbs.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fbs.entity.Flight;

@Service
public interface FlightService {

	Flight addFlight(Flight flight);

	Flight updateFlight(Flight flight);

    String deleteFlight(long id);

	Flight getFlightById(long id);

    List<Flight> getAllFlights();
    
}
