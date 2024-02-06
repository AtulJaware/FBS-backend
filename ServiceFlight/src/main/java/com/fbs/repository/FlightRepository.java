package com.fbs.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fbs.entity.Flight;

public interface FlightRepository extends MongoRepository<Flight, Long>{

}