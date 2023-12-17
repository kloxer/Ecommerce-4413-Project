package dao;

import model.Cart;

public interface addOrderToDatabaseDAOImpl {
    
    public void addOrderToDatabase(Cart cart);
    //DB needs to fill 2 tables, Purchase and Purchase_Item;
    

}
