package controller;
import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ProductDisplayDAO;
import dao.ProductDisplayDAOImpl;
import model.Cart;
import model.ProductDisplay;


@WebServlet("/updateQuantity")
public class updateQuantity extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the product ID and new quantity from the request parameters
        String prodID = request.getParameter("prodID");
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        // Get the cart from the session
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        // Update the quantity of the item in the cart
        cart.updateQuantity(prodID, quantity);

        // Redirect the user back to the cart page
        response.sendRedirect("cart.jsp");
    }

}