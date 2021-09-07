package dal;

import model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;


public class DrugPricesDao {
	protected ConnectionManager connectionManager;
	private static DrugPricesDao instance = null;
	
	protected DrugPricesDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static DrugPricesDao getInstance() {
		if (instance == null) {
			instance = new DrugPricesDao();
		}
		return instance;
	}
	
	public DrugPrices create(DrugPrices drugPrice) throws SQLException {
		String insertDrugPrice = "INSERT INTO DrugPrices(Description, Currency, " +
							     "Cost, Unit, DrugId) VALUES(?, ?, ?, ?, ?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertDrugPrice, Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, drugPrice.getDescription());
			insertStmt.setString(2, drugPrice.getCurrency());
			insertStmt.setDouble(3, drugPrice.getCost());
			insertStmt.setString(4, drugPrice.getUnit());
			insertStmt.setString(5, drugPrice.getDrug().getDrugId());
			insertStmt.executeUpdate();
			
			// retrieve the auto-generated ID
			resultKey = insertStmt.getGeneratedKeys();
			int priceId = -1;
			if (resultKey.next()) {
				priceId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			// update the drugPrices instance with the auto-generated ID
			drugPrice.setPriceId(priceId);
			return drugPrice;
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
			if(resultKey != null) {
				resultKey.close();
			}
		}
	}
	
	public DrugPrices updateCost(DrugPrices drugPrice, double newCost) throws SQLException {
		String updateDrugPrice = "UPDATE DrugPrices SET Cost = ? WHERE PriceId = ?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateDrugPrice);
			updateStmt.setDouble(1, newCost);
			updateStmt.setInt(2, drugPrice.getPriceId());
			updateStmt.executeUpdate();
			// update the drugPrice instance
			drugPrice.setCost(newCost);
			return drugPrice;
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
	
	public DrugPrices delete(DrugPrices drugPrice) throws SQLException {
		String deleteDrugPrice = "DELETE FROM DrugPrices WHERE PriceId = ?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteDrugPrice);
			deleteStmt.setInt(1, drugPrice.getPriceId());
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
	
	public DrugPrices getDrugPriceById(int drugPriceId) throws SQLException {
		String selectDrugPrice = "SELECT * FROM DrugPrices WHERE PriceId = ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectDrugPrice);
			selectStmt.setInt(1, drugPriceId);
			results = selectStmt.executeQuery();
			DrugsDao drugsDao = DrugsDao.getInstance();
			
			if (results.next()) {
				int resultPriceId = results.getInt("PriceId");
				String resultDescription = results.getString("Description");
				String resultCurrency = results.getString("Currency");
				double resultCost = results.getDouble("Cost");
				String resultUnit = results.getString("Unit");
				String resultDrugId = results.getString("DrugId");
				// use drugDao to retrieve the drug instance in this drugPrice
				Drugs resultDrug = drugsDao.getDrugById(resultDrugId);
				DrugPrices drugPrice = new DrugPrices(resultPriceId, resultDescription,
													  resultCurrency, resultCost,
													  resultUnit, resultDrug);
				return drugPrice;
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
	
	public List<DrugPrices> getDrugPricesByDrugId(String drugId) throws SQLException {
		String selectDrugPrices = "SELECT * FROM DrugPrices WHERE DrugId = ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		List<DrugPrices> drugPrices = new ArrayList<DrugPrices>();
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectDrugPrices);
			selectStmt.setString(1, drugId);
			results = selectStmt.executeQuery();
			DrugsDao drugsDao = DrugsDao.getInstance();
			
			while (results.next()) {
				int resultPriceId = results.getInt("PriceId");
				String resultDescription = results.getString("DrugPrices.Description");
				String resultCurrency = results.getString("Currency");
				double resultCost = results.getDouble("Cost");
				String resultUnit = results.getString("Unit");
				String resultDrugId = results.getString("DrugId");
				// use drugDao to retrieve the drug instance in this drugPrice
				Drugs resultDrug = drugsDao.getDrugById(resultDrugId);
				DrugPrices drugPrice = new DrugPrices(resultPriceId, resultDescription,
													  resultCurrency, resultCost,
													  resultUnit, resultDrug);
				drugPrices.add(drugPrice);
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
		return drugPrices;
	}
	
	public List<DrugPrices> getDrugPricesByDrugName(String drugName) throws SQLException {
		String selectDrugPrices = "SELECT * FROM DrugPrices INNER JOIN Drugs " +
								  "ON DrugPrices.DrugId = Drugs.DrugId " +
								  "WHERE DrugName = ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		List<DrugPrices> drugPrices = new ArrayList<DrugPrices>();
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectDrugPrices);
			selectStmt.setString(1, drugName);
			results = selectStmt.executeQuery();
			DrugsDao drugsDao = DrugsDao.getInstance();
			
			while (results.next()) {
				int resultPriceId = results.getInt("PriceId");
				String resultDescription = results.getString("DrugPrices.Description");
				String resultCurrency = results.getString("Currency");
				double resultCost = results.getDouble("Cost");
				String resultUnit = results.getString("Unit");
				String resultDrugId = results.getString("DrugId");
				// use drugDao to retrieve the drug instance in this drugPrice
				Drugs resultDrug = drugsDao.getDrugById(resultDrugId);
				DrugPrices drugPrice = new DrugPrices(resultPriceId, resultDescription,
													  resultCurrency, resultCost,
													  resultUnit, resultDrug);
				drugPrices.add(drugPrice);
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
		return drugPrices;	
	}
}