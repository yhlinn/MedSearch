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


@WebServlet("/create")
public class Create extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.getRequestDispatcher("/Create.jsp").forward(req, resp);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
		String registerUser = req.getParameter("user");
		String destPage = "Create.jsp";
		
		if (registerUser.equals("customer")) {
			destPage = "/CreateCustomers.jsp";
		} else if (registerUser.equals("doctor")) { 
			destPage = "/CreateDoctor.jsp";
		} else if (registerUser.equals("pharmacy")) {
			destPage = "/CreatePharmacy.jsp";
		} else {
			req.setAttribute("fail", "Please choose an option.");
		}
		req.getRequestDispatcher(destPage).forward(req, resp);

	}
	
}