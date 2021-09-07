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

@WebServlet("/home")
public class Home extends HttpServlet {
	protected LoginDao loginDao;
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
		HttpSession session = req.getSession();
		String destPage = null;
		
		if (session.getAttribute("customer") != null) {
			destPage = "/CustomerHome.jsp";	
		} else if (session.getAttribute("pharmacy") != null) {
			destPage = "/PharmacyHome.jsp";
		} else if (session.getAttribute("doctor") != null) {
			destPage = "/DoctorHome.jsp";
		} else {
			destPage = "/CustomerLogin.jsp";
		}
		req.getRequestDispatcher(destPage).forward(req, resp);
	}

}