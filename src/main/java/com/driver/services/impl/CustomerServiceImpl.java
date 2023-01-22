package com.driver.services.impl;

import com.driver.model.TripBooking;
import com.driver.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.driver.model.Cab;
import com.driver.model.Customer;
import com.driver.model.Driver;
import com.driver.repository.CustomerRepository;
import com.driver.repository.DriverRepository;
import com.driver.repository.TripBookingRepository;
import com.driver.model.TripStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository2;

	@Autowired
	DriverRepository driverRepository2;

	@Autowired
	TripBookingRepository tripBookingRepository2;

	@Override
	public void register(Customer customer) {
		//Save the customer in database
		this.customerRepository2.save( customer );
	}

	@Override
	public void deleteCustomer(Integer customerId) {
		// Delete customer without using deleteById function
		Optional<Customer> find = this.customerRepository2.findById( customerId );
		Customer customer = find.get();
		this.customerRepository2.delete(customer);
	}

	@Override
	public TripBooking bookTrip(Integer customerId, String fromLocation, String toLocation, Integer distanceInKm) throws Exception{
		//Book the driver with lowest driverId who is free (cab available variable is Boolean.TRUE). If no driver is available, throw "No cab available!" exception
		//Avoid using SQL query
		TripBooking trip = new TripBooking();
		trip.setFromLocation(fromLocation);
		trip.setToLocation(toLocation);
		trip.setDistanceInKm( distanceInKm );
		
		Optional<Customer> c = this.customerRepository2.findById(customerId);
		Customer customer = c.get();
		
		trip.setCustomer(customer);
		
		// get all drivers and search his availability
		List<Driver> drivers = this.driverRepository2.findAll();
		Driver tripDriver = null;
		boolean availableFlag = false;
		for( Driver driver : drivers ) {
			Cab driverCab = driver.getCab();
			if( driverCab.getAvailable() ) {
				tripDriver = driver;
				availableFlag = true;
				driverCab.setAvailable(false);
			}
		}
		
		if( availableFlag == false ) {
			throw new Exception("No value present");
		}
		
		trip.setDriver(tripDriver);
		trip.setStatus( TripStatus.CONFIRMED);
		
		this.tripBookingRepository2.save(trip);
		return trip;

	}

	@Override
	public void cancelTrip(Integer tripId){
		//Cancel the trip having given trip Id and update TripBooking attributes accordingly
		Optional<TripBooking> t = this.tripBookingRepository2.findById(tripId);
		TripBooking cancelTrip = t.get();
		cancelTrip.setStatus( TripStatus.CANCELED );
		Driver canceled = cancelTrip.getDriver();
		Cab canceledCab = canceled.getCab();
		canceledCab.setAvailable(true);
		this.tripBookingRepository2.save( cancelTrip );

	}

	@Override
	public void completeTrip(Integer tripId){
		//Complete the trip having given trip Id and update TripBooking attributes accordingly
		Optional<TripBooking> t = this.tripBookingRepository2.findById(tripId);
		TripBooking completeTrip = t.get();
		completeTrip.setStatus( TripStatus.COMPLETED );
		Driver completed = completeTrip.getDriver();
		Cab completedCab = completed.getCab();
		completedCab.setAvailable(true);
		this.tripBookingRepository2.save( completeTrip );

	}
}
