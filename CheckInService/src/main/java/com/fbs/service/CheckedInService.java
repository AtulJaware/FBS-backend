package com.fbs.service;

import java.util.List;

import com.fbs.entity.CheckIn;

public interface CheckedInService {

	CheckIn getCheckIn(long id);
	
	String flightCheckIn(CheckIn checkIn);
	
	List<CheckIn> getAllCheckedPassengers();
	
	List<CheckIn> getAllCheckedPassengersForFlight(long flightId);
	
}
