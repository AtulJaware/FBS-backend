package com.bok.ser.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bok.ser.entity.BookingFlight;
import com.bok.ser.entity.User;

@Service
public interface BookingService {
	
	 BookingFlight bookFlight(BookingFlight bookingFlight);
	
	 BookingFlight getBookingById(long bookingId);
	
	 BookingFlight checkedIn(long id) ;
	 
	 List<BookingFlight> getAllBookingForAFlight(long flightId);
	 
	 long getFlightIdByBooking(long bookingId);
	 
	 List<BookingFlight> getAllBookingByFlightName(String name);
	 
	 List<BookingFlight> getAllBookingByDetailsId(long id);
}

