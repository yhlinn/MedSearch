package dal;

import model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;
import java.util.ArrayList;


public class DrugsDao {
	protected ConnectionManager connectionManager;
	private static DrugsDao instance = null;
	
	protected DrugsDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static DrugsDao getInstance() {
		if (instance == null) {
			instance = new DrugsDao();
		}
		return instance;
	}
	
	public Drugs create(Drugs drug) throws SQLException {
		String insertDrug = "INSERT INTO Drugs(DrugId, DrugName, Description) " +
						    "VALUES(?, ?, ?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertDrug);
			insertStmt.setString(1, drug.getDrugId());
			insertStmt.setString(2, drug.getDrugName());
			insertStmt.setString(3, drug.getDescription());
			insertStmt.executeUpdate();
			return drug;
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
	
	public Drugs updateDescription(Drugs drug, String newDescription) throws SQLException {
		String updateDrug = "UPDATE Drugs SET Description = ? WHERE DrugId = ?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateDrug);
			updateStmt.setString(1, newDescription);
			updateStmt.setString(2, drug.getDrugId());
			updateStmt.executeUpdate();
			// update the drug instance
			drug.setDescription(newDescription);
			return drug;
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
	
	public Drugs delete(Drugs drug) throws SQLException {
		String deleteDrug = "DELETE FROM Drugs WHERE DrugId = ?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteDrug);
			deleteStmt.setString(1, drug.getDrugId());
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
	
	public Drugs getDrugById(String drugId) throws SQLException {
		String selectDrug = "SELECT * FROM Drugs WHERE DrugId = ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectDrug);
			selectStmt.setString(1, drugId);
			results = selectStmt.executeQuery();
			
			if (results.next()) {
				String resultDrugId = results.getString("DrugId");
				String resultDrugName = results.getString("DrugName");
				String resultDescription = results.getString("Description");
				Drugs drug = new Drugs(resultDrugId, resultDrugName, resultDescription);
				return drug;
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
	
	// TODO: matches any drugs with a name that contains the input name
	public List<Drugs> getDrugsByName(String drugName) throws SQLException {
		String selectDrugs = "SELECT * FROM Drugs WHERE DrugName LIKE \"%?%\";";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		List<Drugs> drugs = new ArrayList<Drugs>();
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectDrugs);
			selectStmt.setString(1, drugName);
			results = selectStmt.executeQuery();
			
			while (results.next()) {
				String resultDrugId = results.getString("DrugId");
				String resultDrugName = results.getString("DrugName");
				String resultDescription = results.getString("Description");
				Drugs drug = new Drugs(resultDrugId, resultDrugName, resultDescription);
				drugs.add(drug);
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
		return drugs;
	}
	
	// matches any drugs with a description that contains the keyword
	public List<Drugs> getDrugsByKeyword(String keyword) throws SQLException {
		String selectDrugs = "SELECT * FROM Drugs WHERE Description LIKE ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		List<Drugs> drugs = new ArrayList<Drugs>();
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectDrugs);
			selectStmt.setString(1, "%" + keyword + "%");
			results = selectStmt.executeQuery();
			
			while (results.next()) {
				String resultDrugId = results.getString("DrugId");
				String resultDrugName = results.getString("DrugName");
				String resultDescription = results.getString("Description");
				Drugs drug = new Drugs(resultDrugId, resultDrugName, resultDescription);
				drugs.add(drug);
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
		return drugs;
	}
	
	// matches any drugs with a description that contains the keyword, limit 10
	public List<Drugs> demoGetDrugsByKeyword(String keyword) throws SQLException {
		String selectDrugs = "SELECT * FROM Drugs WHERE Description LIKE ? LIMIT 10;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		List<Drugs> drugs = new ArrayList<Drugs>();
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectDrugs);
			selectStmt.setString(1, "%" + keyword + "%");
			results = selectStmt.executeQuery();
			
			while (results.next()) {
				String resultDrugId = results.getString("DrugId");
				String resultDrugName = results.getString("DrugName");
				String resultDescription = results.getString("Description");
				Drugs drug = new Drugs(resultDrugId, resultDrugName, resultDescription);
				drugs.add(drug);
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
		return drugs;
	}
	
	
}