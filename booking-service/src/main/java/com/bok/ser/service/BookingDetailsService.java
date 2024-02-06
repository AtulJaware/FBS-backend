package com.bok.ser.service;

import java.util.List;

import com.bok.ser.entity.BookingFlightDetails;

public interface BookingDetailsService {
	
	BookingFlightDetails addFlightDetails(BookingFlightDetails bookingFlightDetails);
	
	List<BookingFlightDetails> getBookingDetails(long userId);

}
