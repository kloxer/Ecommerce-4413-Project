package dao;

import java.sql.SQLException;
import java.util.List;
import model.ProductDisplay;

public interface ProductDisplayDAO {

    void addProduct(ProductDisplay product) throws SQLException;

    ProductDisplay getProductById(int id) throws SQLException;

    List<ProductDisplay> getAllProducts() throws SQLException;

    void updateProduct(ProductDisplay product) throws SQLException;

    void deleteProduct(int id) throws SQLException;
}
