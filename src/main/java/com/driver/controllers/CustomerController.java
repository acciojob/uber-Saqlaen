package com.driver.controllers;

import com.driver.model.Customer;
import com.driver.model.TripBooking;
import com.driver.services.CustomerService;
import com.driver.services.impl.CustomerServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	CustomerService customerservice;
	
	@PostMapping("/register")
	public ResponseEntity<Void> registerCustomer(@RequestBody Customer customer){
		this.customerservice.register(customer);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/delete")
	public void deleteCustomer(@RequestParam Integer customerId){
		this.customerservice.deleteCustomer(customerId);
	}

	@PostMapping("/bookTrip")
	public ResponseEntity<Integer> bookTrip(@RequestParam Integer customerId, @RequestParam String fromLocation, @RequestParam String toLocation, @RequestParam Integer distanceInKm) throws Exception {
		TripBooking bookedTrip = this.customerservice.bookTrip(customerId, fromLocation, toLocation, distanceInKm );
		return new ResponseEntity<>(bookedTrip.getTripBookingId(), HttpStatus.CREATED);
	}

	@DeleteMapping("/complete")
	public void completeTrip(@RequestParam Integer tripId){
		this.customerservice.completeTrip(tripId);
	}

	@DeleteMapping("/cancelTrip")
	public void cancelTrip(@RequestParam Integer tripId){
		this.customerservice.cancelTrip(tripId);
	}
}
