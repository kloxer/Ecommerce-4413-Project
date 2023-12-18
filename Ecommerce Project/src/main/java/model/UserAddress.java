package model;

import java.io.Serializable;

public class UserAddress implements Serializable{

	private static final long serialVersionUID = 1L;
	private int userAddressID;
	private int addressID;
    
    public UserAddress() {
    	
    }

	public int getAddressID() {
		return addressID;
	}
	
	public void setAddressID(int addressID) {
		this.addressID = addressID;
	}

	public int getUserAddressID() {
		return userAddressID;
	}

	public void setUserAddressID(int userAddressID) {
		this.userAddressID = userAddressID;
	}
	
}
