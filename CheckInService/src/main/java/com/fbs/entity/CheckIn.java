package com.fbs.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("checkedPassengers")
public class CheckIn{
	
	
	@Transient
	public static final String SEQUENCE_NAME = "checkIn_sequence";

	@Id
	private long id;
	private long bookingId;
	private long flightId;
	
}
