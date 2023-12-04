package model;

import java.io.Serializable;

public class User implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private int addressID;
    private String firstName;
	private String lastName;
    private String username;
    private String password;
    private boolean isAdmin;
    
    public User() {
    	
    }
    
    public int getId() {
		return id;
	}
	
    public void setId(int id) {
		this.id = id;
	}
	
	public int getAddressID() {
		return addressID;
	}
	
	public void setAddressID(int addressID) {
		this.addressID = addressID;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean getisAdmin() {
		return isAdmin;
	}
	
	public void setisAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	
}
