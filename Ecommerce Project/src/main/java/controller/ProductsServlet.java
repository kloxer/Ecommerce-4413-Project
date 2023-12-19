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
import javax.servlet.http.HttpSession;

@WebServlet("/products")
public class ProductsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private List<ProductDisplay> productDisplays;
    
    public ProductsServlet() {
        super();
    }
    
    public void init() throws ServletException {
        super.init();
        ProductDisplayDAO productDisplayDAO;
        try {
        	// Create an instance of your ProductDisplayDAO to retrieve data
            productDisplayDAO = new ProductDisplayDAOImpl();
            productDisplays = productDisplayDAO.getAllProducts();
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	

    	init();
    	
    	//Initalize session
    	HttpSession session = request.getSession();
    	String sortType = request.getParameter("sort");
    	String brand = request.getParameter("brand");
    	
    	//Sort by Name and Price
        if (sortType != null && !sortType.isEmpty()) {
            if (sortType.equals("priceasc")) {
                ProductDisplay.sortByPriceAscending(productDisplays);
            } else if (sortType.equals("pricedesc")) {
                ProductDisplay.sortByPriceDescending(productDisplays);
            } else if (sortType.equals("nameasc")) {
                ProductDisplay.sortByNameAscending(productDisplays);
            } else if (sortType.equals("namedesc")) {
                ProductDisplay.sortByNameDescending(productDisplays);
            }
        }
        
        //Filter by Phone Brand or Category
        if (brand != null && !brand.isEmpty()) {
            List<ProductDisplay> filteredList = ProductDisplay.filterByNameContains(productDisplays, brand);
            session.setAttribute("productDisplays", filteredList);
        } else {
            session.setAttribute("productDisplays", productDisplays);
        }
    	
    	//Forward to index	
    	request.getRequestDispatcher("/index.jsp").forward(request, response);
     }
    

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
