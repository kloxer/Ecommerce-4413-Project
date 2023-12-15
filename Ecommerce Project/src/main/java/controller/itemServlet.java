package controller;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ItemDAO;
import dao.ItemDAOImpl;
import dao.ProductDisplayDAO;
import dao.ProductDisplayDAOImpl;
import model.Item;
import model.ProductDisplay;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


@WebServlet("/ItemServlet")
public class itemServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String itemId = request.getParameter("id"); // Get the item ID as a String

        ProductDisplayDAO product = null;
        try {
            product = new ProductDisplayDAOImpl();
        } catch (NamingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

            ProductDisplay item = null;
            try {
                item = product.getProductById(Integer.parseInt(itemId));
            } catch (NumberFormatException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

         if (item != null) {
            HttpSession session = request.getSession(); // Initialize the session object
            session.setAttribute("item", item);
            request.getRequestDispatcher("item.jsp").forward(request, response);
        } else {
            // No item was found with the given itemId
            System.out.println(itemId + " not found");

        }

    }


    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // TODO: Implement your logic for handling POST requests here
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // TODO: Implement your logic for handling PUT requests here
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // TODO: Implement your logic for handling DELETE requests here
    }
    
    // TODO: Add any additional methods or logic specific to your itemServlet class
    
}
