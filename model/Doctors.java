package model;


public class Doctors extends Users {
	protected String firstName;
	protected String lastName;
	
	// all-fields constructor
	public Doctors(String userName, String password, String phone, String street1, String street2, String city,
			String state, String zipcode, String firstName, String lastName) {
		super(userName, password, phone, street1, street2, city, state, zipcode);
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	// PK-only constructor
	public Doctors(String userName) {
		super(userName);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}