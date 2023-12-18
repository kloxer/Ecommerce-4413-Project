package model;

import java.io.Serializable;

public class Address implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private String unitNumber;
    private String streetNumber;
	private String addressLine1;
    private String city;
    private String region;
    private String postalCode;
    private String country;
    
    public Address() {
    	
    }
    
    public int getId() {
		return id;
	}
	
    public void setId(int id) {
		this.id = id;
	}

	public String getUnitNumber() {
		return unitNumber;
	}

	public void setUnitNumber(String unitNumber) {
		this.unitNumber = unitNumber;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	
}