package controller;
import java.io.IOException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AddressDAO;
import dao.AddressDAOImpl;
import dao.orderDAO;
import dao.orderDAOImpl;
import model.Address;
import model.Cart;
import model.Purchase;
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
        System.out.println("POST request received at CheckoutServlet");
        HttpSession session = request.getSession();
        
        Cart cart = (Cart) session.getAttribute("cart");


    
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
        int currAddr = address.getId();

        System.out.println("Payment Info: " + paymentInfo);
        System.out.println("AddressID Info: " + addressInfo);
        System.out.println("UserID Info: " + addressInfo);

        orderDAO orderDAO;
        List<Purchase> allPurchases;
        try {
             orderDAO = new orderDAO();
             orderDAO.addOrderToDatabase(cart, userId, currAddr);
             System.out.println("Order added to database");

            allPurchases = orderDAO.getAllPurchasesByUserId(userId);


            

        } catch (NamingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        

        //Set session attributes so that JSP can output them

        session.setAttribute("paymentUsedForPurchase", paymentInfo);
        session.setAttribute("addressUsedForPurchase", address);
  



         // After processing the order, redirect to showorder.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("showorder.jsp");
        dispatcher.forward(request, response);





    }
}