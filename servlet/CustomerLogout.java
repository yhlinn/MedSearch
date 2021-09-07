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

@WebServlet("/customerlogout")
public class CustomerLogout extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		if (session != null) {
			session.removeAttribute("customer");
			req.getRequestDispatcher("/CustomerLogin.jsp").forward(req, resp);	
		}
	}

}
