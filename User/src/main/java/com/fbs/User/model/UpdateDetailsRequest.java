package com.fbs.User.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateDetailsRequest {
	
	private long id;

	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String username;
	
	private String gender;
	
	
}
