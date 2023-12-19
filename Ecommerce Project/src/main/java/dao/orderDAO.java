package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.Cart;
import model.Item;
import model.Order;
import model.ProductDisplay;
import model.Purchase;

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

        //So I can do purchase_item first, 

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

//Method only called upon by addOrderToDatabase
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
    

//Get orders from user id
public List<Order> getOrdersByUserId(int user_id) {
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;
    List<Order> orders = new ArrayList<>();

    try {
        connection = getConnection();

        String sql = "SELECT * FROM Purchase WHERE user_id = ?";
        statement = connection.prepareStatement(sql);

        statement.setInt(1, user_id);

        resultSet = statement.executeQuery();

        ArrayList<Integer> purchase_ids = new ArrayList<>();
        while (resultSet.next()) {
            int purchase_id = resultSet.getInt("purchase_id");
            
            int address_id = resultSet.getInt("address_id");
            Date date = resultSet.getDate("date");
            double total = resultSet.getDouble("total");
            boolean isFilled = resultSet.getBoolean("isFilled");

            Order purchase = new Order(purchase_id, user_id, address_id, date, total, isFilled);
            orders.add(purchase);
            purchase_ids.add(purchase_id);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        // Close the resources
        if (resultSet != null) try { resultSet.close(); } catch (SQLException e) { /* ignored */ }
        if (statement != null) try { statement.close(); } catch (SQLException e) { /* ignored */ }
        if (connection != null) try { connection.close(); } catch (SQLException e) { /* ignored */ }
    }


    // Get the items for each purchase



    return orders;
}


    public List<Purchase> getAllPurchasesByUserId(int userId) {
         String GET_PURCHASES_QUERY = "SELECT * FROM Purchase WHERE user_id = ?";

        List<Purchase> purchases = new ArrayList<>();
        try (Connection conn = dSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(GET_PURCHASES_QUERY)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Purchase purchase = new Purchase();
                purchase.setPurchaseId(rs.getInt("purchase_id"));
                purchase.setUserId(rs.getInt("user_id"));
                purchase.setAddressId(rs.getInt("address_id"));
                purchase.setDate(rs.getDate("date"));
                purchase.setTotal(rs.getDouble("total"));
                purchase.setFilled(rs.getBoolean("isFilled"));
                purchase.setItems(getItemsForPurchase(rs.getInt("purchase_id"), conn));

                purchases.add(purchase);

                //System.out.println("Purchase ID: " + purchase.getPurchaseId() + " User ID: " + purchase.getUserId() + " Address ID: " + purchase.getAddressId() + " Date: " + purchase.getDate() + " Total: " + purchase.getTotal() + " isFilled: " + purchase.isFilled()  + " Items: " + purchase.getItems());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions
        }
        return purchases;
    }

    private List<Item> getItemsForPurchase(int purchaseId, Connection conn) {
        String GET_ITEMS_QUERY = "SELECT * FROM Purchase_Item WHERE purchase_id = ?";

        List<Item> items = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(GET_ITEMS_QUERY)) {
            ps.setInt(1, purchaseId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Item item = new Item();
                item.setPurchaseId(rs.getInt("purchase_id"));
                item.setItemID(rs.getString("item_id"));
                item.setQuantity(rs.getInt("quantity"));
                item.setPrice(rs.getDouble("price_at_purchase"));

                items.add(item);
                //System.out.println("Item ID: " + item.getItemID() + " Quantity: " + item.getQuantity() + " Price: " + item.getPrice());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle exceptions
        }
        return items;
    }

    





}
