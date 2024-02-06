package com.bok.ser.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bok.ser.entity.BookingFlight;
import com.bok.ser.entity.User;
import com.bok.ser.exception.FlightNotFoundException;
import com.bok.ser.repository.BookingRepository;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired 
	private BookingRepository bookingRepo;
	
	@Autowired
	private SequenceGeneratorService sequence;
	
	@Override
	public BookingFlight bookFlight(BookingFlight bookingFlight) {
		Optional<BookingFlight> book = bookingRepo.findById(bookingFlight.getId());
		if(book.isPresent()) { 
			throw new FlightNotFoundException("Already Booked with this "+bookingFlight.getId());
		}
		bookingFlight.setId(sequence.generateSequence(BookingFlight.SEQUENCE_NAME));
		return bookingRepo.save(bookingFlight);
	}

	@Override
	public BookingFlight getBookingById(long bookingId) {
		return bookingRepo.findById(bookingId).get();
	}

	@Override
	public BookingFlight checkedIn(long id){
		BookingFlight b = bookingRepo.findById(id).orElseThrow(()->new FlightNotFoundException("Flight Not found"));
		b.setCheckedIn(true);
		return bookingRepo.save(b);
	}

	@Override
	public List<BookingFlight> getAllBookingForAFlight(long flightId) {
		List<BookingFlight> list = bookingRepo.findByFlightId(flightId);
		if(list == null) {
			throw new FlightNotFoundException("No booking is available");
		}
		return list;
	}

	@Override
	public long getFlightIdByBooking(long bookingId) {
		// TODO Auto-generated method stub
		BookingFlight book = bookingRepo.findById(bookingId).orElseThrow(()->new FlightNotFoundException("No flight with this id"));
		return book.getFlightId();
	}

	@Override
	public List<BookingFlight> getAllBookingByFlightName(String name) {
	    List<BookingFlight> list = bookingRepo.findByFlightNo(name);
	    if(list==null) {
	    	throw new FlightNotFoundException("No bookings are done yet!!");
	    }
		return list;
	}

	@Override
	public List<BookingFlight> getAllBookingByDetailsId(long id) {
		List<BookingFlight> list = bookingRepo.findAllByBookingFlightDetailsId(id);
		return list;
	}

}
