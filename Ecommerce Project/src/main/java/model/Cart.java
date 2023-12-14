package model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<ProductDisplay> items;

    public Cart() {
        items = new ArrayList<>();
    }

    public void addItem(ProductDisplay item) {
        items.add(item);
    }

    public void removeItem(ProductDisplay item) {
        items.remove(item);
    }

    public List<ProductDisplay> getItems() {
        return items;
    }

    public double getTotalPrice() {
        double total = 0.0;
        for (ProductDisplay item : items) {
            total += item.getPrice();
        }
        return total;
    }
}