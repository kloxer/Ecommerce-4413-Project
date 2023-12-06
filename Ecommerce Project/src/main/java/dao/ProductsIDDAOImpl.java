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
import model.ProductsID;

public class ProductsIDDAOImpl implements ProductsIDDAO {

    private DataSource dSource;

    public ProductsIDDAOImpl() throws NamingException {
        Context ctx = new InitialContext();
        dSource = (DataSource) ctx.lookup("java:comp/env/jdbc/ecomDB");
    }
 
    private Connection getConnection() throws SQLException {
        return dSource.getConnection();
    }

    @Override
    public void addProduct(ProductsID product) throws SQLException {
        String sql = "INSERT INTO products (productid, productname, productdescription) VALUES (?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, product.getProdID());
            statement.setString(2, product.getpName());
            statement.setString(3, product.getpDescription());
            statement.executeUpdate();
        }
    }

    @Override
    public ProductsID getProductById(int productId) throws SQLException {
        String sql = "SELECT * FROM products WHERE productid = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, productId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int prodID = resultSet.getInt("productid");
                    String pName = resultSet.getString("productname");
                    String pDescription = resultSet.getString("productdescription");
                    return new ProductsID(prodID, pName, pDescription);
                }
            }
        }
        return null; // Product not found
    }

    @Override
    public List<ProductsID> getAllProducts() throws SQLException {
        List<ProductsID> products = new ArrayList<>();
        String sql = "SELECT * FROM products";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int prodID = resultSet.getInt("productid");
                String pName = resultSet.getString("productname");
                String pDescription = resultSet.getString("productdescription");
                products.add(new ProductsID(prodID, pName, pDescription));
            }
        }
        return products;
    }

    @Override
    public void updateProduct(ProductsID product) throws SQLException {
        String sql = "UPDATE products SET productname = ?, productdescription = ? WHERE productid = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, product.getpName());
            statement.setString(2, product.getpDescription());
            statement.setInt(3, product.getProdID());
            statement.executeUpdate();
        }
    }

    @Override
    public void deleteProduct(int productId) throws SQLException {
        String sql = "DELETE FROM products WHERE productid = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, productId);
            statement.executeUpdate();
        }
    }


}
