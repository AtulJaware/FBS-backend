package com.bok.ser.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	
	@Transient
	public static final String SEQUENCE_NAME = "users_sequence";

	@Id
	private long id;
	private String firstName;
	private String lastName;
	private String gender;
	private long age;
	private long phoneNumber;
	private long bookingFlightDetailsId;
}
