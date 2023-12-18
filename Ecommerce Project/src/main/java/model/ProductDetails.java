package model;

import java.io.Serializable;

public class ProductDetails implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private int prodID;
	private int quantityRemaining;
	private double price;
	private int sku;
	
	public ProductDetails() {
		
	}
    
	public ProductDetails(int id, int prodID, int quantityRemaining, double price, int sku) {
	    this.id = id;
	    this.prodID = prodID;
	    this.quantityRemaining = quantityRemaining;
	    this.price = price;
	    this.sku = sku;
	}


	public int getId() {
		return id;
	}
	
    public void setId(int id) {
		this.id = id;
	}

	public int getProdID() {
		return prodID;
	}

	public void setProdID(int prodID) {
		this.prodID = prodID;
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