package dao;

import java.sql.SQLException;
import java.util.List;

import model.ProductsID;


public interface ProductsIDDAO {

    void addProduct(ProductsID product) throws SQLException;

    ProductsID getProductById(int productId) throws SQLException;

    List<ProductsID> getAllProducts() throws SQLException;

    void updateProduct(ProductsID product) throws SQLException;

    void deleteProduct(int productId) throws SQLException;
}
