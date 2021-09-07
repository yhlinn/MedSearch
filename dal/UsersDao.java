package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Users;

public class UsersDao {
	protected ConnectionManager connectionManager;
	private static UsersDao instance = null;
	
	protected UsersDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static UsersDao getInstance() {
		if(instance == null) {
			instance = new UsersDao();
		}
		return instance;
	}
	
	/**
	 * Creates a new user object and insert this user to the DB.
	 * @param user the new user
	 * @return the new user
	 * @throws SQLException
	 */
	public Users create(Users user) throws SQLException {
		String insertUser = "INSERT INTO Users(UserName, Password, Phone, Street1, Street2, City, State, Zipcode) " +
							"VALUES(?,?,?,?,?,?,?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertUser);
			insertStmt.setString(1, user.getUserName());
			insertStmt.setString(2, user.getPassword());
			insertStmt.setString(3, user.getPhone());
			insertStmt.setString(4, user.getStreet1());
			insertStmt.setString(5, user.getStreet2());
			insertStmt.setString(6, user.getCity());
			insertStmt.setString(7, user.getState());
			insertStmt.setString(8, user.getZipcode());
			insertStmt.executeUpdate();
			return user;	
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
	 * Deletes the given user from the DB. The user object is identified with
	 * its unique username.
	 * @param user the user to be deleted
	 * @return null
	 * @throws SQLException
	 */
	public Users delete(Users user) throws SQLException {
		String deleteUser = "DELETE FROM Users WHERE UserName = ?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteUser);
			deleteStmt.setString(1, user.getUserName());
			deleteStmt.executeUpdate();
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
	
	
	/**
	 * Updates an existing user with new field values in the given updated
	 * user. The fields of the existing user will be replaced with the
	 * new values in the DB, and the user itself will also be updated.
	 * 
	 * @param user the existing user object
	 * @param updatedUser the user object with the updated values
	 * @return the existing user object with updated field values
	 * @throws SQLException
	 */
	public Users update(Users user, Users updatedUser)
			throws SQLException {
		String updateUser = "UPDATE Users SET Password=?, Phone=?, Street1=?, " +
							"Street2=?, City=?, State=?, Zipcode=? " +
							"WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		
		 try {
			 connection = connectionManager.getConnection();
			 updateStmt = connection.prepareStatement(updateUser);
			 updateStmt.setString(1, updatedUser.getPassword());
			 updateStmt.setString(2, updatedUser.getPhone());
			 updateStmt.setString(3, updatedUser.getStreet1());
			 updateStmt.setString(4, updatedUser.getStreet2());
			 updateStmt.setString(5, updatedUser.getCity());
			 updateStmt.setString(6, updatedUser.getState());
			 updateStmt.setString(7, updatedUser.getZipcode());
			 updateStmt.setString(8, user.getUserName());
			 updateStmt.executeUpdate();
			 
			 // Update the user before returning
			 user.setPassword(updatedUser.getPassword());
			 user.setPhone(updatedUser.getPhone());
			 user.setStreet1(updatedUser.getStreet1());
			 user.setStreet2(updatedUser.getStreet2());
			 user.setCity(updatedUser.getCity());
			 user.setState(updatedUser.getState());
			 user.setZipcode(updatedUser.getZipcode());
			 
			 return user;
			 
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
	
	
	/**
	 * Updates the given user with the given new phone number.
	 * @param user the user
	 * @param newPhone the new phone number
	 * @return the user object with the updated phone number
	 * @throws SQLException
	 */
	public Users updatePhone(Users user, String newPhone) throws SQLException {
		String updateUser = "UPDATE Users SET Phone=? WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateUser);
			updateStmt.setString(1, newPhone);
			updateStmt.setString(2, user.getUserName());
			updateStmt.executeUpdate();

			// Update the user before returning
			user.setPhone(newPhone);
			return user;
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
	
			
	/**
	 * Return the user with the given unique username in the DB.
	 * @param username the target username
	 * @return the user with the given username; null if the user does not exist
	 * @throws SQLException
	 */
	public Users getUserByUserName(String username) throws SQLException {
		String selectUser = "SELECT * FROM Users WHERE UserName=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectUser);
			selectStmt.setString(1, username);
			results = selectStmt.executeQuery();
			if(results.next()) {
				String resultUsername = results.getString("UserName");
				String password = results.getString("Password");
				String phone = results.getString("Phone");
				String street1 = results.getString("Street1");
				String street2 = results.getString("Street2");
				String city = results.getString("City");
				String state = results.getString("Street2");
				String zipcode = results.getString("Zipcode");
				Users user = new Users(resultUsername, password, phone,
								       street1, street2, city, state, zipcode);	
				return user;
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
