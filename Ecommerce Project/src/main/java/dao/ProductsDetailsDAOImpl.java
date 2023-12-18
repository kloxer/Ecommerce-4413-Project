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
import model.ProductDetails;

public class ProductsDetailsDAOImpl implements ProductsDetailsDAO {

    private DataSource dSource;

    public ProductsDetailsDAOImpl() throws NamingException {
        Context ctx = new InitialContext();
        dSource = (DataSource) ctx.lookup("java:comp/env/jdbc/ecomDB");
    }

    @Override
    public void addProduct(ProductDetails product) throws SQLException {
        String sql = "INSERT INTO product_details (prod_id, quantity_remaining, price, SKU) VALUES (?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, product.getProdID());
            statement.setInt(2, product.getQuantityRemaining());
            statement.setDouble(3, product.getPrice());
            statement.setInt(4, product.getSku());
            statement.executeUpdate();
        }
    }

    @Override
    public ProductDetails getProductById(int id) throws SQLException {
        String sql = "SELECT * FROM product_details WHERE Id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int prodId = resultSet.getInt("prod_id");
                    int quantityRemaining = resultSet.getInt("quantity_remaining");
                    double price = resultSet.getDouble("price");
                    int sku = resultSet.getInt("SKU");
                    return new ProductDetails(id, prodId, quantityRemaining, price, sku);
                }
            }
        }
        return null; // Product not found
    }

    @Override
    public ProductDetails getProductBySKU(int sku) throws SQLException {
        String sql = "SELECT * FROM product_details WHERE SKU = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, sku);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int id = resultSet.getInt("Id");
                    int prodId = resultSet.getInt("prod_id");
                    int quantityRemaining = resultSet.getInt("quantity_remaining");
                    double price = resultSet.getDouble("price");
                    return new ProductDetails(id, prodId, quantityRemaining, price, sku);
                }
            }
        }
        return null; // Product not found
    }

    @Override
    public List<ProductDetails> getAllProducts() throws SQLException {
        List<ProductDetails> products = new ArrayList<>();
        String sql = "SELECT * FROM product_details";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("Id");
                int prodId = resultSet.getInt("prod_id");
                int quantityRemaining = resultSet.getInt("quantity_remaining");
                double price = resultSet.getDouble("price");
                int sku = resultSet.getInt("SKU");
                products.add(new ProductDetails(id, prodId, quantityRemaining, price, sku));
            }
        }
        return products;
    }

    @Override
    public void updateProduct(ProductDetails product) throws SQLException {
        String sql = "UPDATE product_details SET prod_id = ?, quantity_remaining = ?, price = ?, SKU = ? WHERE Id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, product.getProdID());
            statement.setInt(2, product.getQuantityRemaining());
            statement.setDouble(3, product.getPrice());
            statement.setInt(4, product.getSku());
            statement.setInt(5, product.getId());
            statement.executeUpdate();
        }
    }

    @Override
    public void deleteProduct(int id) throws SQLException {
        String sql = "DELETE FROM product_details WHERE Id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    private Connection getConnection() throws SQLException {
        return dSource.getConnection();
    }


}
