package com.fbs.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fbs.entity.Flight;
import com.fbs.exception.FlightNotFound;
import com.fbs.repository.FlightRepository;

@Service
public class FlightServiceImpl implements FlightService {

	@Autowired
	private FlightRepository flightRepo;

	@Override
	public Flight addFlight(Flight flight) {
		Flight newFlight = flightRepo.save(flight);
		return newFlight;
	}

	@Override
	public Flight updateFlight(Flight flight) {
		// TODO Auto-generated method stub
		
	    flightRepo.findById(flight.getId()).orElseThrow(() -> new FlightNotFound("Flight not found with " + flight.getId()));
		return flightRepo.save(flight);
	}

	@Override
	public String deleteFlight(long id) {
		Flight f = flightRepo.findById(id).orElseThrow(() -> new FlightNotFound("Flight not found with " + id));
		flightRepo.deleteById(f.getId());

		return "Successfully Deleted";
	}

	@Override
	public Flight getFlightById(long id) {
		Flight f = flightRepo.findById(id).orElseThrow(() -> new FlightNotFound("Flight not found with " + id));
		return f;
	}

	@Override
	public List<Flight> getAllFlights() {
		return flightRepo.findAll();
	}

	
	
}

