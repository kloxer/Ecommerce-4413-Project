package model;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Item implements Serializable {

	private static final long serialVersionUID = 1L;
	private String itemID;
    private String name;
    private String description;
    private String category;
    private String brand;
    private double quantity;
    private double price;

    public Item() {
    }

	public String getItemID() {
		return itemID;
	}

	public void setItemID(String itemID) {
		this.itemID = itemID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
    
    public static void sortByPriceIncreasing(List<Item> items) {
        Collections.sort(items, Comparator.comparingDouble(Item::getPrice));
    }

    public static void sortByPriceDecreasing(List<Item> items) {
        Collections.sort(items, Comparator.comparingDouble(Item::getPrice).reversed());
    }

    public static void sortByName(List<Item> items) {
        Collections.sort(items, Comparator.comparing(Item::getName));
    }
    

}
