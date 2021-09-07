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

@WebServlet("/finddrugprices")
public class FindDrugPrices extends HttpServlet {
	protected DrugPricesDao drugPricesDao;
	
	@Override
	public void init() throws ServletException {
		drugPricesDao = DrugPricesDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		List<DrugPrices> drugPrices = new ArrayList<DrugPrices>();
		String drugName = req.getParameter("drugName");
		
		if (drugName != null && !drugName.trim().isEmpty()) {
			try {
				drugPrices = drugPricesDao.getDrugPricesByDrugName(drugName);
				req.setAttribute("success", "Displaying results for " + drugName);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
		}
		req.setAttribute("drugName", drugName);
		req.setAttribute("drugPrices", drugPrices);
		req.getRequestDispatcher("/FindDrugPrices.jsp").forward(req, resp);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		List<DrugPrices> drugPrices = new ArrayList<DrugPrices>();
		String drugName = req.getParameter("drugName");
		
		if (drugName != null && !drugName.trim().isEmpty()) {
			try {
				drugPrices = drugPricesDao.getDrugPricesByDrugName(drugName);
				req.setAttribute("success", "Displaying results for " + drugName);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
		}
		req.setAttribute("drugName", drugName);
		req.setAttribute("drugPrices", drugPrices);
		req.getRequestDispatcher("/FindDrugPrices.jsp").forward(req, resp);
	}
}