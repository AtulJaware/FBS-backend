package com.fbs.entity;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("booking")
public class BookingFlight {

	@Id
	private long id;
	private long flightId;
	private String flightName;
	private String flightNo;
	private String source;
	private String destination;
	private String date;
	private String departureTime;
	private String arrivalTime;
	private long fare;
	private String firstName;
	private String lastName;
	private String gender;
	private boolean isCheckedIn;
}
