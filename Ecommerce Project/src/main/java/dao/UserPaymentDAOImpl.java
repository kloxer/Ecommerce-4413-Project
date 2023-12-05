package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    
    public boolean addDefUserPaymentMethod(int userId) {
        Connection connection = null;
        try {
            connection = getConnection();

            PreparedStatement paymentStatement = connection.prepareStatement(
                    "INSERT INTO User_Payment_Method (UPM_ID, User_id, Payment_type_id, CardProvider, Account_number, Expiry_date) VALUES (?, ?, ?, ?, ?, ?)");
            paymentStatement.setInt(1, generateUniqueUPM_ID(connection)); 
            paymentStatement.setInt(2, userId);
            paymentStatement.setInt(3, 0);
            paymentStatement.setString(4, "Visa");
            paymentStatement.setInt(5, 0);
            paymentStatement.setDate(6, new Date(System.currentTimeMillis()));

            paymentStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            closeConnection(connection);
        }
        return true;
    }
    
    
    public UserPaymentMethod getCurrentUserPaymentMethod(int userId) {
        Connection connection = null;
        UserPaymentMethod userPaymentMethod = new UserPaymentMethod();

        try {
            connection = getConnection();

            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM User_Payment_Method WHERE User_id = ? LIMIT 1");
            statement.setInt(1, userId);

            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                userPaymentMethod.setUpmID(resultSet.getInt("UPM_ID"));
                userPaymentMethod.setUserID(resultSet.getInt("User_id"));
                userPaymentMethod.setPaymentTypeID(String.valueOf(resultSet.getInt("Payment_type_id")));
                userPaymentMethod.setCardProvider(resultSet.getString("CardProvider"));
                userPaymentMethod.setAccountNumber(resultSet.getInt("Account_number"));
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                userPaymentMethod.setExpiryDate(dateFormat.format(resultSet.getDate("Expiry_date")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection);
        }

        return userPaymentMethod;
    }

}
