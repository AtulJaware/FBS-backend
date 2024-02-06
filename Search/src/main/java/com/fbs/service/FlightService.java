package com.fbs.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fbs.entity.Flight;


@Service
public interface FlightService {
	
	Flight addFlight(Flight flight);

    List<Flight> flightSearch(String source,String destination,String date);
}
