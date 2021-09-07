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


@WebServlet("/findprescriptions")
public class FindPrescriptions extends HttpServlet {
	protected PrescriptionsDao prescriptionsDao;
	
	@Override
	public void init() throws ServletException {
		prescriptionsDao = PrescriptionsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Prescriptions prescription = null;
		String inputPrescriptionId = req.getParameter("prescriptionId");
		
		if (inputPrescriptionId != null && !inputPrescriptionId.trim().isEmpty()) {
			try {
				// convert it back to int to be used in DAO
				int prescriptionId = Integer.parseInt(inputPrescriptionId);
				prescription = prescriptionsDao.getPrescriptionByPrescriptionId(prescriptionId);
				req.setAttribute("success", "Displaying results for prescription with ID " + prescriptionId);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
		}
		req.setAttribute("prescription", prescription);
		req.getRequestDispatcher("/FindPrescriptions.jsp").forward(req, resp);	
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Prescriptions prescription = null;
		String inputPrescriptionId = req.getParameter("prescriptionId");
		
		if (inputPrescriptionId != null && !inputPrescriptionId.trim().isEmpty()) {
			try {
				// convert it back to int to be used in DAO
				int prescriptionId = Integer.parseInt(inputPrescriptionId);
				prescription = prescriptionsDao.getPrescriptionByPrescriptionId(prescriptionId);
				req.setAttribute("success", "Displaying results for prescription with ID " + prescriptionId);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
		}
		req.setAttribute("prescription", prescription);
		req.getRequestDispatcher("/FindPrescriptions.jsp").forward(req, resp);	
	}
}