package com.fbs.User.service;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fbs.User.entity.Passengers;
import com.fbs.User.exception.PassengerNotFoundException;
import com.fbs.User.model.UpdateDetailsRequest;
import com.fbs.User.model.UpdatePasswordRequest;
import com.fbs.User.repo.PassengerRepository;

@Service
public class PassengerService {

	@Autowired
	private PassengerRepository passengerRepo;
	
	
	
	private final SecureRandom random = new SecureRandom();
	private final long OTP_VALID_DURATION = 5 * 60 * 1000;
	
	

	public Passengers logIn(String username, String password) {

		Passengers passenger = passengerRepo.findByUsername(username)
				.orElseThrow(() -> new PassengerNotFoundException("User Not found with username "+username));

		if (!passenger.getPassword().equals(password)) {
			throw new PassengerNotFoundException("Invalid password");
		}

		return passenger;

	}
	
	public List<Passengers> getAllPassenger(){
		return passengerRepo.findAll();
	}
	public Passengers addPassenger(Passengers passenger) {
		return passengerRepo.save(passenger);
	}
//	
//	public String sendOtp(Passengers passenger) {
//		String otp = String.format("%04d", random.nextInt(10000)); // 4-digit OTP
//
//        Optional<OtpEntity> entity = otpRepo.findByEmail(passenger.getEmail());
//     
//        if(entity.isEmpty()) {
//        	OtpEntity otpEntity = new OtpEntity();
//            otpEntity.setEmail(passenger.getEmail());
//            otpEntity.setOtp(otp);
//            otpEntity.setTimestamp(System.currentTimeMillis());
//
//            otpRepo.save(otpEntity);
//        }
//        else {
//        	OtpEntity otpEntity = entity.get();
//        	otpEntity.setOtp(otp);
//        	otpEntity.setTimestamp(System.currentTimeMillis());
//        	otpRepo.save(otpEntity);
//        }
//        
//        return "Otp is sent to your email";
//	}
//	
//	
//	public boolean validateOtp(Passengers passenger) throws Exception {
//		//OtpEntity otpEntity = otpData.get(email);
//		
//		OtpEntity otpEntity = otpRepo.findByEmail(passenger.getEmail()).get();
//
//        if (otpEntity == null) {
//            throw new Exception();
//        }
//
//        long currentTimestamp = System.currentTimeMillis();
//
//        if (currentTimestamp - otpEntity.getTimestamp() > OTP_VALID_DURATION) {
//            return false; // OTP has expired
//        }
//
//        if(otp.equals(otpEntity.getOtp())) {
//        	otpRepo.deleteByEmail(passenger.getEmail());
//        	addPassenger(null)
//        	return true;
//        }
//        else {
//        	return false;
//        }
//	}
	
	
	public Passengers updatePassenger(Passengers passenger) {
		passengerRepo.findById(passenger.getId()).orElseThrow(()->new PassengerNotFoundException("No passenger with this username"));
		return passengerRepo.save(passenger);
	}
	
	
	public String UpdatePasswordRequest(UpdatePasswordRequest updatePasswordRequest,long id) {
		Passengers p = passengerRepo.findById(id).orElseThrow(()->new PassengerNotFoundException("No passenger with this username"));
		
		if(!p.getPassword().equals(updatePasswordRequest.getOldPassword())) {
			throw new PassengerNotFoundException("Invalid Password");
		}
		p.setPassword(updatePasswordRequest.getNewPassword());
		passengerRepo.save(p);
		return "Password is updated";
	}
	
	public Passengers UpdateUserDetails(UpdateDetailsRequest updateDetailsRequest) {
		Passengers p = passengerRepo.findById(updateDetailsRequest.getId()).orElseThrow(()->new PassengerNotFoundException("No passenger with this username"));
		
			p.setUsername(updateDetailsRequest.getUsername());
			p.setFirstName(updateDetailsRequest.getFirstName());
			p.setLastName(updateDetailsRequest.getLastName());
			p.setEmail(updateDetailsRequest.getEmail());
			p.setGender(updateDetailsRequest.getGender());
		
		return passengerRepo.save(p);
	
	}
	
	
	
	public String deletePassenger(String username) {
		passengerRepo.findByUsername(username).orElseThrow(()->new PassengerNotFoundException("No passenger with this username"));
		passengerRepo.deleteByUsername(username);
		return "Successfully Deleted";
	}
	
	
}
