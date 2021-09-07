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


@WebServlet("/findpharmacy")
public class FindPharmacy extends HttpServlet {
	protected PharmaciesDao pharmacyDao;
	
	@Override
	public void init() throws ServletException {
		pharmacyDao = PharmaciesDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		List<Pharmacies> pharmacies = new ArrayList<Pharmacies>();
		
		String username = req.getParameter("username");
		
		if (username != null && !username.trim().isEmpty()) {
			try {
				pharmacies = pharmacyDao.getPharmacyFromPharmacyName(username);
				req.setAttribute("success", "Displaying results for " + username);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
		}
		req.setAttribute("pharmacies", pharmacies);
		req.getRequestDispatcher("/FindPharmacy.jsp").forward(req, resp);	
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		List<Pharmacies> pharmacies = new ArrayList<Pharmacies>();
		String username = req.getParameter("username");
		
		if (username != null && !username.trim().isEmpty()) {
			try {
				pharmacies = pharmacyDao.getPharmacyFromPharmacyName(username);
				req.setAttribute("success", "Displaying results for " + username);
				
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
		}
		req.setAttribute("pharmacies", pharmacies);
		req.getRequestDispatcher("/FindPharmacy.jsp").forward(req, resp);	
	}
}