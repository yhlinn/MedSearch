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


@WebServlet("/finddoctorlastname")
public class FindDoctorLastName extends HttpServlet {
	protected DoctorsDao doctorDao;
	
	@Override
	public void init() throws ServletException {
		doctorDao = DoctorsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		List<Doctors> doctors = new ArrayList<Doctors>();
		
		String lastname = req.getParameter("lastname");
		
		if (lastname != null && !lastname.trim().isEmpty()) {
			
			
			try {
				doctors = doctorDao.getDoctorFromLastName(lastname);
				req.setAttribute("success", "Displaying results for " + lastname);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
		}
		req.setAttribute("doctors", doctors);
		req.getRequestDispatcher("/FindDoctorLastName.jsp").forward(req, resp);	
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		List<Doctors> doctors = new ArrayList<Doctors>();
		String lastname = req.getParameter("lastname");
		
		if (lastname != null && !lastname.trim().isEmpty()) {
			try {
				doctors = doctorDao.getDoctorFromLastName(lastname);
				req.setAttribute("success", "Displaying results for " + lastname);
				
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
		}
		req.setAttribute("doctors", doctors);
		req.getRequestDispatcher("/FindDoctorLastName.jsp").forward(req, resp);	
	}
}