package dao;

import model.Item;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;

public class ItemDAOImpl implements ItemDAO {

    private ServletContext context;

    public ItemDAOImpl(ServletContext context) {
        this.context = context;
    }

    private Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("MySQL Driver not found", e);
        }
        String url = "jdbc:mysql://ecomproj-ecomfin.a.aivencloud.com:18620/defaultdb";
        String user = "avnadmin";
        String password = "AVNS_4En2QrrZ4DU5tpub8TB";
        return DriverManager.getConnection(url, user, password);
    }

    public List<Item> getAllItems() {
        List<Item> items = new ArrayList<>();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM Item")) {
            while (resultSet.next()) {
                Item item = new Item();
                item.setName(resultSet.getString("name"));
                item.setPrice(resultSet.getDouble("price"));
                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }
    
    
    
}
