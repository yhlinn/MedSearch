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


@WebServlet("/finddrugs")
public class FindDrugs extends HttpServlet {
	protected DrugsDao drugsDao;
	
	@Override
	public void init() throws ServletException {
		drugsDao = DrugsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Drugs drug = null;
		
		String drugId = req.getParameter("drugId");
		
		if (drugId != null && !drugId.trim().isEmpty()) {
			try {
				drug = drugsDao.getDrugById(drugId);
				req.setAttribute("success", "Displaying results for " + drugId);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
		}
		req.setAttribute("drug", drug);
		req.getRequestDispatcher("/FindDrugs.jsp").forward(req, resp);	
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Drugs drug = null;
		String drugId = req.getParameter("drugId");
		
		if (drugId != null && !drugId.trim().isEmpty()) {
			try {
				drug = drugsDao.getDrugById(drugId);
				req.setAttribute("success", "Displaying results for " + drugId);
				
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
		}
		req.setAttribute("drug", drug);
		req.getRequestDispatcher("/FindDrugs.jsp").forward(req, resp);	
	}
}