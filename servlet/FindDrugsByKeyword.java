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

@WebServlet("/finddrugsbykeyword")
public class FindDrugsByKeyword extends HttpServlet {
	protected DrugsDao drugsDao;
	
	@Override
	public void init() throws ServletException {
		drugsDao = DrugsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		List<Drugs> drugs = new ArrayList<Drugs>();
		String keyword = req.getParameter("keyword");
		HttpSession session = req.getSession();
		
		if (keyword != null && !keyword.trim().isEmpty()) {
			try {
				if (session.getAttribute("customer") == null &&
					session.getAttribute("admin") == null) {
					// demo session
					drugs = drugsDao.demoGetDrugsByKeyword(keyword);
					String warningMessage = "Results are limited to 10 because you are currently in demo mode. " +
											"Sign up or log in to view more results.";
					req.setAttribute("warning", warningMessage);
				} else {
					// logged in
					drugs = drugsDao.getDrugsByKeyword(keyword);	
				}
				req.setAttribute("success", "Displaying results for " + keyword);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
		}
		req.setAttribute("keyword", keyword);
		req.setAttribute("drugs", drugs);
		req.getRequestDispatcher("/FindDrugsByKeyword.jsp").forward(req, resp);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		List<Drugs> drugs = new ArrayList<Drugs>();
		String keyword = req.getParameter("keyword");
		HttpSession session = req.getSession();
		
		if (keyword != null && !keyword.trim().isEmpty()) {
			try {
				if (session.getAttribute("customer") == null &&
					session.getAttribute("admin") == null) {
					// demo session
					drugs = drugsDao.demoGetDrugsByKeyword(keyword);
					String warningMessage = "Results are limited to 10 because you are currently in demo mode. " +
											"Sign up or log in to view more results.";
					req.setAttribute("warning", warningMessage);
				} else {
					// logged in
					drugs = drugsDao.getDrugsByKeyword(keyword);	
				}
				req.setAttribute("success", "Displaying results for " + keyword);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
		}
		req.setAttribute("keyword", keyword);
		req.setAttribute("drugs", drugs);
		req.getRequestDispatcher("/FindDrugsByKeyword.jsp").forward(req, resp);
	}
}