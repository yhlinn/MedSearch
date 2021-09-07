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


@WebServlet("/prescriptionsupdate")
public class PrescriptionsUpdate extends HttpServlet {
	protected PrescriptionsDao prescriptionsDao;
	
	@Override
	public void init() throws ServletException {
		prescriptionsDao = PrescriptionsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        Prescriptions prescription = null;
        
        // Retrieve input ID and validate
        String inputPrescriptionId = req.getParameter("prescriptionId");
		
		if (inputPrescriptionId != null && !inputPrescriptionId.trim().isEmpty()) {
			try {
				// Convert the ID back to int to be used in DAO
				int prescriptionId = Integer.parseInt(inputPrescriptionId);
				prescription = prescriptionsDao.getPrescriptionByPrescriptionId(prescriptionId);
				// Check if a prescription with the given ID exists
				if (prescription == null) {
					messages.put("fail", "Prescription ID does not exist.");
				}
				req.setAttribute("prescription", prescription);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
		}
		req.getRequestDispatcher("/PrescriptionsUpdate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
		// Map for storing messages
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
        Prescriptions prescription = null;
        
        // Retrieve input ID and validate
        String inputPrescriptionId = req.getParameter("prescriptionId");
        
        if (inputPrescriptionId == null || inputPrescriptionId.trim().isEmpty()) {
        	messages.put("fail", "Please enter a valid Prescription ID.");
        } else {
        	try {
	        	// Convert the ID back to int to be used in DAO
				int prescriptionId = Integer.parseInt(inputPrescriptionId);
				prescription = prescriptionsDao.getPrescriptionByPrescriptionId(prescriptionId);
				// Check if a prescription with the given ID exists
				if (prescription == null) {
					messages.put("fail", "Prescription ID does not exist. No update is performed.");
				} else {
					// Retrieve input for new fill date
					Date newFillDate = Date.valueOf(req.getParameter("newFillDate"));
					// Perform update on the prescription
					prescription = prescriptionsDao.updateFillDate(prescription, newFillDate);
					messages.put("success", "Successfully updated fill date for prescription with ID " +
								 prescriptionId);
				}
				req.setAttribute("prescription", prescription);
        	} catch (SQLException e) {
        		e.printStackTrace();
        		throw new IOException(e);
        	}
        }
		req.getRequestDispatcher("/PrescriptionsUpdate.jsp").forward(req, resp);
	}
}
