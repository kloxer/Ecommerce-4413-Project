package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.*;
import model.*;

@WebServlet("/products")
public class ProductsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ProductsServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Create an instance of your ProductDisplayDAO to retrieve data
        ProductDisplayDAO productDisplayDAO;

        try {

			productDisplayDAO = new ProductDisplayDAOImpl();
			List<ProductDisplay> productDisplays = productDisplayDAO.getAllProducts();
            request.setAttribute("productDisplays", productDisplays);

            //giving to index.jsp for now
            request.getRequestDispatcher("/index.jsp").forward(request, response);
  
		} catch (NamingException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

 
        request.getRequestDispatcher("/index.jsp").forward(request, response);

        }
    

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
