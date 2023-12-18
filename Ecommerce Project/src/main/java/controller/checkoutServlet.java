package controller;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Cart;



@WebServlet("/checkoutServlet")
public class checkoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.getWriter().println("GET request received at CheckoutServlet");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.getWriter().println("POST request received at CheckoutServlet");
        System.out.println("POST request received at CheckoutServlet");
        HttpSession session = request.getSession();
        
        Cart cart = (Cart) session.getAttribute("cart"); //get cart from session, assume already in session

         // After processing the order, redirect to showorder.jsp
            response.sendRedirect("index.jsp");









    }
}