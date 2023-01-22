package com.driver.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer{

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private int customerId;

    private String mobile;
    private String password;

    @OneToMany( mappedBy = "customer", cascade = CascadeType.ALL )
    List<TripBooking> tripBookingList = new ArrayList<>();

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobileNumber) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<TripBooking> getTripBookingList() {
		return tripBookingList;
	}

	public void setTripBookingList(List<TripBooking> tripBookingList) {
		this.tripBookingList = tripBookingList;
	}

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(int customerId, String mobileNumber, String password, List<TripBooking> tripBookingList) {
		super();
		this.customerId = customerId;
		this.mobile = mobileNumber;
		this.password = password;
		this.tripBookingList = tripBookingList;
	}

	public Customer(int customerId, String mobileNumber, String password) {
		super();
		this.customerId = customerId;
		this.mobile = mobileNumber;
		this.password = password;
	}
    
    

}