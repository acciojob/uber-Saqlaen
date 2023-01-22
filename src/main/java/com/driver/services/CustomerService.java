package com.driver.services;


import java.util.List;

import com.driver.model.Customer;
import com.driver.model.Driver;
import com.driver.model.TripBooking;


public interface CustomerService {

	public void register(Customer customer);

	public void deleteCustomer(Integer customerId);
	
	public TripBooking bookTrip(Integer customerId, String fromLocation, String toLocation, Integer distanceInKm) throws Exception;
	
	public void cancelTrip(Integer tripId);

	public void completeTrip(Integer tripId);
	
}
