package model;

import java.sql.Time;


public class Pharmacies extends Users {
	protected String pharmacyName;
	protected Time openTime;
	protected Time closeTime;
	
	// all-fields constructor
	public Pharmacies(String userName, String password, String phone, String street1, String street2, String city,
			String state, String zipcode, String pharmacyName, Time openTime, Time closeTime) {
		super(userName, password, phone, street1, street2, city, state, zipcode);
		this.pharmacyName = pharmacyName;
		this.openTime = openTime;
		this.closeTime = closeTime;
	}
	
	// PK-only constructor
	public Pharmacies(String userName) {
		super(userName);
	}

	public String getPharmacyName() {
		return pharmacyName;
	}

	public void setPharmacyName(String pharmacyName) {
		this.pharmacyName = pharmacyName;
	}

	public Time getOpenTime() {
		return openTime;
	}

	public void setOpenTime(Time openTime) {
		this.openTime = openTime;
	}

	public Time getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(Time closeTime) {
		this.closeTime = closeTime;
	}
}