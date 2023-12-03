package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ItemDAO;
import dao.ItemDAOImpl;
import model.Item;

import java.io.IOException;
import java.util.List;

@WebServlet("/items")
public class ItemController extends HttpServlet {
    private ItemDAO itemDAO;

	public ItemController() {
		super();
	}
    
    @Override
    public void init() throws ServletException {
        super.init();
        itemDAO = new ItemDAOImpl(getServletContext());
    }

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException {
		List<Item> items = itemDAO.getAllItems();
		request.setAttribute("items", items);
		request.getRequestDispatcher("jsp/index.jsp").forward(request, response);
    }
    
}
