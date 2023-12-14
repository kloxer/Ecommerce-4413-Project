package model;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<ProductDisplay, Integer> items;

    public Cart() {
        items = new HashMap<>();
    }

    public void addItem(ProductDisplay item) {
        items.put(item, items.getOrDefault(item, 0) + 1);
    }

    public void removeItem(ProductDisplay item) {
        if (items.containsKey(item)) {
            int count = items.get(item);
            if (count > 1) {
                items.put(item, count - 1);
            } else {
                items.remove(item);
            }
        }
    }

    public Map<ProductDisplay, Integer> getItems() {
        return items;
    }

    public double getTotalPrice() {
        double total = 0.0;
        for (Map.Entry<ProductDisplay, Integer> entry : items.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }
        return total;
    }
}