package com.fbs.User.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fbs.User.entity.Passengers;
import com.fbs.User.model.UpdateDetailsRequest;
import com.fbs.User.model.UpdatePasswordRequest;
import com.fbs.User.service.PassengerService;



@RestController
@RequestMapping("/user/passenger")
@CrossOrigin(origins = "*")
public class PassengerController {

	@Autowired
	private PassengerService service;

	
	@GetMapping("/login/{username}/{password}")
	@PreAuthorize("hasRole('Passenger')")
	public ResponseEntity<?> passengerLogin(@PathVariable String username, @PathVariable String password) {
		Passengers passenger = service.logIn(username, password);

		return new ResponseEntity<>(passenger, HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<Object> addPassenger(@RequestBody Passengers passenger) {
		Passengers newPassenger = service.addPassenger(passenger);
		return new ResponseEntity<>(newPassenger, HttpStatus.CREATED);
	}


	@PutMapping("/update")
	@PreAuthorize("hasRole('Passenger')")
	public ResponseEntity<Object> updatePassenger(@RequestBody Passengers passenger) {
		Passengers newPassenger = service.addPassenger(passenger);
		return new ResponseEntity<>(newPassenger, HttpStatus.CREATED);
	}
	
	
	@DeleteMapping("/passenger/delete")
	@PreAuthorize("hasAnyRole('PASSENGER','ADMIN')")
	public ResponseEntity<Object> deletePassenger(@PathVariable String username) {
		service.deletePassenger(username);
		return new ResponseEntity<>("Successfully Deleted", HttpStatus.CREATED);
	}
	
	@PatchMapping("/password/update/{id}")
	@PreAuthorize("hasRole('Passenger')")
	public ResponseEntity<Object> updatePassword(@RequestBody UpdatePasswordRequest updatePasswordRequest,@PathVariable long id ){
		service.UpdatePasswordRequest(updatePasswordRequest, id);
		return new ResponseEntity<>("Password updated successfully",HttpStatus.OK);
	}
	
	@PatchMapping("/details/update")
	@PreAuthorize("hasRole('Passenger')")
	public ResponseEntity<Object> updatePassenger(@RequestBody UpdateDetailsRequest updateDetailsRequest){
		Passengers p=service.UpdateUserDetails(updateDetailsRequest);
		return new ResponseEntity<>(p,HttpStatus.OK);
	}
	
	
}
