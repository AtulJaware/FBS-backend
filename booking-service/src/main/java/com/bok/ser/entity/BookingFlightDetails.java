package com.bok.ser.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class BookingFlightDetails {

	@Transient
	public static final String SEQUENCE_NAME = "users_sequence";
	
	
	@Id
	private long id;
	private long userId;
	private long quantity;
	private long totalFare;
}
