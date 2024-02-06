package com.fbs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fbs.entity.BookingFlight;
import com.fbs.entity.CheckIn;
import com.fbs.service.CheckedInServiceImpl;

@RestController
@RequestMapping("/checkin")
@CrossOrigin("*")
public class CheckInController {

	@Autowired
	private CheckedInServiceImpl checkInService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@PostMapping("/add")
	public ResponseEntity<String> flighCheckIn(@RequestBody CheckIn checkIn){
		
		restTemplate.put("http://localhost:8300/booking/checkedIn/"+checkIn.getBookingId(), BookingFlight.class);
		
		String str=checkInService.flightCheckIn(checkIn);
		
		return new ResponseEntity<>(str,HttpStatus.OK);
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<Object> getCheckIn(@PathVariable long id){
		CheckIn c = checkInService.getCheckIn(id);
		return new ResponseEntity<>(c,HttpStatus.OK);
		
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<Object> getAllCheckedPassengers(){
		List<CheckIn> list = checkInService.getAllCheckedPassengers();
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	@GetMapping("/getCheckInPassengers/{flightId}")
	public ResponseEntity<Object> getAllCheckedPassengersForFlight(@PathVariable long flightId){
		List<CheckIn> list = checkInService.getAllCheckedPassengersForFlight(flightId);
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	
}
