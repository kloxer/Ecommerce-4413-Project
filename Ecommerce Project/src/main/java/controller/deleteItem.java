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


@WebServlet("/deleteItem")
public class deleteItem extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the product ID from the request parameters
        String prodID = request.getParameter("prodID");

        // Check if the product ID is not null before parsing it
        if (prodID != null && !prodID.isEmpty()) {
            // Get the cart from the session
            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("cart");

            // Delete the item from the cart
            cart.removeItemUsingID(prodID);

            // Redirect the user back to the cart page
            response.sendRedirect("cart.jsp");
        } else {
            // Handle the case where the product ID is null or empty
            // You might want to redirect the user to an error page or display an error message
        }
    }
}