package controller;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Address;
import model.Cart;
import model.ProductDisplay;
import model.User;
import model.UserPaymentMethod;

import dao.*;


@WebServlet("/checkout")
public class checkout extends HttpServlet {

    private UserPaymentDAO userPaymentDAO;
	private AddressDAO addressDAO;
   
    public checkout(){
        try {
			//Initialize the DAOs
            userPaymentDAO = new UserPaymentDAOImpl();
            addressDAO = new AddressDAOImpl();
		} catch (NamingException e) {
			e.printStackTrace();
		}

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the session
        HttpSession session = request.getSession();

        // Check if the user is logged in
        if (session.getAttribute("user") != null) {
            // If the user is logged in, redirect them to the checkout page

            User user = (User) session.getAttribute("user");
            int userId = user.getUserId();
			UserPaymentMethod userPayMeth = userPaymentDAO.getLatestUserPaymentMethod(userId);
            session.setAttribute("latestPayMethod", userPayMeth);

			Address userAdd = addressDAO.getLatestAddressMethod(user.getUserId());
			session.setAttribute("latestAddress", userAdd);

			List<Address> addrList = addressDAO.getUserAddresses(userId);
			session.setAttribute("addrList", addrList);




            //send info to checkout.jsp
            request.getRequestDispatcher("checkout.jsp").forward(request, response);

        } else {
            // If the user is not logged in, redirect them to the notLoggedin.jsp page
            response.sendRedirect("notLoggedin.jsp");
        }
    }
}