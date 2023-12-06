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

            //Insert default/empty CC values for the User
            PreparedStatement paymentStatement = connection.prepareStatement(
                    "INSERT INTO User_Payment_Method (UPM_ID, User_id, CVV, CardProvider, Card_Number, exp_year, exp_month) VALUES (?, ?, ?, ?, ?, ?, ?)");
            paymentStatement.setInt(1, generateUniqueUPM_ID(connection));
            paymentStatement.setInt(2, userId);
            paymentStatement.setInt(3, 0);
            paymentStatement.setString(4, "Visa");
            paymentStatement.setInt(5, 0);
            paymentStatement.setInt(6, 0); 
            paymentStatement.setInt(7, 0);

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
            	//Make the model
                userPaymentMethod.setUPM_ID(resultSet.getInt("UPM_ID"));
                userPaymentMethod.setUser_id(resultSet.getInt("User_id"));
                userPaymentMethod.setCVV(resultSet.getInt("CVV"));
                userPaymentMethod.setCardProvider(resultSet.getString("CardProvider"));
                userPaymentMethod.setCardNumber(resultSet.getInt("Card_Number"));
                userPaymentMethod.setExp_year(resultSet.getInt("exp_year"));
                userPaymentMethod.setExp_month(resultSet.getInt("exp_month"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection);
        }

        return userPaymentMethod;
    }

}
