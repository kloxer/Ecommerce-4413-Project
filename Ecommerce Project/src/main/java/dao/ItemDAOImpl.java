package dao;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.NamingException;
import javax.sql.DataSource;

import model.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO{
	
	private ServletContext context;
	
    public ItemDAOImpl(ServletContext context) {
        this.context = context;
    }
    
    private Connection getConnection() throws SQLException, ClassNotFoundException {
    	Class.forName("org.sqlite.JDBC");
    	String dbLocation = this.context.getRealPath("/dbFile/ecomDB.db");
    	
        return DriverManager.getConnection("jdbc:sqlite:" + dbLocation);
    }

    public List<Item> getAllItems() {
    	
    }
    	try {
	    	Class.forName("com.mysql.cj.jdbc.Driver"); // may not need actually

	    	Connection con = DriverManager.getConnection
	    			 ("jdbc:mysql://localhost:3306/lab6","root","EECS4413");
	    	
	    	Statement stmt =con.createStatement();
	    	
	    	
    	
    	
        List<Item> items = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Item");
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Item item = new Item();
                item.setName(resultSet.getString("name"));
                item.setPrice(resultSet.getDouble("price"));
                items.add(item);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return items;
    }
}


