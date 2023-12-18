package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

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
	
	@Override
public boolean equals(Object obj) {
    if (this == obj) {
        return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
        return false;
    }
    ProductDisplay that = (ProductDisplay) obj;
    return Objects.equals(prodID, that.prodID);
}

@Override
public int hashCode() {
    return Objects.hash(prodID);
}

//Sorters based on price and name (ascend and descend)
public static void sortByPriceAscending(List<ProductDisplay> products) {
	products.sort(Comparator.comparingDouble(ProductDisplay::getPrice));
}

public static void sortByPriceDescending(List<ProductDisplay> products) {
	products.sort((p1, p2) -> Double.compare(p2.getPrice(), p1.getPrice()));
}

public static void sortByNameAscending(List<ProductDisplay> products) {
	products.sort(Comparator.comparing(ProductDisplay::getpName));
}

public static void sortByNameDescending(List<ProductDisplay> products) {
	products.sort((p1, p2) -> p2.getpName().compareToIgnoreCase(p1.getpName()));
}

//Filters List leaving only products where name contains String
public static List<ProductDisplay> filterByNameContains(List<ProductDisplay> products, String String) {
    List<ProductDisplay> filteredList = new ArrayList<>();

    for (ProductDisplay product : products) {
        if (product.getpName().toLowerCase().contains(String.toLowerCase())) {
            filteredList.add(product);
        }
    }

    return filteredList;
}


}
