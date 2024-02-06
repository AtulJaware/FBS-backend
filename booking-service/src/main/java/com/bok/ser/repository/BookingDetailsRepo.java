package com.bok.ser.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bok.ser.entity.BookingFlightDetails;

public interface BookingDetailsRepo extends MongoRepository<BookingFlightDetails, Long>{

	Optional<List<BookingFlightDetails>> findAllByUserId(long userId);
	
}
