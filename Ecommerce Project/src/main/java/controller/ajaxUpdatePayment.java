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


@WebServlet("/ajaxUpdatePayment")
public class ajaxUpdatePayment extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestedWithHeader = request.getHeader("X-Requested-With");
        if ("XMLHttpRequest".equals(requestedWithHeader)) {
            // This is an AJAX request
            System.out.println("AJAX request received");
            int userId = Integer.parseInt(request.getParameter("userId"));
            int upmId = Integer.parseInt(request.getParameter("upmId"));
            int cvv = Integer.parseInt(request.getParameter("cvv"));
            String cardProvider = request.getParameter("cardProvider");
            String cardNumber = request.getParameter("cardNumber");
            int expYear = Integer.parseInt(request.getParameter("expYear"));
            int expMonth = Integer.parseInt(request.getParameter("expMonth"));

   

            // Create a UserPaymentMethod object with updated information
            UserPaymentMethod updatedPaymentMethod = new UserPaymentMethod();
            updatedPaymentMethod.setUPM_ID(upmId);
            updatedPaymentMethod.setUser_id(userId);
            updatedPaymentMethod.setCVV(cvv);
            updatedPaymentMethod.setCardProvider(cardProvider);
            updatedPaymentMethod.setCardNumber(cardNumber);
            updatedPaymentMethod.setExp_year(expYear);
            updatedPaymentMethod.setExp_month(expMonth);

            UserPaymentDAO userPaymentDAO = null;
            try {
                userPaymentDAO = new UserPaymentDAOImpl();
            } catch (NamingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            boolean updatePayment = userPaymentDAO.updateUserPaymentMethod(updatedPaymentMethod);

            if (updatePayment) { 
                // Payment method updated successfully
                response.setContentType("text/plain");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("Payment method updated successfully!");
            }



        } else {
                response.setContentType("text/plain");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("Not updated successfully!");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forward GET requests to the POST method
        doPost(request, response);
    }
}