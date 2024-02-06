package com.bok.ser.controller;

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

import com.bok.ser.entity.BookingFlightDetails;
import com.bok.ser.service.BookingDetailsServiceImpl;

@RestController
@RequestMapping("/booking")
@CrossOrigin(origins="*")
public class BookingDetailsController {
	
	@Autowired
	private BookingDetailsServiceImpl bookingService;
	
	@PostMapping("/bookingDetails/book")
	public ResponseEntity<?> addBookingDetails(@RequestBody BookingFlightDetails bookingFlightDetails){
		BookingFlightDetails bfd=bookingService.addFlightDetails(bookingFlightDetails);
		return new ResponseEntity<>(bfd,HttpStatus.CREATED); 
	}

	
	@GetMapping("/bookingDetails/{userId}")
	public ResponseEntity<?> getBookingDetails(@PathVariable long userId){
		List<BookingFlightDetails> list =bookingService.getBookingDetails(userId);
		return new ResponseEntity<>(list,HttpStatus.CREATED); 
	}
	
}
