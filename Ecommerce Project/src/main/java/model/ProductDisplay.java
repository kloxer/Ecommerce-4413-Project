package model;

import java.io.Serializable;

public class ProductDisplay  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1627448052006526271L;
	//from ProductID Table
	private int prodID;
	private String pName;
	private String pDescription;
	
	
	//From ProductDescrip Table
	private int id;
	private int quantityRemaining;
	private double price;
	private int sku;
	
	
	public ProductDisplay(int prodID, String pName, String pDescription, int id, int quantityRemaining, double price,
			int sku) {
		super();
		this.prodID = prodID;
		this.pName = pName;
		this.pDescription = pDescription;
		this.id = id;
		this.quantityRemaining = quantityRemaining;
		this.price = price;
		this.sku = sku;
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
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getQuantityRemaining() {
		return quantityRemaining;
	}
	public void setQuantityRemaining(int quantityRemaining) {
		this.quantityRemaining = quantityRemaining;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getSku() {
		return sku;
	}
	public void setSku(int sku) {
		this.sku = sku;
	}
	
	
	

}
