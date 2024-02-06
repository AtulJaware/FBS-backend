package com.fbs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fbs.entity.Flight;
import com.fbs.service.FlightServiceImpl;

@RestController
@RequestMapping("/flight")
public class FlightController { 

	@Autowired
	private FlightServiceImpl flightService;

	@PostMapping("/add")
	public ResponseEntity<Object> addFlight(@RequestBody Flight flight) {
		var newFlight = flightService.addFlight(flight);
		return new ResponseEntity<>(newFlight, HttpStatus.CREATED); 

	}

	@PutMapping("/update")
	public ResponseEntity<Object> updateFlight(@RequestBody Flight flight) {
		var newFlight = flightService.updateFlight(flight);
		return new ResponseEntity<>(newFlight, HttpStatus.CREATED);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteFlight(@PathVariable long id) {
		flightService.deleteFlight(id);
		return new ResponseEntity<>("Flight successfully deleted", HttpStatus.OK);
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<Object> getFlightById(@PathVariable long id) {
		var newFlight = flightService.getFlightById(id);
		return new ResponseEntity<>(newFlight, HttpStatus.OK);
	}

	@GetMapping("/getAll")
	public ResponseEntity<Object> getAllFlights() {
		List<Flight> list = flightService.getAllFlights();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

}
