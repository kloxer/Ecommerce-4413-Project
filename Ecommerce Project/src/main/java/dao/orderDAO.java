package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.Cart;
import model.ProductDisplay;

public class orderDAO implements orderDAOImpl {
        private DataSource dSource;

    public orderDAO() throws NamingException {
        Context ctx = new InitialContext();
        dSource = (DataSource) ctx.lookup("java:comp/env/jdbc/ecomDB");
    }
 
    private Connection getConnection() throws SQLException {
        return dSource.getConnection();
    }

    @Override
    public void addOrderToDatabase(Cart cart, int userID, int addressID) {
        // TODO Auto-generated method stub


        //Need to add to Purchase_Item and Purchase tables
        //Purchase_item schema (purchase_id,item_id,quantity,price_at_purchase)
        //Purchase schema (purchase_id, user_id ,address_id,date,total,isFilled) 

        //So I can do purcahse_item first, 

    Connection connection = null;
    PreparedStatement statement = null;

    try {
        connection = getConnection(); // Assuming you have a method to get the connection
        connection.setAutoCommit(false); // Start transaction

        // Get the unique address ID
        int uniqueOrderID = generateUniqueOrderID(connection);

        //Add to Purchase table first before adding individual items to other table
        Date currentTimestamp = new Date(System.currentTimeMillis());
        // Add the purchase to the Purchase table with address info
        addPurchaseToDatabase(uniqueOrderID, userID, addressID, currentTimestamp, cart.getTotalPrice(), false);

        String sql = "INSERT INTO Purchase_Item (purchase_id,item_id,quantity,price_at_purchase) VALUES (?, ?, ?, ?)";
        statement = connection.prepareStatement(sql);

        //Add individual items to Purchase_Item table
        for (Map.Entry<ProductDisplay, Integer> entry : cart.getItems().entrySet()) {
            ProductDisplay product = entry.getKey();
            int quantity = entry.getValue();

            statement.setInt(1, uniqueOrderID);
            statement.setInt(2, product.getId());
            System.out.println("Product ID: " + product.getId());
            statement.setInt(3, cart.getCurrentQuantity(product));
            statement.setDouble(4, product.getPrice());
            statement.addBatch();
        }

        statement.executeBatch(); // Execute all the insert statements as a batch
        connection.commit(); // Commit the transaction



    } catch (SQLException ex) {
        if (connection != null) {
            try {
                connection.rollback(); // Rollback the transaction in case of an error
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        ex.printStackTrace();
    } 
         catch (Exception e) {
            System.out.println("Error: " + e);
        }
        
    }

    
public void addPurchaseToDatabase(int purchase_id, int user_id, int address_id, Date date, double total, boolean isFilled) {
    Connection connection = null;
    PreparedStatement statement = null;

    try {
        connection = getConnection(); // Assuming you have a method to get the connection

        String sql = "INSERT INTO Purchase (purchase_id, user_id, address_id, date, total, isFilled) VALUES (?, ?, ?, ?, ?, ?)";
        statement = connection.prepareStatement(sql);

        statement.setInt(1, purchase_id);
        statement.setInt(2, user_id);
        statement.setInt(3, address_id);
        statement.setDate(4, new java.sql.Date(date.getTime())); // Convert java.util.Date to java.sql.Date
        statement.setDouble(5, total);
        statement.setBoolean(6, isFilled);

        statement.executeUpdate(); // Execute the insert statement

        System.out.println("Purchase added to database successfully");
    } catch (SQLException ex) {
        ex.printStackTrace();
    } 
}





        // Method to generate a Unique Address_ID
    private int generateUniqueOrderID(Connection connection) throws SQLException {
        int uniqueAddressID = 0;
        PreparedStatement statement = connection.prepareStatement("SELECT MAX(purchase_id) FROM Purchase_Item");
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            uniqueAddressID = resultSet.getInt(1);
        }

        // Just add 1 to the max if it exists, else ID = 1
        return uniqueAddressID + 1;
    }
    



}
