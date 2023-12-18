package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.sql.DataSource;

import model.Item;

public class ItemDAOImpl implements ItemDAO {

    private DataSource dSource;

    public ItemDAOImpl() throws NamingException{
    	Context ctx = new InitialContext();
    	dSource = (DataSource) ctx.lookup("java:comp/env/jdbc/ecomDB");
    }

    private Connection getConnection() throws SQLException {
    	return dSource.getConnection();
    }

    private void closeConnection(Connection connection) {
        if (connection == null)
            return;
        try {
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<Item> findAllItems() {
        List<Item> result = new ArrayList<>();

        String sql = "SELECT * FROM Item";

        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Item item = new Item();
                item.setItemID(resultSet.getString("itemID"));
                item.setName(resultSet.getString("name"));
                item.setDescription(resultSet.getString("description"));
                item.setCategory(resultSet.getString("category"));
                item.setBrand(resultSet.getString("brand"));
                item.setQuantity(resultSet.getInt("quantity"));
                item.setPrice(resultSet.getInt("price"));

                result.add(item);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return result;
    }

    @Override
    public Item findItemByID(String itemID) {
        Item item = null;

        String sql = "SELECT * FROM Item WHERE itemID = " + itemID;

        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                System.out.println("SUCESS FOR ITEM FOUND");
                item = new Item();
                item.setItemID(resultSet.getString("itemID"));
                item.setName(resultSet.getString("name"));
                item.setDescription(resultSet.getString("description"));
                item.setCategory(resultSet.getString("category"));
                item.setBrand(resultSet.getString("brand"));
                item.setQuantity(resultSet.getInt("quantity"));
                item.setPrice(resultSet.getInt("price"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return item;
    }

    @Override
    public List<Item> searchItemsByKeyword(String keyWord) {
        List<Item> result = new ArrayList<>();

        String sql = "SELECT * FROM Item WHERE name LIKE ? OR description LIKE ? OR brand LIKE ?";

        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            String keywordParam = "%" + keyWord + "%";
            statement.setString(1, keywordParam);
            statement.setString(2, keywordParam);
            statement.setString(3, keywordParam);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Item item = new Item();
                item.setItemID(resultSet.getString("itemID"));
                item.setName(resultSet.getString("name"));
                item.setDescription(resultSet.getString("description"));
                item.setCategory(resultSet.getString("category"));
                item.setBrand(resultSet.getString("brand"));
                item.setQuantity(resultSet.getInt("quantity"));
                item.setPrice(resultSet.getInt("price"));

                result.add(item);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return result;
    }

    @Override
    public List<Item> findItemsByCategory(String category) {
        List<Item> result = new ArrayList<>();

        String sql = "SELECT * FROM Item WHERE category = ?";

        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, category);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Item item = new Item();
                item.setItemID(resultSet.getString("itemID"));
                item.setName(resultSet.getString("name"));
                item.setDescription(resultSet.getString("description"));
                item.setCategory(resultSet.getString("category"));
                item.setBrand(resultSet.getString("brand"));
                item.setQuantity(resultSet.getInt("quantity"));
                item.setPrice(resultSet.getInt("price"));

                result.add(item);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return result;
    }

    public List<Item> findItemsByBrand(String brand) {
        List<Item> result = new ArrayList<>();

        String sql = "SELECT * FROM Item WHERE brand = ?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, brand);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Item item = new Item();
                item.setItemID(resultSet.getString("itemID"));
                item.setName(resultSet.getString("name"));
                item.setDescription(resultSet.getString("description"));
                item.setCategory(resultSet.getString("category"));
                item.setBrand(resultSet.getString("brand"));
                item.setQuantity(resultSet.getInt("quantity"));
                item.setPrice(resultSet.getInt("price"));

                result.add(item);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
    }
    
    @Override
    public void insert(Item item) {
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO Item (itemID, name, description, category, brand, quantity, price) VALUES (?, ?, ?, ?, ?, ?, ?)");
            statement.setString(1,  String.valueOf(item.getItemID()));
            statement.setString(2, item.getName());
            statement.setString(3, item.getDescription());
            statement.setString(4,  String.valueOf(item.getCategory()));
            statement.setString(5, item.getBrand());
            statement.setInt(6, (int) item.getQuantity());
            statement.setInt(7, (int) item.getPrice());

            statement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }

    @Override
    public void delete(String itemID) {
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement("DELETE FROM Item WHERE itemID=?");
            statement.setString(1, itemID);
            statement.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection);
        }
    }
}
