package model;


public class Customers extends Users {
	protected String firstName;
	protected String lastName;
	protected int age;
	protected Sex sex;
	
	public enum Sex {
		MALE, FEMALE
	}

	// all-fields constructor
	public Customers(String userName, String password, String phone, String street1, String street2, String city,
			String state, String zipcode, String firstName, String lastName, int age, Sex sex) {
		super(userName, password, phone, street1, street2, city, state, zipcode);
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.sex = sex;
	}

	// PK-only constructor
	public Customers(String userName) {
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}
}