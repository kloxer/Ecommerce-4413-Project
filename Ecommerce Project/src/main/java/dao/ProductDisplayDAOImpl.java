package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import model.ProductDisplay;

public class ProductDisplayDAOImpl implements ProductDisplayDAO {

    private DataSource dSource;

    public ProductDisplayDAOImpl() throws NamingException {
        Context ctx = new InitialContext();
        dSource = (DataSource) ctx.lookup("java:comp/env/jdbc/ecomDB");
    }

    @Override
    public void addProduct(ProductDisplay product) throws SQLException {
        String insertProductSQL = "INSERT INTO Products (ProductID, P_name, P_description) VALUES (?, ?, ?)";
        String insertProductDetailsSQL = "INSERT INTO Product_Details (ID, Prod_id, Quantity_remaining, Price, SKU) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement productStatement = connection.prepareStatement(insertProductSQL);
             PreparedStatement productDetailsStatement = connection.prepareStatement(insertProductDetailsSQL)) {

            // Insert into Products table
            productStatement.setInt(1, product.getProdID());
            productStatement.setString(2, product.getpName());
            productStatement.setString(3, product.getpDescription());
            productStatement.executeUpdate();

            // Insert into Product_Details table
            productDetailsStatement.setInt(1, product.getId());
            productDetailsStatement.setInt(2, product.getProdID());
            productDetailsStatement.setInt(3, product.getQuantityRemaining());
            productDetailsStatement.setDouble(4, product.getPrice());
            productDetailsStatement.setInt(5, product.getSku());
            productDetailsStatement.executeUpdate();
        }
    }

    @Override
    public ProductDisplay getProductById(int id) throws SQLException {
        String sql = "SELECT * FROM Products, Product_Details WHERE Products.ProductID = Product_Details.Prod_id AND Products.ProductID = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int prodID = resultSet.getInt("Products.ProductID");
                    String pName = resultSet.getString("Products.P_name");
                    String pDescription = resultSet.getString("Products.P_description");
                    int productId = resultSet.getInt("Product_Details.ID");
                    int quantityRemaining = resultSet.getInt("Product_Details.Quantity_remaining");
                    double price = resultSet.getDouble("Product_Details.Price");
                    int sku = resultSet.getInt("Product_Details.SKU");
                    return new ProductDisplay(prodID, pName, pDescription, productId, quantityRemaining, price, sku);
                }
            }
        }
        return null; // Product not found
    }

    @Override
    public List<ProductDisplay> getAllProducts() throws SQLException {
        List<ProductDisplay> productDisplays = new ArrayList<>();
        String sql = "SELECT * FROM Products, Product_Details WHERE Products.ProductID = Product_Details.Prod_id";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int prodID = resultSet.getInt("Products.ProductID");
                String pName = resultSet.getString("Products.P_name");
                String pDescription = resultSet.getString("Products.P_description");
                int productId = resultSet.getInt("Product_Details.ID");
                int quantityRemaining = resultSet.getInt("Product_Details.Quantity_remaining");
                double price = resultSet.getDouble("Product_Details.Price");
                int sku = resultSet.getInt("Product_Details.SKU");
                ProductDisplay productDisplay = new ProductDisplay(prodID, pName, pDescription, productId, quantityRemaining, price, sku);
                productDisplays.add(productDisplay);
            }
        }
        System.out.println("success");
        return productDisplays;
    }

    @Override
    public void updateProduct(ProductDisplay product) throws SQLException {
        String updateProductSQL = "UPDATE Products SET P_name = ?, P_description = ? WHERE ProductID = ?";
        String updateProductDetailsSQL = "UPDATE Product_Details SET Quantity_remaining = ?, Price = ?, SKU = ? WHERE ID = ?";

        try (Connection connection = getConnection();
             PreparedStatement productStatement = connection.prepareStatement(updateProductSQL);
             PreparedStatement productDetailsStatement = connection.prepareStatement(updateProductDetailsSQL)) {

            // Update Products table
            productStatement.setString(1, product.getpName());
            productStatement.setString(2, product.getpDescription());
            productStatement.setInt(3, product.getProdID());
            productStatement.executeUpdate();

            // Update Product_Details table
            productDetailsStatement.setInt(1, product.getQuantityRemaining());
            productDetailsStatement.setDouble(2, product.getPrice());
            productDetailsStatement.setInt(3, product.getSku());
            productDetailsStatement.setInt(4, product.getId());
            productDetailsStatement.executeUpdate();
        }
    }

    @Override
    public void deleteProduct(int id) throws SQLException {
        String deleteProductSQL = "DELETE FROM Products WHERE ProductID = ?";
        String deleteProductDetailsSQL = "DELETE FROM Product_Details WHERE ID = ?";

        try (Connection connection = getConnection();
             PreparedStatement productStatement = connection.prepareStatement(deleteProductSQL);
             PreparedStatement productDetailsStatement = connection.prepareStatement(deleteProductDetailsSQL)) {

            // Delete from Products table
            productStatement.setInt(1, id);
            productStatement.executeUpdate();

            // Delete from Product_Details table
            productDetailsStatement.setInt(1, id); // Assuming ID corresponds to ProductID
            productDetailsStatement.executeUpdate();
        }
    }

    private Connection getConnection() throws SQLException {
        return dSource.getConnection();
    }
}
