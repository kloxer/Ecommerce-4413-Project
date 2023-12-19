package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AddressDAO;
import dao.AddressDAOImpl;
import dao.ProductDisplayDAO;
import dao.ProductDisplayDAOImpl;
import dao.UserDAOImpl;
import dao.UserPaymentDAO;
import dao.UserPaymentDAOImpl;
import model.Address;
import model.ProductDisplay;
import model.User;
import model.UserPaymentMethod;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAOImpl userDAO;
	private UserPaymentDAO userPaymentDAO;
	private AddressDAO addressDAO;
	private ProductDisplayDAO productDisplayDAO;

    public void init() throws ServletException {
        super.init();
        try {
        	// Initialize DAOs
            userDAO = new UserDAOImpl();
            userPaymentDAO = new UserPaymentDAOImpl();
            addressDAO = new AddressDAOImpl();
            productDisplayDAO = new ProductDisplayDAOImpl();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session != null) {
            User user = (User) session.getAttribute("user");

            // Check if User is an Admin
            if (user != null && user.isAdmin()) {
            
            	// Obtain section parameter Admin wants
            	String section = request.getParameter("section");
            	// Obtain action parameter of Admin 
            	String action = request.getParameter("action");
            	
            	//Check what section Admin selected
            	 if (section != null && section.equals("inventory")) {
            		 List<ProductDisplay> allProducts = null;
					
            		 try {
						allProducts = productDisplayDAO.getAllProducts();
					} catch (SQLException e) {
						e.printStackTrace();
					}
            		 
            		 session.setAttribute("productList", allProducts);
                     request.getRequestDispatcher("admin_inventory_maintenance.jsp").forward(request, response);
            	 }else if(section != null && section.equals("sales")) {
            		 //REDIRECT HERE FOR SALES HISTORY
            		 
            	 }
            	
            	
            	// Action is Edit User (admin wants to edit a selected User)
                if (action != null && action.equals("EditUser")) {
                	//Get the ID of selected USer
                    String selectedUserId = request.getParameter("selectedUser");
                    User userToEdit = userDAO.getUser(Integer.parseInt(selectedUserId));

                    // Addresses of user to edit
                    List<Address> addrList = addressDAO.getUserAddresses(userToEdit.getUserId());
                    request.setAttribute("addrList", addrList);
                    //Get latest address of User
                    Address userAdd = addressDAO.getLatestAddressMethod(userToEdit.getUserId());
                    request.setAttribute("latestAddress", userAdd);

                    // Get latest payment method to edit
                    UserPaymentMethod latestPaymentMethod = userPaymentDAO.getLatestUserPaymentMethod(userToEdit.getUserId());
                    request.setAttribute("latestPaymentMethod", latestPaymentMethod);

                    request.setAttribute("selectedUser", userToEdit);
                    request.getRequestDispatcher("admin_edit_user.jsp").forward(request, response);
                }
            	
                // If no action or section, obtain list of users for drop down list
                // Save the list in the session and forward
                if (section == null && action == null) {
                    request.setAttribute("userList", userDAO.getAllUsers());
                    request.getRequestDispatcher("admin_account_maintenance.jsp").forward(request, response);
                }

            } else {
                // User is not an admin or logged out, redirect to login
                response.sendRedirect("login.jsp");
            }
        } else {
            // Session doesn't exist, redirect to login
            response.sendRedirect("login.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
