package dal;


import java.sql.Time;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Doctors;
import model.Pharmacies;
import model.Users;

public class PharmaciesDao extends UsersDao {
	private static PharmaciesDao instance = null;
	
	protected PharmaciesDao() {
		super();
	}
	
	public static PharmaciesDao getInstance() {
		if(instance == null) {
			instance = new PharmaciesDao();
		}
		return instance;
	}
	
	public Pharmacies create(Pharmacies pharmacy) throws SQLException {
		super.create(new Users(pharmacy.getUserName(), pharmacy.getPassword(),
				               pharmacy.getPhone(), pharmacy.getStreet1(), pharmacy.getStreet2(), 
				               pharmacy.getCity(), pharmacy.getState(), pharmacy.getZipcode()));
		
		String insertPharmacy = "INSERT INTO Pharmacies(UserName, PharmacyName, Open, Close) " +
								"VALUES(?, ?, ?, ?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertPharmacy);
			insertStmt.setString(1, pharmacy.getUserName());
			insertStmt.setString(2, pharmacy.getPharmacyName());
			insertStmt.setTime(3, pharmacy.getOpenTime());
			insertStmt.setTime(4, pharmacy.getCloseTime());
			insertStmt.executeUpdate();
			return pharmacy;
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
	
	public Pharmacies update(Pharmacies pharmacy, Pharmacies updatedPharmacy)
			throws SQLException {
		// perform update on the Users class
		// this updates the basic information that is stored in the super class
		// such as password, phone, address...etc
		pharmacy = (Pharmacies) super.update(pharmacy, updatedPharmacy);
		
		// perform update on the Pharmacies class
		String updatePharmacy = "UPDATE Pharmacies SET PharmacyName=?, Open=?, Close=? " +
								"WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updatePharmacy);
			updateStmt.setString(1, updatedPharmacy.getPharmacyName());
			updateStmt.setTime(2, updatedPharmacy.getOpenTime());
			updateStmt.setTime(3, updatedPharmacy.getCloseTime());
			updateStmt.setString(4, pharmacy.getUserName());
			updateStmt.executeUpdate();
			
			// update the pharmacy before returning
			pharmacy.setPharmacyName(updatedPharmacy.getPharmacyName());
			pharmacy.setOpenTime(updatedPharmacy.getOpenTime());
			pharmacy.setCloseTime(updatedPharmacy.getCloseTime());
			
			return pharmacy;
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

	public List<Pharmacies> getPharmacyFromPharmacyName(String pharmacyname) throws SQLException {
		List<Pharmacies> pharmacies = new ArrayList<Pharmacies>();
		String selectPharmacies =
			"SELECT Pharmacies.UserName AS UserName, PharmacyName, Open, Close, Password, Phone, Street1, Street2, City, State, Zipcode " +
			"FROM Pharmacies INNER JOIN Users " +
			"ON Pharmacies.UserName = Users.UserName " +
			"WHERE Pharmacies.PharmacyName = ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectPharmacies);
			selectStmt.setString(1, pharmacyname);
			results = selectStmt.executeQuery();
			while(results.next()) {
				String username = results.getString("UserName");
				String resultPharmacyName = results.getString("PharmacyName");
				Time resultOpen = results.getTime("Open");
				Time resultClose = results.getTime("Close");
				String password = results.getString("Password");
				String phone = results.getString("Phone");
				String street1 = results.getString("Street1");
				String street2 = results.getString("Street2");
				String city = results.getString("City");
				String state = results.getString("State");
				String zipcode = results.getString("Zipcode");
	
				Pharmacies pharmacy = new Pharmacies(username, password, phone, street1, street2, city, state, zipcode, resultPharmacyName,
													 resultOpen, resultClose);
				pharmacies.add(pharmacy);
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
		return pharmacies;
	}
	
	
	public Pharmacies delete(Pharmacies pharmacy) throws SQLException {
		String deletePharmacy = "DELETE FROM Pharmacies WHERE UserName = ?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deletePharmacy);
			deleteStmt.setString(1, pharmacy.getUserName());
			deleteStmt.executeUpdate();
			super.delete(pharmacy);
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
