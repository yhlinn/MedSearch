package dal;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.*;

public class DoctorsDao extends UsersDao {
	private static DoctorsDao instance = null;
	protected DoctorsDao() {
		super();
	}
	
	public static DoctorsDao getInstance() {
		if(instance == null) {
			instance = new DoctorsDao();
		}
		return instance;
	}
	
	public Doctors create(Doctors doctor) throws SQLException {
		create(new Users(doctor.getUserName(), doctor.getPassword(), doctor.getPhone(), doctor.getStreet1(), doctor.getStreet2(), 
				doctor.getCity(), doctor.getState(), doctor.getZipcode()));
		
		String insertDoctor =
			"INSERT INTO Doctors(UserName, FirstName, LastName) VALUES(?,?, ?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertDoctor);
			insertStmt.setString(1, doctor.getUserName());
			insertStmt.setString(2, doctor.getFirstName());
			insertStmt.setString(3, doctor.getLastName());
			
			insertStmt.executeUpdate();
			
			return doctor;
			
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
	
	public Doctors update(Doctors doctor, Doctors updatedDoctor)
			throws SQLException {
		// perform update on the Users class
		// this updates the basic information that is stored in the super class
		// such as password, phone, address...etc
		doctor = (Doctors) super.update(doctor, updatedDoctor);
		
		// perform update on the Doctors class
		String updateDoctor = "UPDATE Doctors SET FirstName=?, LastName=? " +
							  "WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateDoctor);
			updateStmt.setString(1, updatedDoctor.getFirstName());
			updateStmt.setString(2, updatedDoctor.getLastName());
			updateStmt.setString(3, doctor.getUserName());
			updateStmt.executeUpdate();
			
			// update the doctor before returning
			doctor.setFirstName(updatedDoctor.getFirstName());
			doctor.setLastName(updatedDoctor.getLastName());
			
			return doctor;
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
	
	
	public Doctors getDoctorFromUserName(String username) throws SQLException {
		String selectDoctor =
	
			"SELECT Doctors.UserName AS UserName, FirstName, LastName, "
			+ "Password, Phone, Street1, Street2, City, State, Zipcode "
			+ "FROM Doctors INNER JOIN Users ON Doctors.UserName = Users.UserName WHERE Doctors.UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectDoctor);
			selectStmt.setString(1, username);
			results = selectStmt.executeQuery();
			if(results.next()) {
				String resultUserName = results.getString("UserName");
				String password = results.getString("Password");
				String phone = results.getString("Phone");
				String street1 = results.getString("Street1");
				String street2 = results.getString("Street2");
				String city = results.getString("City");
				String state = results.getString("State");
				String zipcode = results.getString("Zipcode");
				String firstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
	
	
				Doctors doctor = new Doctors(resultUserName, password,
						phone, street1, street2, city, state, zipcode, firstName, lastName);
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
	
	
	
	public List<Doctors> getDoctorFromLastName(String lastname) throws SQLException {
		List<Doctors> doctors = new ArrayList<Doctors>();
	
		String selectDoctor =
	
			"SELECT Doctors.UserName AS UserName, FirstName, LastName, "
			+ "Password, Phone, Street1, Street2, City, State, Zipcode "
			+ "FROM Doctors INNER JOIN Users ON Doctors.UserName = Users.UserName WHERE Doctors.LastName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectDoctor);
			selectStmt.setString(1, lastname);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String username = results.getString("UserName");
				String password = results.getString("Password");
				String phone = results.getString("Phone");
				String street1 = results.getString("Street1");
				String street2 = results.getString("Street2");
				String city = results.getString("City");
				String state = results.getString("State");
				String zipcode = results.getString("Zipcode");
				String firstName = results.getString("FirstName");
				String lastName = results.getString("LastName");
	
	
				Doctors doctor = new Doctors(username, password,
						phone, street1, street2, city, state, zipcode, firstName, lastName);
				doctors.add(doctor);
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
		return doctors;
	}
	
	
	public Doctors delete(Doctors doctor) throws SQLException {
		String deleteBlogComment = "DELETE FROM Doctors WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteBlogComment);
			deleteStmt.setString(1, doctor.getUserName());
			deleteStmt.executeUpdate();
			super.delete(doctor);
	
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

}
