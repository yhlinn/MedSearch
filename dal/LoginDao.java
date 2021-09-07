package dal;

import model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

import java.util.List;
import java.util.ArrayList;

public class LoginDao {
	protected ConnectionManager connectionManager;
	private static LoginDao instance = null;
	
	protected LoginDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static LoginDao getInstance() {
		if (instance == null) {
			instance = new LoginDao();
		}
		return instance;
	}
	
	/**
	 * Validates whether the login credentials exist in the DB.
	 * @param login the login object
	 * @return the customer corresponding to the given login information;
	 * 		   null if the customer does not exist
	 */
	public Customers validateCustomer(Login login) throws SQLException {
		Connection connection = null;
		String selectCustomer = "SELECT * FROM Users INNER JOIN Customers " +
								"ON Users.UserName = Customers.UserName " +
								"WHERE Users.UserName = ? AND Password = ?;";
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectCustomer);
			selectStmt.setString(1, login.getUsername());
			selectStmt.setString(2, login.getPassword());
			results = selectStmt.executeQuery();
			if (results.next()) {
				String resultUsername = results.getString("Users.UserName");
				String resultPassword = results.getString("Password");
				String resultPhone = results.getString("Phone");
				String resultStreet1 = results.getString("Street1");
				String resultStreet2 = results.getString("Street2");
				String resultCity = results.getString("City");
				String resultState = results.getString("State");
				String resultZipcode = results.getString("Zipcode");
				String resultFirstName = results.getString("FirstName");
				String resultLastName = results.getString("LastName");
				int resultAge = results.getInt("Age");
				Customers.Sex resultSex = Customers.Sex.valueOf(results.getString("Sex"));
				Customers customer = new Customers(resultUsername, resultPassword, resultPhone,
												   resultStreet1, resultStreet2, resultCity,
												   resultState, resultZipcode, resultFirstName,
												   resultLastName, resultAge, resultSex);
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
	
	public Doctors validateDoctor(Login login) throws SQLException {
		Connection connection = null;
		String selectDoctor = "SELECT * FROM Users INNER JOIN Doctors " +
								"ON Users.UserName = Doctors.UserName " +
								"WHERE Users.UserName = ? AND Password = ?;";
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectDoctor);
			selectStmt.setString(1, login.getUsername());
			selectStmt.setString(2, login.getPassword());
			results = selectStmt.executeQuery();
			if (results.next()) {
				String resultUsername = results.getString("Users.UserName");
				String resultPassword = results.getString("Password");
				String resultPhone = results.getString("Phone");
				String resultStreet1 = results.getString("Street1");
				String resultStreet2 = results.getString("Street2");
				String resultCity = results.getString("City");
				String resultState = results.getString("State");
				String resultZipcode = results.getString("Zipcode");
				String resultFirstName = results.getString("FirstName");
				String resultLastName = results.getString("LastName");
				Doctors doctor = new Doctors(resultUsername, resultPassword,
									 resultPhone, resultStreet1, resultStreet2,
									 resultCity, resultState, resultZipcode,
									 resultFirstName, resultLastName);
				return doctor;
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
	
	public Pharmacies validatePharmacy(Login login) throws SQLException {
		Connection connection = null;
		String selectPharmacy = "SELECT * FROM Users INNER JOIN Pharmacies " +
								"ON Users.UserName = Pharmacies.UserName " +
								"WHERE Users.UserName = ? AND Password = ?;";
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPharmacy);
			selectStmt.setString(1, login.getUsername());
			selectStmt.setString(2, login.getPassword());
			results = selectStmt.executeQuery();
			if (results.next()) {
				String resultUsername = results.getString("Users.UserName");
				String resultPassword = results.getString("Password");
				String resultPhone = results.getString("Phone");
				String resultStreet1 = results.getString("Street1");
				String resultStreet2 = results.getString("Street2");
				String resultCity = results.getString("City");
				String resultState = results.getString("State");
				String resultZipcode = results.getString("Zipcode");
				String resultPharmacyName = results.getString("PharmacyName");
				String resultHours = results.getString("Hours");
				Time resultOpen = results.getTime("Open");
				Time resultClose = results.getTime("Close");
				Pharmacies pharmacy = new Pharmacies(resultUsername, resultPassword,
													 resultPhone, resultStreet1,
													 resultStreet2, resultCity,
													 resultState, resultZipcode,
													 resultPharmacyName, resultOpen,
													 resultClose);
				return pharmacy;
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