package com.fbs.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.fbs.entity.Flight;

@Repository
public interface FlightRepository extends MongoRepository<Flight, Long>{

	

	@Query("{ 'source': ?0, 'destination': ?1, 'date': ?2 }")
    Optional<List<Flight>> findBySourceDestinationAndDate(String source, String destination,String date);
	
	
}
