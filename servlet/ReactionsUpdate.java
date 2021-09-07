package servlet;

import model.*;
import dal.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/reactionsupdate")
public class ReactionsUpdate extends HttpServlet {
	
	protected ReactionsDao reactionsDao;
	
	@Override
	public void init() throws ServletException {
		reactionsDao = ReactionsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        Reactions reaction = null;

        // Retrieve input ID and validate
        String inputReactionId = req.getParameter("reactionId");
        
        if (inputReactionId != null && !inputReactionId.trim().isEmpty()) {
            try {
            	// Convert the ID back to int to be used in DAO
            	int reactionId = Integer.parseInt(inputReactionId);
            	reaction = reactionsDao.getReactionById(reactionId);
            	// Check if a reaction with the given ID exists
            	if (reaction == null) {
            		messages.put("fail", "Reaction ID does not exist.");
            	}
            	req.setAttribute("reaction:", reaction);
            } catch (SQLException e) {
            	e.printStackTrace();
            	throw new IOException(e);
            }
        }
        req.getRequestDispatcher("/ReactionsUpdate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
		// Map for storing messages
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        Reactions reaction = null;

        // Retrieve input ID and validate
        String inputReactionId = req.getParameter("reactionId");
        
        if (inputReactionId == null || inputReactionId.trim().isEmpty()) {
        	messages.put("fail", "Please enter a valid Prescription ID");
        } else {
        	try {
        		// Convert the ID back to int to be used in DAO
            	int reactionId = Integer.parseInt(inputReactionId);
            	reaction = reactionsDao.getReactionById(reactionId);
            	// Check if a reaction with the given ID exists
            	if (reaction == null) {
            		messages.put("fail", "Reaction ID does not exist.");
            	} else {
            		// Retrieve input for new description
            		String newDescription = req.getParameter("newDescription");
        			if (newDescription == null || newDescription.trim().isEmpty()) {
        	            messages.put("fail", "Please enter a valid description.");
        	        } else {
        	        	// Perform update on the reaction
        	        	reaction = reactionsDao.updateDescription(reaction, newDescription);
        	        	messages.put("success", "Successfully updated reaction with ID " + reactionId);
        	        }
            	}
            	req.setAttribute("reaction", reaction);
        	} catch (SQLException e) {
        		e.printStackTrace();
        		throw new IOException(e);
        	}
        }
        req.getRequestDispatcher("/ReactionsUpdate.jsp").forward(req, resp);
    }
}
