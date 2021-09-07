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
import java.sql.Time;
import java.text.SimpleDateFormat;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/updatepharmacy")
public class PharmacyUpdate extends HttpServlet {
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
		HttpSession session = req.getSession();
		String destPage = null;
		
		if (session.getAttribute("pharmacy") == null) {
			destPage = "/LoginError.jsp";
		} else {
			destPage = "/PharmacyUpdate.jsp";
			Pharmacies pharmacy = (Pharmacies) session.getAttribute("pharmacy");
			
			// Display existing information
			req.setAttribute("username", pharmacy.getUserName());
			req.setAttribute("password", pharmacy.getPassword());
			req.setAttribute("phone", pharmacy.getPhone());
			req.setAttribute("street1", pharmacy.getStreet1());
			req.setAttribute("street2", pharmacy.getStreet2());
			req.setAttribute("city", pharmacy.getCity());
			req.setAttribute("state", pharmacy.getState());
			req.setAttribute("zipcode", pharmacy.getZipcode());
			req.setAttribute("pharmacyname", pharmacy.getPharmacyName());
			req.setAttribute("open", pharmacy.getOpenTime());
			req.setAttribute("close", pharmacy.getCloseTime());
		}
		req.getRequestDispatcher(destPage).forward(req, resp);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
		Pharmacies pharmacy = null;
		Pharmacies updatedPharmacy = null;
		HttpSession session = req.getSession();
		String destPage = null;
		
		
		if (session.getAttribute("pharmacy") == null) {
			destPage = "/LoginError.jsp";
		} else {
			destPage = "/PharmacyUpdate.jsp";
			pharmacy = (Pharmacies) session.getAttribute("pharmacy");
			
			// Retrieve inputs of new information
			String username = pharmacy.getUserName();
			String password = req.getParameter("password");
			String phone = req.getParameter("phone");
			String street1 = req.getParameter("street1");
			String street2 = req.getParameter("street2");
			String city = req.getParameter("city");
			String state = req.getParameter("state");
			String zipcode = req.getParameter("zipcode");
			String pharmacyName = req.getParameter("pharmacyname");
			String openTime = req.getParameter("open");
			String closeTime = req.getParameter("close");
			
			// Proceed with update if all fields are not null
			if (password!= null && phone != null && street1 != null
				&& street2 != null && city != null && state != null && zipcode != null
				&& pharmacyName != null && openTime != null && closeTime != null) {
				try {
					SimpleDateFormat format = new SimpleDateFormat("hh:mm");
					Time open = new Time(format.parse(openTime).getTime());
					Time close = new Time(format.parse(closeTime).getTime());
					
					updatedPharmacy = new Pharmacies(username, password, phone, street1,
								 					street2, city, state, zipcode,
								 					pharmacyName, open, close);
					pharmacy = pharmaciesDao.update(pharmacy, updatedPharmacy);
						
					req.setAttribute("success", "Succesfully updated your profile.");
					req.setAttribute("pharmacy", pharmacy);
					session.setAttribute("pharmacy", pharmacy);
				} catch (Exception e) {
					e.printStackTrace();
					throw new IOException(e);
			    }
			} else {
				req.setAttribute("fail", "Please make sure all fields are filled correctly.");
			}
			
			// Display existing information, either updated or not
			req.setAttribute("username", pharmacy.getUserName());
			req.setAttribute("password", pharmacy.getPassword());
			req.setAttribute("phone", pharmacy.getPhone());
			req.setAttribute("street1", pharmacy.getStreet1());
			req.setAttribute("street2", pharmacy.getStreet2());
			req.setAttribute("city", pharmacy.getCity());
			req.setAttribute("state", pharmacy.getState());
			req.setAttribute("zipcode", pharmacy.getZipcode());
			req.setAttribute("pharmacyname", pharmacy.getPharmacyName());
			req.setAttribute("open", pharmacy.getOpenTime());
			req.setAttribute("close", pharmacy.getCloseTime());
		}
		req.getRequestDispatcher(destPage).forward(req, resp);
	}
	
}
