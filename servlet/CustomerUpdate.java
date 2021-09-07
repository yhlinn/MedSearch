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


@WebServlet("/updatecustomer")
public class CustomerUpdate extends HttpServlet {
	protected CustomersDao customersDao;
	protected UsersDao usersDao;
	
	@Override
	public void init() throws ServletException {
		customersDao = CustomersDao.getInstance();
		usersDao = UsersDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		String destPage = null;
		
		if (session.getAttribute("customer") == null) {
			destPage = "/LoginError.jsp";
		} else {
			Customers customer = (Customers) session.getAttribute("customer");
			
			// Display existing information
			req.setAttribute("username", customer.getUserName());
			req.setAttribute("password", customer.getPassword());
			req.setAttribute("phone", customer.getPhone());
			req.setAttribute("street1", customer.getStreet1());
			req.setAttribute("street2", customer.getStreet2());
			req.setAttribute("city", customer.getCity());
			req.setAttribute("state", customer.getState());
			req.setAttribute("zipcode", customer.getZipcode());
			req.setAttribute("firstname", customer.getFirstName());
			req.setAttribute("lastname", customer.getLastName());
			req.setAttribute("age", customer.getAge());
			req.setAttribute("sex", customer.getSex());
			
			destPage = "/CustomerUpdate.jsp";
		}
		req.getRequestDispatcher(destPage).forward(req, resp);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
		Customers customer = null;
		Customers updatedCustomer = null;
		HttpSession session = req.getSession();
		String destPage = null;
		
		
		if (session.getAttribute("customer") == null) {
			destPage = "/LoginError.jsp";
		} else {
			destPage = "/CustomerUpdate.jsp";
			customer = (Customers) session.getAttribute("customer");
			
			// Retrieve inputs of new information
			String username = customer.getUserName();
			String password = req.getParameter("password");
			String phone = req.getParameter("phone");
			String street1 = req.getParameter("street1");
			String street2 = req.getParameter("street2");
			String city = req.getParameter("city");
			String state = req.getParameter("state");
			String zipcode = req.getParameter("zipcode");
			String firstName = req.getParameter("firstName");
			String lastName = req.getParameter("lastName");
			String age = req.getParameter("age");
			String sex = req.getParameter("sex");
			
			// Proceed with update if all fields are not null
			if (password!= null && phone != null && street1 != null
				&& street2 != null && city != null && state != null && zipcode != null
				&& firstName != null && lastName != null && age != null && sex != null) {
				try {
					// Retrieve the existing customer
					customer = customersDao.getCustomerFromUserName(username);
					
					// Update the customer if the user name exists
					// Otherwise, do nothing
					if (customer == null) {
						req.setAttribute("fail", "The username does not exist.");
					} else {
						updatedCustomer = new Customers(username, password, phone, street1,
								 						street2, city, state, zipcode,
								 						firstName, lastName, Integer.parseInt(age),
								 						Customers.Sex.valueOf(sex));
						customer = customersDao.update(customer, updatedCustomer);
						
						req.setAttribute("success", "Succesfully updated your profile.");
					}
					req.setAttribute("customer", customer);
				} catch (SQLException e) {
					e.printStackTrace();
					throw new IOException(e);
			    }
			} else {
				req.setAttribute("fail", "Please make sure all fields are filled correctly.");
			}
			
			// Display existing information, either updated or not
			req.setAttribute("username", customer.getUserName());
			req.setAttribute("password", customer.getPassword());
			req.setAttribute("phone", customer.getPhone());
			req.setAttribute("street1", customer.getStreet1());
			req.setAttribute("street2", customer.getStreet2());
			req.setAttribute("city", customer.getCity());
			req.setAttribute("state", customer.getState());
			req.setAttribute("zipcode", customer.getZipcode());
			req.setAttribute("firstname", customer.getFirstName());
			req.setAttribute("lastname", customer.getLastName());
			req.setAttribute("age", customer.getAge());
			req.setAttribute("sex", customer.getSex());
		}
		req.getRequestDispatcher(destPage).forward(req, resp);
	}
	
}
