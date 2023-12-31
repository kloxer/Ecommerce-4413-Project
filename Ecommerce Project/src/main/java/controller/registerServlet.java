package controller;


import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.*;
import model.User;

/**
 * Servlet implementation class registerServlet
 */
@WebServlet("/registerServlet")
public class registerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	@Override
	public void init() throws ServletException {
		super.init();
		try {
			userDAO = new UserDAOImpl();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String phoneNumber = request.getParameter("phone");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        User newUser = new User();
        newUser.setFirstName(firstname);
        newUser.setLastName(lastname);
        newUser.setPhoneNumber(phoneNumber);
        newUser.setUsername(username);
        newUser.setPassword(password);
        
        //Check if entered username Exists
        boolean userExists = userDAO.userExists(username);
        if (userExists) {
			//Notify to pick another username
			request.setAttribute("error", "Username: " + username + " already exists. "
					+ "Please choose a different username.");
			request.setAttribute("firstname", firstname);
			request.setAttribute("lastname", lastname);
			request.setAttribute("phone", phoneNumber);
			request.setAttribute("username", username);
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}
        
        //Attempt to add user to database
        boolean userAdded = userDAO.addUser(newUser);

		if (userAdded) {
			// Successful registration
			User user = userDAO.getUserByUsernamePassword(username, password);
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			// Redirect to account for additional account info input
			response.sendRedirect("./account");
		} else {
			response.sendRedirect("register.jsp");
		}
        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
