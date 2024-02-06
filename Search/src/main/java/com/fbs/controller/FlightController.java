package com.fbs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fbs.entity.Flight;
import com.fbs.exception.FlightNotFoundException;
import com.fbs.service.FlightServiceImpl;

@RestController
@RequestMapping("/search")
@CrossOrigin(origins = "*")
public class FlightController {

	@Autowired
	private FlightServiceImpl flightService;

	@PostMapping("/add")
	public ResponseEntity<Object> addFlight(@RequestBody Flight flight) {
		var newFlight = flightService.addFlight(flight);
		return new ResponseEntity<>(newFlight, HttpStatus.CREATED);
	}

	@GetMapping("/getAll")
	public ResponseEntity<Object> searchFlight(@RequestParam String source, @RequestParam String destination,
			@RequestParam String date)
			throws FlightNotFoundException {
		List<Flight> list = flightService.flightSearch(source, destination, date);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

}
