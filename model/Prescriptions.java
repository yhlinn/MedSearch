package model;

import java.util.Date;


public class Prescriptions {
	protected int prescriptionId;
	protected String customerUserName;
	protected String drugId;
	protected Date fillDate;
	protected String doctorUserName;
	
	// all-fields constructor
	public Prescriptions(int prescriptionId, String customerUserName, String drugId, Date fillDate, String doctorUserName) {
		this.prescriptionId = prescriptionId;
		this.customerUserName = customerUserName;
		this.drugId = drugId;
		this.fillDate = fillDate;
		this.doctorUserName = doctorUserName;
	}

	// PK-only constructor
	public Prescriptions(int prescriptionId) {
		this.prescriptionId = prescriptionId;
	}

	// no-PK constructor
	public Prescriptions(String customerUserName, String drugId, Date fillDate, String doctorUserName) {
		this.customerUserName = customerUserName;
		this.drugId = drugId;
		this.fillDate = fillDate;
		this.doctorUserName = doctorUserName;
	}
	
	public Prescriptions(String customerUserName, String drugId,  String doctorUserName) {
		this.customerUserName = customerUserName;
		this.drugId = drugId;
		this.fillDate = new Date();
		this.doctorUserName = doctorUserName;
	}


	public int getPrescriptionId() {
		return prescriptionId;
	}

	public void setPrescriptionId(int prescriptionId) {
		this.prescriptionId = prescriptionId;
	}

	public String getCustomerUserName() {
		return customerUserName;
	}

	public void setCustomerUserName(String customerUserName) {
		this.customerUserName = customerUserName;
	}

	public String getDrugId() {
		return drugId;
	}

	public void setDrugId(String drugId) {
		this.drugId = drugId;
	}

	public Date getFillDate() {
		return fillDate;
	}

	public void setFillDate(Date fillDate) {
		this.fillDate = fillDate;
	}

	public String getDoctorUserName() {
		return doctorUserName;
	}

	public void setDoctorUserName(String doctorUserName) {
		this.doctorUserName = doctorUserName;
	}
	

}