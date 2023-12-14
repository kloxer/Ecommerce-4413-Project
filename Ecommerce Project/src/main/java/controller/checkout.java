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


@WebServlet("/checkout")
public class checkout extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the session
        HttpSession session = request.getSession();

        // Check if the user is logged in
        if (session.getAttribute("user") != null) {
            // If the user is logged in, redirect them to the checkout page
            response.sendRedirect("checkout.jsp");
        } else {
            // If the user is not logged in, redirect them to the notLoggedin.jsp page
            response.sendRedirect("notLoggedin.jsp");
        }
    }
}