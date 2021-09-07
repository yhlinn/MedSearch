package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Customers;
import model.Doctors;
import model.Users;

public class CustomersDao extends UsersDao {
	private static CustomersDao instance = null;
	
	protected CustomersDao() {
		super();
	}
	
	public static CustomersDao getInstance() {
		if(instance == null) {
			instance = new CustomersDao();
		}
		return instance;
	}
	
	public Customers create(Customers customer) throws SQLException {
		create(new Users(customer.getUserName(), customer.getPassword(),
						 customer.getPhone(), customer.getStreet1(), customer.getStreet2(), 
						 customer.getCity(), customer.getState(), customer.getZipcode()));
		
		String insertDoctor =
			"INSERT INTO Customers(UserName, FirstName, LastName, Age, Sex) " +
			"VALUES(?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertDoctor);
			insertStmt.setString(1, customer.getUserName());
			insertStmt.setString(2, customer.getFirstName());
			insertStmt.setString(3, customer.getLastName());
			insertStmt.setInt(4, customer.getAge());
			insertStmt.setString(5, customer.getSex().name());
			insertStmt.executeUpdate();
			
			return customer;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
			
		}
	}
	
	/**
	 * Updates an existing customer with new field values in the given updated
	 * customer. The fields of the existing customer will be replaced with the
	 * new values in the DB, and the customer itself will also be updated.
	 * 
	 * @param customer the existing customer object
	 * @param updatedCustomer the customer object with update field values
	 * @return the existing customer object with updated field values
	 * @throws SQLException
	 */
	public Customers update(Customers customer, Customers updatedCustomer)
			throws SQLException {
		// perform update on the Users class
		// this updates the basic information that is stored in the super class
		// such as password, phone, address...etc
		customer = (Customers) super.update(customer, updatedCustomer);
		
		// perform update on the Customers class
		String updateCustomer = "UPDATE Customers SET FirstName=?, LastName=?, " +
								"Age=?, Sex=? WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateCustomer);
			updateStmt.setString(1, updatedCustomer.getFirstName());
			updateStmt.setString(2, updatedCustomer.getLastName());
			updateStmt.setInt(3, updatedCustomer.getAge());
			updateStmt.setString(4, updatedCustomer.getSex().name());
			updateStmt.setString(5, customer.getUserName());
			updateStmt.executeUpdate();
			
			// update the customer before returning
			customer.setFirstName(updatedCustomer.getFirstName());
			customer.setLastName(updatedCustomer.getLastName());
			customer.setAge(updatedCustomer.getAge());
			customer.setSex(updatedCustomer.getSex());
			
			return customer;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(updateStmt != null) {
				updateStmt.close();
			}
		}
	}
	
	
	public Customers delete(Customers customer) throws SQLException {
		String deleteCustomer = "DELETE FROM Customers WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteCustomer);
			deleteStmt.setString(1, customer.getUserName());
			deleteStmt.executeUpdate();
			super.delete(customer);
	
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}
	
	
	public Customers getCustomerFromUserName(String userName) throws SQLException {
		String selectDoctor =
			"SELECT Customers.UserName AS UserName, FirstName, LastName, Age, Sex, Password, Phone, Street1, Street2, City, State, Zipcode " +
			"FROM Customers INNER JOIN Users " +
			"ON Customers.UserName = Users.UserName " +
			"WHERE Customers.UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectDoctor);
			selectStmt.setString(1, userName);
			results = selectStmt.executeQuery();
			if(results.next()) {
				String resultUserName = results.getString("UserName");
				String firstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
				int age = results.getInt("Age");
				Customers.Sex sex = Customers.Sex.valueOf(
						results.getString("Sex"));
				String password = results.getString("Password");
				String phone = results.getString("Phone");
				String street1 = results.getString("Street1");
				String street2 = results.getString("Street2");
				String city = results.getString("City");
				String state = results.getString("State");
				String zipcode = results.getString("Zipcode");
	
	
				Customers customer = new Customers(resultUserName, password, phone, street1, street2, city,
						state, zipcode, firstName, lastName, age, sex);
				
				return customer;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return null;
	}

}
