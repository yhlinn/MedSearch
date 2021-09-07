package dal;

import model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;


public class ReactionsDao {
	protected ConnectionManager connectionManager;
	private static ReactionsDao instance = null;
	
	protected ReactionsDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static ReactionsDao getInstance() {
		if (instance == null) {
			instance = new ReactionsDao();
		}
		return instance;
	}
	
	public Reactions create(Reactions reaction) throws SQLException {
		String insertReaction = "INSERT INTO Reactions(DrugIdA, DrugIdB, " +
								"DrugName, Description) VALUES(?, ?, ?, ?);";
		
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertReaction, Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, reaction.getDrugA().getDrugId());
			insertStmt.setString(2, reaction.getDrugB().getDrugId());
			insertStmt.setString(3, reaction.getDrugName());
			insertStmt.setString(4, reaction.getDescription());
			insertStmt.executeUpdate();
			
			// retrieve the auto-generated ID
			resultKey = insertStmt.getGeneratedKeys();
			int reactionId = -1;
			if (resultKey.next()) {
				reactionId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			// update the reaction instance with the auto-generated ID
			reaction.setReactionId(reactionId);
			return reaction;	
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
	
	public Reactions updateDescription(Reactions reaction, String newDescription) throws SQLException {
		String updateReaction = "UPDATE Reactions SET Description = ? WHERE ReactionId = ?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateReaction);
			updateStmt.setString(1, newDescription);
			updateStmt.setInt(2, reaction.getReactionId());
			updateStmt.executeUpdate();
			// update the reaction instance
			reaction.setDescription(newDescription);
			return reaction;
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
	
	public Reactions delete(Reactions reaction) throws SQLException {
		String deleteReaction = "DELETE FROM Reactions WHERE ReactionId = ?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteReaction);
			deleteStmt.setInt(1, reaction.getReactionId());
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
	
	public Reactions getReactionById(int reactionId) throws SQLException {
		String selectReaction = "SELECT * FROM Reactions WHERE ReactionId = ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReaction);
			selectStmt.setInt(1, reactionId);
			results = selectStmt.executeQuery();
			DrugsDao drugsDao = DrugsDao.getInstance();
			
			if (results.next()) {
				int resultReactionId = results.getInt("ReactionId");
				String resultDrugIdA = results.getString("DrugIdA");
				String resultDrugIdB = results.getString("DrugIdB");
				String resultDrugName = results.getString("DrugName");
				String resultDescription = results.getString("Description");
				// use drugDao to retrieve the drug instances in this reaction
				Drugs resultDrugA = drugsDao.getDrugById(resultDrugIdA);
				Drugs resultDrugB = drugsDao.getDrugById(resultDrugIdB);
	
				Reactions reaction = new Reactions(resultReactionId, resultDrugA,
									 			   resultDrugB, resultDrugName,
									 			   resultDescription);
				return reaction;
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
	
	public List<Reactions> getReactionsForDrugId(String drugId) throws SQLException {
		String selectReactions = "SELECT * FROM Reactions WHERE DrugIdA=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		List<Reactions> reactions = new ArrayList<Reactions>();
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReactions);
			selectStmt.setString(1, drugId);
			results = selectStmt.executeQuery();
			DrugsDao drugsDao = DrugsDao.getInstance();
			
			while (results.next()) {
				int resultReactionId = results.getInt("ReactionId");
				String resultDrugIdA = results.getString("DrugIdA");
				String resultDrugIdB = results.getString("DrugIdB");
				String resultDrugName = results.getString("DrugName");
				String resultDescription = results.getString("Description");
				// use drugDao to retrieve the drug instances in this reaction
				Drugs resultDrugA = drugsDao.getDrugById(resultDrugIdA);
				Drugs resultDrugB = drugsDao.getDrugById(resultDrugIdB);
	
				Reactions reaction = new Reactions(resultReactionId, resultDrugA,
									 			   resultDrugB, resultDrugName,
									 			   resultDescription);
				reactions.add(reaction);
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
		return reactions;
	}
	
	public List<Reactions> getReactionsBetweenDrugNames(String drugNameA, String drugNameB)
			throws SQLException {
		String selectReactions = "SELECT * FROM Reactions INNER JOIN Drugs " +
								 "ON Reactions.DrugIdA = Drugs.DrugId " +
								 "WHERE Reactions.DrugName = ? AND Drugs.DrugName = ?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		List<Reactions> reactions = new ArrayList<Reactions>();
		DrugsDao drugsDao = DrugsDao.getInstance();
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReactions);
			selectStmt.setString(1, drugNameB);
			selectStmt.setString(2, drugNameA);
			results = selectStmt.executeQuery();
			
			while (results.next()) {
				int resultReactionId = results.getInt("ReactionId");
				String resultDrugIdA = results.getString("DrugIdA");
				String resultDrugIdB = results.getString("DrugIdB");
				String resultDrugName = results.getString("Reactions.DrugName");
				String resultDescription = results.getString("Reactions.Description");
				// use drugDao to retrieve the drug instances in this reaction
				Drugs resultDrugA = drugsDao.getDrugById(resultDrugIdA);
				Drugs resultDrugB = drugsDao.getDrugById(resultDrugIdB);
	
				Reactions reaction = new Reactions(resultReactionId, resultDrugA,
									 			   resultDrugB, resultDrugName,
									 			   resultDescription);
				reactions.add(reaction);
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
		return reactions;
	}
	
	public List<Reactions> demoGetReactionsBetweenDrugNames(String drugNameA, String drugNameB)
			throws SQLException {
		String selectReactions = "SELECT * FROM Reactions INNER JOIN Drugs " +
								 "ON Reactions.DrugIdA = Drugs.DrugId " +
								 "WHERE Reactions.DrugName = ? AND Drugs.DrugName = ? " +
								 "LIMIT 3;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		List<Reactions> reactions = new ArrayList<Reactions>();
		DrugsDao drugsDao = DrugsDao.getInstance();
		
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectReactions);
			selectStmt.setString(1, drugNameB);
			selectStmt.setString(2, drugNameA);
			results = selectStmt.executeQuery();
			
			while (results.next()) {
				int resultReactionId = results.getInt("ReactionId");
				String resultDrugIdA = results.getString("DrugIdA");
				String resultDrugIdB = results.getString("DrugIdB");
				String resultDrugName = results.getString("Reactions.DrugName");
				String resultDescription = results.getString("Reactions.Description");
				// use drugDao to retrieve the drug instances in this reaction
				Drugs resultDrugA = drugsDao.getDrugById(resultDrugIdA);
				Drugs resultDrugB = drugsDao.getDrugById(resultDrugIdB);
	
				Reactions reaction = new Reactions(resultReactionId, resultDrugA,
									 			   resultDrugB, resultDrugName,
									 			   resultDescription);
				reactions.add(reaction);
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
		return reactions;
	}
}