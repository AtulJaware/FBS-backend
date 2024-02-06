package com.fbs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.fbs.entity.CheckIn;
import com.fbs.exception.FlightNotFoundException;
import com.fbs.repository.CheckedInRepository;


@Service
public class CheckedInServiceImpl implements CheckedInService {

	@Autowired
	private CheckedInRepository checkInRepo;
	
	@Autowired
	private SequenceGeneratorService sequence;

	@Override
	public String flightCheckIn(CheckIn checkIn) {

		checkIn.setId(sequence.generateSequence(CheckIn.SEQUENCE_NAME));
		checkInRepo.save(checkIn);
		return "You are checkedIn and your checkInId is "+checkIn.getId();
	}

	@Override
	public CheckIn getCheckIn(long id) {

		CheckIn checkIn = checkInRepo.findById(id).orElseThrow(() -> new FlightNotFoundException("Passengers Not Found"));

		return checkIn;
	}

	@Override
	public List<CheckIn> getAllCheckedPassengers() {
		List<CheckIn> list = checkInRepo.findAll();
		if(list==null) {
			throw new FlightNotFoundException("No passengers are available");
		}
		return list;
	}

	@Override
	public List<CheckIn> getAllCheckedPassengersForFlight(long flightId) {
		// TODO Auto-generated method stub
		List<CheckIn> list = checkInRepo.findByFlightId(flightId);
		if(list==null) {
			throw new FlightNotFoundException("No passengers are available");
		}
		return list;
	}

}
