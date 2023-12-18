package dao;

import model.Cart;

public interface orderDAOImpl {
    
    public void addOrderToDatabase(Cart cart, int userID, int addressID);
    

}
