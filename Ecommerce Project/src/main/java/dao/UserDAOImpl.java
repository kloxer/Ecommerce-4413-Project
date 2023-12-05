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

import model.User;


public class UserDAOImpl implements UserDAO{
	
    private DataSource dSource;

    public UserDAOImpl() throws NamingException{
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
    
    //check if user exists
    private boolean userExists(String username) {
        String sql = "SELECT * FROM User WHERE Username = ?";
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            //True if user Exists
            return resultSet.next(); 
        } catch (SQLException ex) {
            ex.printStackTrace();
            // False if Not
            return false; 
        } finally {
            closeConnection(connection);
        }
    }
    
    public boolean addUser(User user) {
        if (userExists(user.getUsername())) {
            //Indicate user already Exists
            return false;
        }

        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO User (Username, Phone_number, Password, First_name, Last_name, isAdmin) VALUES (?, ?, ?, ?, ?, ?)");
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPhoneNumber());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getFirstName());
            statement.setString(5, user.getLastName());
            statement.setBoolean(6, user.isAdmin());

            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        //User has been added
        return true;
    }
    
    
    public User getUser(int userId) {
        User user = null;
        //Find user where ID matches
        String sql = "SELECT * FROM User WHERE User_ID = ?";
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user = new User();
                user.setUserId(resultSet.getInt("User_ID"));
                user.setUsername(resultSet.getString("Username"));
                user.setPhoneNumber(resultSet.getString("Phone_number"));
                user.setPassword(resultSet.getString("Password"));
                user.setFirstName(resultSet.getString("First_name"));
                user.setLastName(resultSet.getString("Last_name"));
                user.setAdmin(resultSet.getBoolean("isAdmin"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return user;
    }
    
    public User getUserByUsernamePassword(String username, String password) {
        User user = null;
        //Find user where Username and Password match
        String sql = "SELECT * FROM User WHERE Username = ? AND Password = ?";
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user = new User();
                user.setUserId(resultSet.getInt("User_ID"));
                user.setUsername(resultSet.getString("Username"));
                user.setPhoneNumber(resultSet.getString("Phone_number"));
                user.setPassword(resultSet.getString("Password"));
                user.setFirstName(resultSet.getString("First_name"));
                user.setLastName(resultSet.getString("Last_name"));
                user.setAdmin(resultSet.getBoolean("isAdmin"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        //Return the User
        return user;
    }

}
