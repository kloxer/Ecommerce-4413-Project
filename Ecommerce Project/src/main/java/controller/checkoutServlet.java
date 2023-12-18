package controller;
import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AddressDAO;
import dao.AddressDAOImpl;
import model.Address;
import model.Cart;
import model.User;



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

        User user = (User) session.getAttribute("user");

        String paymentInfo = request.getParameter("paymentInfo");
        int addressInfo = Integer.parseInt(request.getParameter("addressInfo")); // Parse the string value to an integer
        int userId = user.getUserId();
        
        AddressDAO addressDAO = null;

        try {
            addressDAO = new AddressDAOImpl();
        } catch (NamingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Address address = addressDAO.getAddressBasedOnID(addressInfo);
        String currAddr = address.getAddressLine1();

        System.out.println("Payment Info: " + paymentInfo);
        System.out.println("AddressID Info: " + addressInfo);
        System.out.println("UserID Info: " + addressInfo);
        System.out.println("street line Info: " + currAddr);

        
        
    



         // After processing the order, redirect to showorder.jsp
        response.sendRedirect("index.jsp");









    }
}