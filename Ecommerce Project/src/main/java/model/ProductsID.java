package model;

import java.io.Serializable;

public class ProductsID implements Serializable{

	private static final long serialVersionUID = 1L;
	private int prodID;
	private String pName;
	private String pDescription;
	
	public ProductsID() {
		
	}

	public ProductsID(int prodID2, String pName2, String pDescription2) {
		this.prodID = prodID2;
		this.pName = pName2;
		this.pDescription = pDescription2;
	}

	public int getProdID() {
		return prodID;
	}

	public void setProdID(int prodID) {
		this.prodID = prodID;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getpDescription() {
		return pDescription;
	}

	public void setpDescription(String pDescription) {
		this.pDescription = pDescription;
	}

	

	

	
}