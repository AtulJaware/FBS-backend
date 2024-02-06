package com.bok.ser.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bok.ser.entity.BookingFlight;

@Repository
public interface BookingRepository extends MongoRepository<BookingFlight, Long> {

	List<BookingFlight> findByFlightId(long flightId);
	
	List<BookingFlight> findByFlightNo(String name);
	
	List<BookingFlight> findAllByBookingFlightDetailsId(long id);	

	
}
