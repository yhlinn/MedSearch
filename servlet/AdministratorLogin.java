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

@WebServlet("/medproviderlogin")
public class AdministratorLogin extends HttpServlet {
	protected LoginDao loginDao;
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		String destPage = null;

		if (session != null) {
			if (session.getAttribute("doctor") != null) {
				destPage = "/DoctorHome.jsp";
			} else if (session.getAttribute("pharmacy") != null) {
				destPage = "/PharmacyHome.jsp";
			} else {
				destPage = "/AdministratorLogin.jsp";
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
			Doctors doctor = loginDao.validateDoctor(login);
			Pharmacies pharmacy = loginDao.validatePharmacy(login);
			HttpSession session = req.getSession();
			
			if (doctor != null) {
				destPage = "/DoctorHome.jsp";
				session.setAttribute("admin", doctor);
				session.setAttribute("doctor", doctor);
			} else if (pharmacy != null) {
				destPage = "/PharmacyHome.jsp";
				session.setAttribute("admin", pharmacy);
				session.setAttribute("pharmacy", pharmacy);	
			} else {
				destPage = "/AdministratorLogin.jsp";
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