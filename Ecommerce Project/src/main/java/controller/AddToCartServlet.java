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

@WebServlet("/addToCartServlet")
public class AddToCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the prodID from the request
        String prodID = request.getParameter("prodID");

        // Find the product with the given prodID
        // This code assumes that you have a method for getting a product by its ID
        ProductDisplayDAO productDisplayDAO = null;
        try {
            productDisplayDAO = new ProductDisplayDAOImpl();
        } catch (NamingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        ProductDisplay product = null;
        try {
            product = productDisplayDAO.getProductById(Integer.parseInt(prodID));
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Get the user's cart
        // This code assumes that you have a method for getting the user's cart
        // Get the user's cart from the session
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");

        // If the cart doesn't exist, create it and add it to the session
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        // Add the product to the cart
        cart.addItem(product);


        // Redirect the user back to the item page
        response.sendRedirect("cart.jsp");
    }
}
