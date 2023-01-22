package com.driver.services.impl;

import com.driver.model.Cab;
import com.driver.repository.CabRepository;
import com.driver.services.DriverService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.driver.model.Driver;
import com.driver.repository.DriverRepository;

@Service
public class DriverServiceImpl implements DriverService {

	@Autowired
	DriverRepository driverRepository3;

	@Autowired
	CabRepository cabRepository3;

	@Override
	public void register(String mobile, String password){
		//Save a driver in the database having given details and a cab with ratePerKm as 10 and availability as True by default.
		Driver driver = new Driver();
		driver.setMobile(mobile);
		driver.setPassword(password);
		
		Cab driversCab = new Cab();
		driversCab.setAvailable(true);
		driversCab.setPerKmRate(10);
		
		this.cabRepository3.save( driversCab );
		
		driver.setCab(driversCab);
		
		this.driverRepository3.save(driver);
	}

	@Override
	public void removeDriver(int driverId){
		// Delete driver without using deleteById function
		Optional<Driver> d = this.driverRepository3.findById( driverId );
		Driver toBeremoved = d.get();
		this.driverRepository3.delete( toBeremoved );

	}

	@Override
	public void updateStatus(int driverId){
		//Set the status of respective car to unavailable
		Optional<Driver> d = this.driverRepository3.findById( driverId );
		Driver toBeremoved = d.get();
		
		Cab driversCab = toBeremoved.getCab();
		driversCab.setAvailable(false);
		
		this.cabRepository3.save( driversCab );
		this.driverRepository3.save( toBeremoved );

	}
}
