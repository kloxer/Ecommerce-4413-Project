package dao;

import java.sql.SQLException;
import java.util.List;

import model.Address;

public interface AddressDAO {
	
	boolean addDefAddressMethod(int userID) throws SQLException;
	
	boolean addAddress(int userID, Address address);
	
	boolean updateAddress(Address updatedAddress);
	
	Address getLatestAddressMethod(int userID);
	
	List<Address> getUserAddresses(int userID);
	
	boolean removeAddressByID(int userID, int addressID);

}
