package controller;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import dao.UserDAOImpl;
import dao.UserPaymentDAO;
import dao.UserPaymentDAOImpl;
import model.User;
import model.UserPaymentMethod;

/**
 * Servlet implementation class AccountServlet
 */
@WebServlet("/account")
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserPaymentDAO userPaymentDAO;

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
			userPaymentDAO = new UserPaymentDAOImpl();
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
				UserPaymentMethod userPayMeth = userPaymentDAO.getCurrentUserPaymentMethod(userId);
				request.setAttribute("userPaymentMethod", userPayMeth);
				request.getRequestDispatcher("paymentInfo.jsp").forward(request, response);
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
