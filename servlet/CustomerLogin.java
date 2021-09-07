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

@WebServlet("/customerlogin")
public class CustomerLogin extends HttpServlet {
	protected LoginDao loginDao;
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		String destPage = null;
		if (session != null) {
			if (session.getAttribute("customer") != null) {
				destPage = "/CustomerHome.jsp";
			} else {
				destPage = "/CustomerLogin.jsp";
			}
		}
		req.getRequestDispatcher(destPage).forward(req, resp);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
		LoginDao loginDao = LoginDao.getInstance();
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String destPage = null;
		
		try {
			Login login = new Login(username, password);
			Customers customer = loginDao.validateCustomer(login);
			if (customer != null) {
				destPage = "/CustomerHome.jsp";
				HttpSession session = req.getSession();
				session.setAttribute("customer", customer);
			} else {
				destPage = "/CustomerLogin.jsp";
				String failMessage = "Invalid Username or Password.";
				req.setAttribute("fail", failMessage);
			}	
			req.getRequestDispatcher(destPage).forward(req, resp);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
		}
	}

}


