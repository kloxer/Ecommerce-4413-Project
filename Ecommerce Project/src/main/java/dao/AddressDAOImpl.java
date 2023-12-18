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

import model.Address;
import model.UserAddress;

public class AddressDAOImpl implements AddressDAO {

    private DataSource dSource;

    public AddressDAOImpl() throws NamingException {
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

    // Method to generate a Unique Address_ID
    private int generateUniqueAddressID(Connection connection) throws SQLException {
        int uniqueAddressID = 0;
        PreparedStatement statement = connection.prepareStatement("SELECT MAX(id) FROM Address");
        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            uniqueAddressID = resultSet.getInt(1);
        }

        // Just add 1 to the max if it exists, else ID = 1
        return uniqueAddressID + 1;
    }
    
    // Method to add the relationship between User and Address in UserAddress table
    private void addUserAddress(Connection connection, int userAddressID, int addressID) throws SQLException {
        PreparedStatement userAddressStatement = connection.prepareStatement(
                "INSERT INTO UserAddress (UserAd_ID, Address_ID) VALUES (?, ?)");
        userAddressStatement.setInt(1, userAddressID);
        userAddressStatement.setInt(2, addressID);

        userAddressStatement.executeUpdate();
    }
    
    // Method to create default address with empty fields
    public boolean addDefAddressMethod(int userID) {
        Connection connection = null;
        try {
            connection = getConnection();

            // Insert empty fields for entry
            PreparedStatement addressStatement = connection.prepareStatement(
                    "INSERT INTO Address (id, Unit_number, address_line1, city, region, postal_code, country) VALUES (?, ?, ?, ?, ?, ?, ?)");

            int addressID = generateUniqueAddressID(connection);

            addressStatement.setInt(1, addressID);
            addressStatement.setString(2, "");
            addressStatement.setString(3, "");
            addressStatement.setString(4, "");
            addressStatement.setString(5, "");
            addressStatement.setString(6, "");
            addressStatement.setString(7, "");

            addressStatement.executeUpdate();
            
            // Add the address to UserAddress table
            addUserAddress(connection, userID, addressID);
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            closeConnection(connection);
        }
      
        
        return true;
    }

    // Method to add an address to the Address table
    public boolean addAddress(int userID, Address address) {
        Connection connection = null;
        try {
            connection = getConnection();

            int addressID = generateUniqueAddressID(connection);

            PreparedStatement addressStatement = connection.prepareStatement(
                    "INSERT INTO Address (id, Unit_number, address_line1, city, region, postal_code, country) VALUES (?, ?, ?, ?, ?, ?, ?)");
            addressStatement.setInt(1, addressID);
            addressStatement.setString(2, address.getUnitNumber());
            addressStatement.setString(3, address.getAddressLine1());
            addressStatement.setString(4, address.getCity());
            addressStatement.setString(5, address.getRegion());
            addressStatement.setString(6, address.getPostalCode());
            addressStatement.setString(7, address.getCountry());

            addressStatement.executeUpdate();

            // Add the address to UserAddress table
            addUserAddress(connection, userID, addressID);
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            closeConnection(connection);
        }
        return true;
    }

    
    // Method to update an address in Address table
    public boolean updateAddress(Address updatedAddress) {
        Connection connection = null;
        try {
            connection = getConnection();

            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE Address SET Unit_number=?, address_line1=?, city=?, region=?, postal_code=?, country=? WHERE id=?");

            statement.setString(1, updatedAddress.getUnitNumber());
            statement.setString(2, updatedAddress.getAddressLine1());
            statement.setString(3, updatedAddress.getCity());
            statement.setString(4, updatedAddress.getRegion());
            statement.setString(5, updatedAddress.getPostalCode());
            statement.setString(6, updatedAddress.getCountry());
            statement.setInt(7, updatedAddress.getId());

            int rowsChanged = statement.executeUpdate();

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
    
    // Method to get the latest address based on highest Address_ID
    public Address getLatestAddressMethod(int userID) {
        Connection connection = null;
        Address latestAddress = new Address();

        try {
            connection = getConnection();

            PreparedStatement statement = connection.prepareStatement(
                    "SELECT a.* FROM Address a JOIN UserAddress ua ON a.id = ua.Address_ID WHERE ua.UserAd_ID = ? ORDER BY a.id DESC LIMIT 1");
            statement.setInt(1, userID);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                latestAddress.setId(resultSet.getInt("id"));
                latestAddress.setUnitNumber(resultSet.getString("Unit_number"));
                latestAddress.setAddressLine1(resultSet.getString("address_line1"));
                latestAddress.setCity(resultSet.getString("city"));
                latestAddress.setRegion(resultSet.getString("region"));
                latestAddress.setPostalCode(resultSet.getString("postal_code"));
                latestAddress.setCountry(resultSet.getString("country"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection);
        }

        return latestAddress;
    }


    // Method to get all addresses of a user by userAddressID or userID
    public List<Address> getUserAddresses(int userID) {
        List<Address> userAddresses = new ArrayList<>();
        Connection connection = null;

        try {
            connection = getConnection();

            PreparedStatement statement = connection.prepareStatement(
                    "SELECT a.* FROM Address a JOIN UserAddress ua ON a.id = ua.Address_ID WHERE ua.UserAd_ID = ? ORDER BY a.id DESC");
            statement.setInt(1, userID);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Address address = new Address();
                address.setId(resultSet.getInt("id"));
                address.setUnitNumber(resultSet.getString("Unit_number"));
                address.setAddressLine1(resultSet.getString("address_line1"));
                address.setCity(resultSet.getString("city"));
                address.setRegion(resultSet.getString("region"));
                address.setPostalCode(resultSet.getString("postal_code"));
                address.setCountry(resultSet.getString("country"));

                userAddresses.add(address);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection);
        }

        return userAddresses;
    }
    
    //Method to remove Address by its ID if more than one exists
    public boolean removeAddressByID(int userID, int addressID) {
        Connection connection = null;
        try {
            connection = getConnection();

            // Check if only address for user
            PreparedStatement countStatement = connection.prepareStatement(
                    "SELECT COUNT(*) AS address_count FROM UserAddress WHERE UserAd_ID = ?");
            countStatement.setInt(1, userID);
            ResultSet countResult = countStatement.executeQuery();

            int addressCount = 0;
            if (countResult.next()) {
                addressCount = countResult.getInt("address_count");
            }
            
            //If only address or there is none, return false (don't delete)
            if (addressCount <= 1) {
                return false;
            } else {
                // Delete address if at least one other exists
                PreparedStatement deleteStatement = connection.prepareStatement(
                        "DELETE FROM Address WHERE id = ? AND id IN (SELECT Address_ID FROM UserAddress WHERE UserAd_ID = ?)");
                deleteStatement.setInt(1, addressID);
                deleteStatement.setInt(2, userID);

                int rowsImpacted = deleteStatement.executeUpdate();
                //Check if successfully executed
                if (rowsImpacted > 0) {
                    return true;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection(connection);
        }

        //Return false if deletion fails
        return false;
    }



}
