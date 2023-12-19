package model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Map;

public class Order implements Serializable  {
    int purchase_id;
    int user_id;
    int address_id;
    Date date;
    double total;
    boolean isFilled;
    
    private Map<ProductDisplay, Integer> items;



    public Order(int purchase_id, int user_id, int address_id, Date date, double total, boolean isFilled) {
        this.purchase_id = purchase_id;
        this.user_id = user_id;
        this.address_id = address_id;
        this.date = date;
        this.total = total;
        this.isFilled = isFilled;
    }

    public int getPurchase_id() {
        return purchase_id;
    }
    
    public void setPurchase_id(int purchase_id) {
        this.purchase_id = purchase_id;
    }
    
    public int getUser_id() {
        return user_id;
    }
    
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    
    public int getAddress_id() {
        return address_id;
    }
    
    public void setAddress_id(int address_id) {
        this.address_id = address_id;
    }
    
    public Date getDate() {
        return date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    
    public double getTotal() {
        return total;
    }
    
    public void setTotal(double total) {
        this.total = total;
    }
    
    public boolean isFilled() {
        return isFilled;
    }
    
    public void setFilled(boolean isFilled) {
        this.isFilled = isFilled;
    }
    
    public Map<ProductDisplay, Integer> getItems() {
        return items;
    }
    
    public void setItems(Map<ProductDisplay, Integer> items) {
        this.items = items;
    }


    
}
