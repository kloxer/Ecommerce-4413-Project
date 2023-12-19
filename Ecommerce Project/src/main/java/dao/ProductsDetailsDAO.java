package dao;

import java.sql.SQLException;
import java.util.List;

import model.ProductDetails;


public interface ProductsDetailsDAO {

    // Add a new product to the details
    void addProduct(ProductDetails product) throws SQLException;

    // Get a product by its unique ID
    ProductDetails getProductById(int id) throws SQLException;

    // Get all products in the details
    List<ProductDetails> getAllProducts() throws SQLException;

    // Update product information in the details
    void updateProduct(ProductDetails product) throws SQLException;

    // Delete a product from the details
    void deleteProduct(int id) throws SQLException;

    // Update Product Quantity by ID and new Quantity
    public boolean updateProductQuantity(int prodId, int updatedQuantity) throws SQLException;
    
	ProductDetails getProductBySKU(int sku) throws SQLException;
}