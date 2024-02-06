package com.bok.ser.entity;


import java.time.LocalDate;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Flight {

	@Id
	private long id;

	private String flightName;
	
	private String flightNo;

	private String source;

	private String destination;

	private String date;
	
	private String departureTime;
	
	private String arrivalTime;

	private long fare;
}
