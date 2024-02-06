package com.fbs.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("flight")
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
