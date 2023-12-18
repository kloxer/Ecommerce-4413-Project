package controller;

import java.io.IOException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AddressDAO;
import dao.AddressDAOImpl;
import dao.UserDAO;
import dao.UserDAOImpl;
import dao.UserPaymentDAO;
import dao.UserPaymentDAOImpl;
import model.Address;
import model.User;
import model.UserPaymentMethod;

/**
 * Servlet implementation class AccountServlet
 */
@WebServlet("/account")
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserPaymentDAO userPaymentDAO;
	private AddressDAO addressDAO;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AccountServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init() throws ServletException {
		super.init();
		try {
			//Initialize the DAOs
            userPaymentDAO = new UserPaymentDAOImpl();
            addressDAO = new AddressDAOImpl();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		if (session != null) {
			User user = (User) session.getAttribute("user");

			if (user != null) {
				int userId = user.getUserId();
				
				UserPaymentMethod userPayMeth = userPaymentDAO.getLatestUserPaymentMethod(userId);
				request.setAttribute("latestPayMethod", userPayMeth);

				Address userAdd = addressDAO.getLatestAddressMethod(user.getUserId());
				request.setAttribute("latestAddress", userAdd);
				
				//Check if User has never filled out New Account forum
				if(userPayMeth.getCardNumber().isBlank() && userAdd.getAddressLine1().isBlank()) {
					//If new account, redirect to new account forum
					request.getRequestDispatcher("new_account.jsp").forward(request, response);
				} else {
						
						String section = request.getParameter("section");
						
						if(section == null) {
							//Redirect to General Info for account
							request.getRequestDispatcher("account_general.jsp").forward(request, response);
						} else if (section.equals("paymeth")){
							//Redirect to Payment Method for account
							request.getRequestDispatcher("account_paymeth.jsp").forward(request, response);
						} else if (section.equals("addresses")){
							List<Address> addrList = addressDAO.getUserAddresses(userId);
							request.setAttribute("addrList", addrList);
							//Redirect to Addresses for Account
							request.getRequestDispatcher("account_addresses.jsp").forward(request, response);
						} else if (section.equals("addressadd")){
							//Redirect to add new Address for account
							request.getRequestDispatcher("account_add_address.jsp").forward(request, response);
						}
						
				}
				
			} else {
				// redirect to login if no user
				response.sendRedirect("login.jsp");
			}
		} else {
			// redirect to login if no user
			response.sendRedirect("login.jsp");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
