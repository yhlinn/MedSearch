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
import javax.servlet.http.HttpSession;


@WebServlet("/updatedoctor")
public class DoctorUpdate extends HttpServlet {
	protected DoctorsDao doctorsDao;
	protected UsersDao usersDao;
	
	@Override
	public void init() throws ServletException {
		doctorsDao = DoctorsDao.getInstance();
		usersDao = UsersDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		String destPage = null;
		
		if (session.getAttribute("doctor") == null) {
			destPage = "/LoginError.jsp";
		} else {
			destPage = "/DoctorUpdate.jsp";
			Doctors doctor = (Doctors) session.getAttribute("doctor");
			
			// Display existing information
			req.setAttribute("username", doctor.getUserName());
			req.setAttribute("password", doctor.getPassword());
			req.setAttribute("phone", doctor.getPhone());
			req.setAttribute("street1", doctor.getStreet1());
			req.setAttribute("street2", doctor.getStreet2());
			req.setAttribute("city", doctor.getCity());
			req.setAttribute("state", doctor.getState());
			req.setAttribute("zipcode", doctor.getZipcode());
			req.setAttribute("firstname", doctor.getFirstName());
			req.setAttribute("lastname", doctor.getLastName());
		}
		req.getRequestDispatcher(destPage).forward(req, resp);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
		Doctors doctor = null;
		Doctors updatedDoctor = null;
		HttpSession session = req.getSession();
		String destPage = null;
		
		
		if (session.getAttribute("doctor") == null) {
			destPage = "/LoginError.jsp";
		} else {
			destPage = "/DoctorUpdate.jsp";
			doctor = (Doctors) session.getAttribute("doctor");
			
			// Retrieve inputs of new information
			String username = doctor.getUserName();
			String password = req.getParameter("password");
			String phone = req.getParameter("phone");
			String street1 = req.getParameter("street1");
			String street2 = req.getParameter("street2");
			String city = req.getParameter("city");
			String state = req.getParameter("state");
			String zipcode = req.getParameter("zipcode");
			String firstName = req.getParameter("firstName");
			String lastName = req.getParameter("lastName");
			
			// Proceed with update if all fields are not null
			if (password!= null && phone != null && street1 != null
				&& street2 != null && city != null && state != null && zipcode != null
				&& firstName != null && lastName != null) {
				try {
					// Retrieve the existing doctor
					doctor = doctorsDao.getDoctorFromUserName(username);
					
					// Update the doctor if the user name exists
					// Otherwise, do nothing
					if (doctor == null) {
						req.setAttribute("fail", "The username does not exist.");
					} else {
						updatedDoctor = new Doctors(username, password, phone, street1,
								 					street2, city, state, zipcode,
								 					firstName, lastName);
						doctor = doctorsDao.update(doctor, updatedDoctor);
						
						req.setAttribute("success", "Succesfully updated your profile.");
					}
					req.setAttribute("doctor", doctor);
				} catch (SQLException e) {
					e.printStackTrace();
					throw new IOException(e);
			    }
			} else {
				req.setAttribute("fail", "Please make sure all fields are filled correctly.");
			}
			
			// Display existing information, either updated or not
			req.setAttribute("username", doctor.getUserName());
			req.setAttribute("password", doctor.getPassword());
			req.setAttribute("phone", doctor.getPhone());
			req.setAttribute("street1", doctor.getStreet1());
			req.setAttribute("street2", doctor.getStreet2());
			req.setAttribute("city", doctor.getCity());
			req.setAttribute("state", doctor.getState());
			req.setAttribute("zipcode", doctor.getZipcode());
			req.setAttribute("firstname", doctor.getFirstName());
			req.setAttribute("lastname", doctor.getLastName());
		}
		req.getRequestDispatcher(destPage).forward(req, resp);
	}
	
}
