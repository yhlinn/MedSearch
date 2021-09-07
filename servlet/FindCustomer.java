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


@WebServlet("/findcustomer")
public class FindCustomer extends HttpServlet {
	protected CustomersDao customerDao;
	
	@Override
	public void init() throws ServletException {
		customerDao = CustomersDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Customers customer = null;
		String username = req.getParameter("username");
		String destPage = "/FindCustomer.jsp";
		HttpSession session = req.getSession();
		
		if (session.getAttribute("admin") != null) {
			if (username != null && !username.trim().isEmpty()) {
				try {
					customer = customerDao.getCustomerFromUserName(username);
					req.setAttribute("success", "Displaying results for " + username);	
				} catch (SQLException e) {
					e.printStackTrace();
					throw new IOException(e);
				}
			}
		} else {
			destPage = "/LoginError.jsp";
		}
		req.setAttribute("resultCustomer", customer);
		req.getRequestDispatcher(destPage).forward(req, resp);	
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Customers customer = null;
		String username = req.getParameter("username");
		String destPage = "/FindCustomer.jsp";
		HttpSession session = req.getSession();
		
		if (session.getAttribute("admin") != null) {
			if (username != null && !username.trim().isEmpty()) {
				try {
					customer = customerDao.getCustomerFromUserName(username);
					req.setAttribute("success", "Displaying results for " + username);	
				} catch (SQLException e) {
					e.printStackTrace();
					throw new IOException(e);
				}
			}
		} else {
			destPage = "/LoginError.jsp";
		}
		req.setAttribute("resultCustomer", customer);
		req.getRequestDispatcher(destPage).forward(req, resp);	
	}
}