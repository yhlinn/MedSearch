package servlet;

import model.*;
import dal.*;

import java.io.IOException;
import java.sql.Date;
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


@WebServlet("/reactionscreate")
public class ReactionsCreate extends HttpServlet {
	protected ReactionsDao reactionsDao;
	
	@Override
	public void init() throws ServletException {
		reactionsDao = ReactionsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
        //Just render the JSP  
        req.getRequestDispatcher("/ReactionsCreate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        Reactions reaction = null;
        
        // Retrieve inputs
        String inputDrugIdA = req.getParameter("drugIdA");
        String inputDrugIdB = req.getParameter("drugIdB");
        String inputDescription = req.getParameter("description");
        
        if (inputDrugIdA == null || inputDrugIdA.trim().isEmpty() ||
        	inputDrugIdB == null || inputDrugIdB.trim().isEmpty()) {
        	messages.put("fail", "Please enter a valid drug ID.");
        } else if (inputDescription == null || inputDescription.trim().isEmpty()) {
        	messages.put("fail", "Please enter a valid description.");
        } else {
            try {
            	DrugsDao drugsDao = DrugsDao.getInstance();
            	Drugs inputDrugA = drugsDao.getDrugById(inputDrugIdA);
            	Drugs inputDrugB = drugsDao.getDrugById(inputDrugIdB);
   
            	// Validate if the drugs exist
            	if (inputDrugA == null || inputDrugB == null) {
            		messages.put("fail", "Drug ID(s) do not exist.");
            	} else {
            		// Create a new reaction
            		String inputDrugName = inputDrugB.getDrugName();
            		reaction = new Reactions(inputDrugA, inputDrugB, inputDrugName, inputDescription);
            		reaction = reactionsDao.create(reaction);
                	messages.put("success", "Successfully created reaction with ID " + reaction.getReactionId());
            	}
            	req.setAttribute("reaction", reaction);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
    	    }
        }
        req.getRequestDispatcher("/ReactionsCreate.jsp").forward(req, resp);
	}
}