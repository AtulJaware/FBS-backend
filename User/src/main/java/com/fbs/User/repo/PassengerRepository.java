package com.fbs.User.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fbs.User.entity.Passengers;


@Repository
public interface PassengerRepository extends JpaRepository<Passengers, Long>{

	Optional<Passengers> findByUsername(String username);

	void deleteByUsername(String username);
	
}
