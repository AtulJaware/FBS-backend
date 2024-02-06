package com.fbs.repository;

import java.util.List;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.fbs.entity.CheckIn;

public interface CheckedInRepository extends MongoRepository<CheckIn, Long>{

	List<CheckIn> findByFlightId(long flightId);
}
