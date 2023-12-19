package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.User;
import dao.*;


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
    
    // Method to generate a unique User_ID for a new user
    private int generateUniqueUserID(Connection connection) throws SQLException {
        int uniqueUserID = 0;

        PreparedStatement statement = connection.prepareStatement("SELECT MAX(User_ID) FROM User");
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            uniqueUserID = resultSet.getInt(1);
        }

        //Just add 1 to max if exists else id = 1
        return uniqueUserID + 1;
    }
    
    //check if username exists
    public boolean userExists(String username) {
        String sql = "SELECT * FROM User WHERE Username = ?";
        Connection connection = null;
        try {
            connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            // True when the username is found
            return resultSet.next(); 
        } catch (SQLException ex) {
            ex.printStackTrace();
            // False if Not
            return false; 
        } finally {
            closeConnection(connection);
        }
    }
    
    //Add User model to database
    public boolean addUser(User user) {
    	//Check is Username already Exists
        if (userExists(user.getUsername())) {
            //Indicate user already Exists
            return false;
        }

        Connection connection = null;
        try {
            connection = getConnection();
            //Generate a unique User_ID for this User
            int userId = generateUniqueUserID(connection);
            
            //Add default payment and address entry for current User ID
            UserPaymentDAO userDAO = new UserPaymentDAOImpl();
            userDAO.addDefUserPaymentMethod(userId);
            
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO User (User_ID, Username, Phone_number, Password, First_name, Last_name, isAdmin) VALUES (?, ?, ?, ?, ?, ?, ?)");
                statement.setInt(1, userId);
                statement.setString(2, user.getUsername());
                statement.setString(3, user.getPhoneNumber());
                statement.setString(4, user.getPassword());
                statement.setString(5, user.getFirstName());
                statement.setString(6, user.getLastName());
                statement.setBoolean(7, user.isAdmin());

                statement.executeUpdate();
                
                //Add default address now that user exists
                AddressDAO addrDAO = new AddressDAOImpl();
                addrDAO.addDefAddressMethod(userId);
                
        } catch (SQLException | NamingException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        
        //User has been added
        return true;
    }
    
    //Retrieve a user by their Username and Password
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
    
    //Get a User by the ID
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
    
    public boolean updateUserDetails(int userId, String firstName, String lastName, String phoneNumber) {
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "UPDATE User SET First_name = ?, Last_name = ?, Phone_number = ? WHERE User_ID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, phoneNumber);
            statement.setInt(4, userId);

            int rowsUpdated = statement.executeUpdate();

         
            if (rowsUpdated > 0) {
            	// Updated successful
                return true; 
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        // Update failed
        return false; 
    }
    
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "SELECT * FROM User";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                User user = new User();
                user.setUserId(resultSet.getInt("User_ID"));
                user.setUsername(resultSet.getString("Username"));
                user.setPhoneNumber(resultSet.getString("Phone_number"));
                user.setPassword(resultSet.getString("Password"));
                user.setFirstName(resultSet.getString("First_name"));
                user.setLastName(resultSet.getString("Last_name"));
                user.setAdmin(resultSet.getBoolean("isAdmin"));

                userList.add(user);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return userList;
    }



}
