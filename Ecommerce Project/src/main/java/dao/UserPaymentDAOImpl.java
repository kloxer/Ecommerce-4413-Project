package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import model.UserPaymentMethod;


public class UserPaymentDAOImpl implements UserPaymentDAO {
	
	
    private DataSource dSource;

    public UserPaymentDAOImpl() throws NamingException{
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
    
    // Method to generate a UniqueUPM_ID for a new user
    private int generateUniqueUPM_ID(Connection connection) throws SQLException {
        int uniqueUPM_ID = 0;
        PreparedStatement statement = connection.prepareStatement("SELECT MAX(UPM_ID) FROM User_Payment_Method");
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            uniqueUPM_ID = resultSet.getInt(1);
        }

        //Just add 1 to max if exists else id = 1
        return uniqueUPM_ID + 1;
    }
    
    //Method to create default user payment method when account created
    public boolean addDefUserPaymentMethod(int userId) {
        Connection connection = null;
        try {
            connection = getConnection();

            //Insert default/empty CC values for the User
            PreparedStatement paymentStatement = connection.prepareStatement(
                    "INSERT INTO User_Payment_Method (UPM_ID, User_id, CVV, CardProvider, Card_Number, exp_year, exp_month) VALUES (?, ?, ?, ?, ?, ?, ?)");
            paymentStatement.setInt(1, generateUniqueUPM_ID(connection));
            paymentStatement.setInt(2, userId);
            paymentStatement.setNull(3, Types.INTEGER);
            paymentStatement.setString(4, "Visa");
            paymentStatement.setString(5, "");
            paymentStatement.setNull(6, Types.INTEGER); 
            paymentStatement.setNull(7, Types.INTEGER);

            paymentStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            closeConnection(connection);
        }
        return true;
    }
    
    //Method to add a user payment method
    public boolean addUserPaymentMethod(UserPaymentMethod paymentMethod) {
        Connection connection = null;
        try {
            connection = getConnection();

            // Insert details to DB from paymentMethod
            PreparedStatement paymentStatement = connection.prepareStatement(
                    "INSERT INTO User_Payment_Method (UPM_ID, User_id, CVV, CardProvider, Card_Number, exp_year, exp_month) VALUES (?, ?, ?, ?, ?, ?, ?)");
            paymentStatement.setInt(1, paymentMethod.getUPM_ID());
            paymentStatement.setInt(2, paymentMethod.getUser_id());
            paymentStatement.setInt(3, paymentMethod.getCVV());
            paymentStatement.setString(4, paymentMethod.getCardProvider());
            paymentStatement.setString(5, paymentMethod.getCardNumber());
            paymentStatement.setInt(6, paymentMethod.getExp_year());
            paymentStatement.setInt(7, paymentMethod.getExp_month());

            int rowsInserted = paymentStatement.executeUpdate();

            // Check if successful
            if (rowsInserted == 0) {
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            closeConnection(connection);
        }
        //Insertion was successful
        return true;
    }
    
    //Method to update a user payment method
    public boolean updateUserPaymentMethod(UserPaymentMethod updatedPaymentMethod) {
        Connection connection = null;
        try {
            connection = getConnection();

            PreparedStatement statement = connection.prepareStatement(
                "UPDATE User_Payment_Method SET CVV=?, CardProvider=?, Card_Number=?, exp_year=?, exp_month=? WHERE UPM_ID=? AND User_id=?");

            statement.setInt(1, updatedPaymentMethod.getCVV());
            statement.setString(2, updatedPaymentMethod.getCardProvider());
            statement.setString(3, updatedPaymentMethod.getCardNumber());
            statement.setInt(4, updatedPaymentMethod.getExp_year());
            statement.setInt(5, updatedPaymentMethod.getExp_month());
            statement.setInt(6, updatedPaymentMethod.getUPM_ID());
            statement.setInt(7, updatedPaymentMethod.getUser_id());

            int rowsChanged = statement.executeUpdate();
            
            //Check if successful update
            if (rowsChanged == 0) {
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            closeConnection(connection);
        }
        return true;
    }
    
    
    //Method to get the latest Payment method provided given userId
    public UserPaymentMethod getLatestUserPaymentMethod(int userId) {
        Connection connection = null;
        UserPaymentMethod userPaymentMethod = new UserPaymentMethod();

        try {
            connection = getConnection();

            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM User_Payment_Method WHERE User_id = ? ORDER BY UPM_ID DESC LIMIT 1");
            statement.setInt(1, userId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Make the UserPaymentMethod model
                userPaymentMethod.setUPM_ID(resultSet.getInt("UPM_ID"));
                userPaymentMethod.setUser_id(resultSet.getInt("User_id"));
                userPaymentMethod.setCVV(resultSet.getInt("CVV"));
                userPaymentMethod.setCardProvider(resultSet.getString("CardProvider"));
                userPaymentMethod.setCardNumber(resultSet.getString("Card_Number"));
                userPaymentMethod.setExp_year(resultSet.getInt("exp_year"));
                userPaymentMethod.setExp_month(resultSet.getInt("exp_month"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection);
        }

        //Send model back
        return userPaymentMethod;
    }
    
    //Method to retrieve all payment methods given ID of user
    public List<UserPaymentMethod> getAllUserPaymentMethods(int userId) {
        List<UserPaymentMethod> userPaymentMethods = new ArrayList<>();
        Connection connection = null;

        try {
            connection = getConnection();

            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM User_Payment_Method WHERE User_id = ?");
            statement.setInt(1, userId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                UserPaymentMethod userPaymentMethod = new UserPaymentMethod();
                // Populate the model for current payment method
                userPaymentMethod.setUPM_ID(resultSet.getInt("UPM_ID"));
                userPaymentMethod.setUser_id(resultSet.getInt("User_id"));
                userPaymentMethod.setCVV(resultSet.getInt("CVV"));
                userPaymentMethod.setCardProvider(resultSet.getString("CardProvider"));
                userPaymentMethod.setCardNumber(resultSet.getString("Card_Number"));
                userPaymentMethod.setExp_year(resultSet.getInt("exp_year"));
                userPaymentMethod.setExp_month(resultSet.getInt("exp_month"));

                // Add the current model to list
                userPaymentMethods.add(userPaymentMethod);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        
        //Return the list
        return userPaymentMethods;
    }




}
