package controller;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ItemDAO;
import dao.ItemDAOImpl;
import model.Item;

import java.io.IOException;
import java.sql.SQLException;
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
		try {
			itemDAO = new ItemDAOImpl();
		} catch (NamingException e) {
			e.printStackTrace();
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		List<Item> items = itemDAO.findAllItems();
		request.setAttribute("items", items);
		request.getRequestDispatcher("/index.jsp").forward(request, response);

	}

}
