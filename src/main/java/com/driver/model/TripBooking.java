package com.driver.model;

import javax.persistence.*;

@Entity
public class TripBooking{
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private int tripBookingId;

    private String fromLocation;
    private String toLocation;

    private  int distancaInKm;

    private TripStatus status;

    private int bill;

    @ManyToOne
    @JoinColumn
    private Customer customer;

    @ManyToOne
    @JoinColumn
    private Driver driver;

	public int getTripBookingId() {
		return tripBookingId;
	}

	public void setTripBookingId(int tripBookingId) {
		this.tripBookingId = tripBookingId;
	}

	public String getFromLocation() {
		return fromLocation;
	}

	public void setFromLocation(String fromLocation) {
		this.fromLocation = fromLocation;
	}

	public String getToLocation() {
		return toLocation;
	}

	public void setToLocation(String toLocation) {
		this.toLocation = toLocation;
	}

	public int getDistancaInKm() {
		return distancaInKm;
	}

	public void setDistancaInKm(int distancaInKm) {
		this.distancaInKm = distancaInKm;
	}

	public TripStatus getStatus() {
		return status;
	}

	public void setStatus(TripStatus status) {
		this.status = status;
	}

	public int getBill() {
		return bill;
	}

	public void setBill(int bill) {
		this.bill = bill;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public TripBooking(int tripBookingId, String fromLocation, String toLocation, int distancaInKm, TripStatus status,
			int bill) {
		super();
		this.tripBookingId = tripBookingId;
		this.fromLocation = fromLocation;
		this.toLocation = toLocation;
		this.distancaInKm = distancaInKm;
		this.status = status;
		this.bill = bill;
	}

	public TripBooking() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TripBooking(int tripBookingId, String fromLocation, String toLocation, int distancaInKm, TripStatus status,
			int bill, Customer customer, Driver driver) {
		super();
		this.tripBookingId = tripBookingId;
		this.fromLocation = fromLocation;
		this.toLocation = toLocation;
		this.distancaInKm = distancaInKm;
		this.status = status;
		this.bill = bill;
		this.customer = customer;
		this.driver = driver;
	}
    
    
}