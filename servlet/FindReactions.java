package servlet;

import model.*;
import dal.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.jasper.tagplugins.jstl.core.If;

@WebServlet("/findinteractions")
public class FindReactions extends HttpServlet {
	protected ReactionsDao reactionsDao;
	
	@Override
	public void init() throws ServletException {
		reactionsDao = ReactionsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		List<Reactions> reactions = new ArrayList<Reactions>();
		String drugNameA = req.getParameter("drugNameA");
		String drugNameB = req.getParameter("drugNameB");
		HttpSession session = req.getSession();
		
		boolean existDrugNameA = drugNameA != null && !drugNameA.trim().isEmpty();
		boolean existDrugNameB = drugNameB != null && !drugNameB.trim().isEmpty();
		
		if (existDrugNameA && existDrugNameB) {
			try {
				if (session.getAttribute("customer") == null &&
					session.getAttribute("admin") == null) {
					// demo session
					reactions = reactionsDao.demoGetReactionsBetweenDrugNames(drugNameA, drugNameB);
					String warningMessage = "Results are limited to 10 because you are currently in demo mode. " +
							"Sign up or log in to view more results.";
					req.setAttribute("warning", warningMessage);
				} else {
					// logged in
					reactions = reactionsDao.getReactionsBetweenDrugNames(drugNameA, drugNameB);	
				}
				req.setAttribute("success", "Displaying results for " + drugNameA +
								 " and " + drugNameB);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
		}
		req.setAttribute("reactions", reactions);
		// store the drug name that is not stored in reactions
		req.setAttribute("drugNameA", drugNameA);
		req.getRequestDispatcher("/FindReactions.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
		List<Reactions> reactions = new ArrayList<Reactions>();
		String drugNameA = req.getParameter("drugNameA");
		String drugNameB = req.getParameter("drugNameB");
		HttpSession session = req.getSession();
		
		boolean existDrugNameA = drugNameA != null && !drugNameA.trim().isEmpty();
		boolean existDrugNameB = drugNameB != null && !drugNameB.trim().isEmpty();
		
		if (existDrugNameA && existDrugNameB) {
			try {
				if (session.getAttribute("customer") == null &&
					session.getAttribute("admin") == null) {
					// demo session
					reactions = reactionsDao.demoGetReactionsBetweenDrugNames(drugNameA, drugNameB);
					String warningMessage = "Results are limited to 3 because you are currently in demo mode. " +
							"Sign up or log in to view more results.";
					req.setAttribute("warning", warningMessage);
				} else {
					// logged in
					reactions = reactionsDao.getReactionsBetweenDrugNames(drugNameA, drugNameB);	
				}
				req.setAttribute("success", "Displaying results for " + drugNameA +
								 " and " + drugNameB);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
		}
		req.setAttribute("reactions", reactions);
		// store the drug name that is not stored in reactions
		req.setAttribute("drugNameA", drugNameA);
		req.getRequestDispatcher("/FindReactions.jsp").forward(req, resp);
	}
	
	
	
	
	
	
	
}