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


@WebServlet("/createdoctor")
public class CreateDoctor extends HttpServlet {
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
		req.getRequestDispatcher("/CreateDoctor.jsp").forward(req, resp);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
		Doctors doctor = null;
		
		// Retrieve inputs
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String phone = req.getParameter("phone");
		String street1 = req.getParameter("street1");
		String street2 = req.getParameter("street2");
		String city = req.getParameter("city");
		String state = req.getParameter("state");
		String zipcode = req.getParameter("zipcode");
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		
		if (username != null && password!= null && phone != null && street1 != null
			&& street2 != null && city != null && state != null && zipcode != null
			&& firstName != null && lastName != null) {
			try {
				doctor = new Doctors(username, password, phone, street1,
									 street2, city, state, zipcode, firstName,
									 lastName);
				// check whether the user name already exists in the DB
				if (usersDao.getUserByUserName(doctor.getUserName()) == null) {
					doctor = doctorsDao.create(doctor);
					messages.put("success", "Successfully created doctor with username: " + doctor.getUserName());
				} else {
					messages.put("fail", "The username already exists.");
				}
				req.setAttribute("doctor", doctor);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
		    }
		} else {
			messages.put("fail", "Please make sure all fields are filled correctly.");
		}
		req.getRequestDispatcher("/CreateDoctor.jsp").forward(req, resp);

	}
	
}