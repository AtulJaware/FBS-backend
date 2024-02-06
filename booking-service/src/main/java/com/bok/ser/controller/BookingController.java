package com.bok.ser.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.bok.ser.entity.BookingFlight;
import com.bok.ser.entity.Flight;
import com.bok.ser.entity.User;
import com.bok.ser.service.BookingService;

@RestController
@RequestMapping("/booking")
@CrossOrigin(origins="*")
public class BookingController {

	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private RestTemplate restTemplate;

	@PostMapping("/book/{flightId}")
	public String book(@RequestBody User user, @PathVariable long flightId) {
		BookingFlight bookingFlight = new BookingFlight(); 
		
		bookingFlight.setId(user.getId());
		bookingFlight.setFirstName(user.getFirstName());
		bookingFlight.setLastName(user.getLastName()); 
		bookingFlight.setGender(user.getGender());
		bookingFlight.setAge(user.getAge());
		bookingFlight.setPhoneNumber(user.getPhoneNumber());
		bookingFlight.setBookingFlightDetailsId(user.getBookingFlightDetailsId());
		
		Flight flight = restTemplate.getForEntity("http://localhost:8100/flight/get/"+flightId, Flight.class).getBody();
		
		bookingFlight.setFlightId(flight.getId());
		bookingFlight.setFlightName(flight.getFlightName());
		bookingFlight.setFlightNo(flight.getFlightNo());
		bookingFlight.setSource(flight.getSource());
		bookingFlight.setDestination(flight.getDestination());
		bookingFlight.setDate(flight.getDate());
		bookingFlight.setDepartureTime(flight.getDepartureTime());
		bookingFlight.setArrivalTime(flight.getArrivalTime());
		bookingFlight.setFare(flight.getFare());
		
		
		BookingFlight bookedFlight = bookingService.bookFlight(bookingFlight);
		
		return "Your Booking is confirmed and your reference id is : " + bookedFlight.getId();
	}
	
	
	@GetMapping("/getBooking/{flightId}")
	public ResponseEntity<Object> getBookingForFlight(@PathVariable long flightId){
		List<BookingFlight> list = bookingService.getAllBookingForAFlight(flightId);
		return new ResponseEntity<>(list,HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public Object getBookindById(@PathVariable long id) {
		return bookingService.getBookingById(id);
	}
	
	@GetMapping("/getFlight/{id}")
	public ResponseEntity<Object> getFlightByBooking(@PathVariable long id){
		long flightId = bookingService.getFlightIdByBooking(id);
		return new ResponseEntity<>(flightId,HttpStatus.OK);
	}
	
	
	@PutMapping("/checkedIn/{id}")
	public ResponseEntity<Object> checkedIn(@PathVariable long id){
		BookingFlight b = bookingService.checkedIn(id);
		return new ResponseEntity<>(b,HttpStatus.OK);
	}
	
	@GetMapping("/bookings/{name}")
	public ResponseEntity<Object> getBookingByFlight(@PathVariable String name){
		List<BookingFlight> list = bookingService.getAllBookingByFlightName(name);
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
	
	@GetMapping("/details/{id}")
	public ResponseEntity<?> getBookingDetails(@PathVariable long id){
		List<BookingFlight> list = bookingService.getAllBookingByDetailsId(id);
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	
}
