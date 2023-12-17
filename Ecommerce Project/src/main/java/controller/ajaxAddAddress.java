package controller;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.*;
import model.Address;
import model.User;
import model.UserPaymentMethod;


@WebServlet("/ajaxAddAddress")
public class ajaxAddAddress  extends HttpServlet  {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forward GET requests to the POST method
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AddressDAO addressDAO = null;
        try {
           addressDAO = new AddressDAOImpl();
        } catch (NamingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String requestedWithHeader = request.getHeader("X-Requested-With");
        if ("XMLHttpRequest".equals(requestedWithHeader)) {
            int userId = Integer.parseInt(request.getParameter("userId"));

            // Obtain details from the request parameters
            String unitNumber = request.getParameter("unitNumber");
            String addressLine1 = request.getParameter("addressLine1");
            String city = request.getParameter("city");
            String region = request.getParameter("region");
            String postalCode = request.getParameter("postalCode");
            String country = request.getParameter("country");
    
            // Create Address object with details
            Address newAddress = new Address();
            newAddress.setUnitNumber(unitNumber);
            newAddress.setAddressLine1(addressLine1);
            newAddress.setCity(city);
            newAddress.setRegion(region);
            newAddress.setPostalCode(postalCode);
            newAddress.setCountry(country);
    
            // Add new address database
            boolean addressAdded = addressDAO.addAddress(userId, newAddress);
    
            if (addressAdded) {
                // If adding address is successful, send AJAX message to the client
                response.setContentType("text/plain");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("Address added successfully!");
            } else {
                // If adding address is unsuccessful, send AJAX message to the client  
                response.setContentType("text/plain");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("Address failed to be added.");
            }

        }


    }
    
}
