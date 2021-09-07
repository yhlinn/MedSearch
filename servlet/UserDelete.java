package servlet;
import dal.*;
import model.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.Session;

@WebServlet("/userdelete")
public class UserDelete extends HttpServlet {
	protected UsersDao usersDao;
	@Override
	public void init() throws ServletException {
		usersDao = UsersDao.getInstance();
	}
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		String destPage = null;
        
        if (session.getAttribute("customer") == null &&
        		session.getAttribute("admin") == null) {
        	destPage = "/LoginError.jsp";
        } else {
        	destPage = "/UserDelete.jsp";
        }
        req.getRequestDispatcher(destPage).forward(req, resp);
	}
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        
		HttpSession session = req.getSession();
		String destPage = null;
		
        if (session.getAttribute("customer") == null &&
        		session.getAttribute("admin") == null) {
        	destPage = "/LoginError.jsp";
        } else {
        	destPage = "/UserDelete.jsp";
        	 // Retrieve and validate name.
            String userName = req.getParameter("username");
            if (userName == null || userName.trim().isEmpty()) {
                messages.put("fail", "Invalid Username");
                messages.put("disableSubmit", "false");
            } else {
    	        try {
    	        	// Check if the user exists
    		        Users user = usersDao.getUserByUserName(userName);
    		        if (user == null) {
    		        	messages.put("fail", "The username does not exist.");
    		        } else {
    		            // Delete the user
    		        	user = usersDao.delete(user);
    			        if (user == null) {
    			        	// log out
    			        	session.removeAttribute("admin");
    			        	session.removeAttribute("doctor");
    			        	session.removeAttribute("pharmacy");
    			        	session.removeAttribute("customer");
    			        	
    			            messages.put("success", "Successfully deleted " + userName);
    			            messages.put("disableSubmit", "true");
    			        } else {
    			        	messages.put("fail", "Failed to delete " + userName);
    			        	messages.put("disableSubmit", "false");
    			        }	
    		        }
    	        } catch (SQLException e) {
    				e.printStackTrace();
    				throw new IOException(e);
    	        }
            }
        }
        req.getRequestDispatcher(destPage).forward(req, resp);
    }
}