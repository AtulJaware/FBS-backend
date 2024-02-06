package com.bok.ser.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bok.ser.entity.BookingFlight;
import com.bok.ser.entity.BookingFlightDetails;
import com.bok.ser.exception.FlightNotFoundException;
import com.bok.ser.repository.BookingDetailsRepo;

@Service
public class BookingDetailsServiceImpl implements BookingDetailsService{
	
	@Autowired
	private BookingDetailsRepo bookingRepo;
	
	@Autowired
	private SequenceGeneratorService sequence;

	@Override
	public BookingFlightDetails addFlightDetails(BookingFlightDetails bookingFlightDetails) {
		bookingFlightDetails.setId(sequence.generateSequence(BookingFlightDetails.SEQUENCE_NAME));
		return  bookingRepo.save(bookingFlightDetails);  
	}

	@Override
	public List<BookingFlightDetails> getBookingDetails(long userId) {
		List<BookingFlightDetails> list = bookingRepo.findAllByUserId(userId).orElseThrow(()->new FlightNotFoundException("No bookings with this "+userId));
		return list;
	}

}
