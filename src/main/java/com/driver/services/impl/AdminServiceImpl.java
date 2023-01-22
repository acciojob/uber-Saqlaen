package com.driver.services.impl;

import java.util.List;
import java.util.Optional;

import com.driver.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.driver.model.Admin;
import com.driver.model.Customer;
import com.driver.model.Driver;
import com.driver.repository.AdminRepository;
import com.driver.repository.CustomerRepository;
import com.driver.repository.DriverRepository;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminRepository adminRepository1;

	@Autowired
	DriverRepository driverRepository1;

	@Autowired
	CustomerRepository customerRepository1;

	@Override
	public void adminRegister(Admin admin) {
		//Save the admin in the database
		this.adminRepository1.save( admin );
	}

	@Override
	public Admin updatePassword(Integer adminId, String password) {
		//Update the password of admin with given id
		Optional<Admin> updateAdmin = this.adminRepository1.findById( adminId );
		Admin admin = updateAdmin.get();
		admin.setPassword(password);
		this.adminRepository1.save( admin );
		return admin;
	}

	@Override
	public void deleteAdmin(Integer adminId){
		// Delete admin without using deleteById function
		Optional<Admin> adminToDelete = this.adminRepository1.findById( adminId );
		Admin admin = adminToDelete.get();
		this.adminRepository1.delete(admin);
		
	}

	@Override
	public List<Driver> getListOfDrivers() {
		//Find the list of all drivers
		List<Driver> drivers = this.driverRepository1.findAll();
		return drivers;

	}

	@Override
	public List<Customer> getListOfCustomers() {
		//Find the list of all customers
		List<Customer> customers = this.customerRepository1.findAll();
		return customers;
	}

}
