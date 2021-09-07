package servlet;

import model.*;
import dal.*;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.text.SimpleDateFormat;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/createpharmacy")
public class CreatePharmacy extends HttpServlet {
	protected PharmaciesDao pharmaciesDao;
	protected UsersDao usersDao;
	
	@Override
	public void init() throws ServletException {
		pharmaciesDao = PharmaciesDao.getInstance();
		usersDao = UsersDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getRequestDispatcher("/CreatePharmacy.jsp").forward(req, resp);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
		Pharmacies pharmacy = null;
		
		// Retrieve inputs
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String phone = req.getParameter("phone");
		String street1 = req.getParameter("street1");
		String street2 = req.getParameter("street2");
		String city = req.getParameter("city");
		String state = req.getParameter("state");
		String zipcode = req.getParameter("zipcode");
		String pharmacyName = req.getParameter("pharmacyName");
		String open = req.getParameter("open");
		String close = req.getParameter("close");
		
		if (username != null && password!= null && phone != null && street1 != null
			&& street2 != null && city != null && state != null && zipcode != null
			&& open != null && close != null) {
			try {
				// convert time from html to java.sql.Time
				SimpleDateFormat format = new SimpleDateFormat("hh:mm");
				Time openTime = new Time(format.parse(open).getTime());
				Time closeTime = new Time(format.parse(close).getTime());
				
				pharmacy = new Pharmacies(username, password, phone, street1,
									 	  street2, city, state, zipcode, pharmacyName,
									 	  openTime, closeTime);
				
				// check whether the user name already exists in the DB
				if (usersDao.getUserByUserName(pharmacy.getUserName()) == null) {
					pharmacy = pharmaciesDao.create(pharmacy);
					messages.put("success", "Successfully created pharmacy with username: " + pharmacy.getUserName());
				} else {
					messages.put("fail", "The username already exists.");
				}
				req.setAttribute("pharmacy", pharmacy);
			} catch (Exception e) {
				e.printStackTrace();
				throw new IOException(e);
		    }
		} else {
			messages.put("fail", "Please make sure all fields are filled correctly.");
		}
		req.getRequestDispatcher("/CreatePharmacy.jsp").forward(req, resp);

	}
	
}