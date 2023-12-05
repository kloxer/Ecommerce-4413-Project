package dao;

import model.UserPaymentMethod;

public interface UserPaymentDAO {

	boolean addDefUserPaymentMethod(int userId);

	UserPaymentMethod getCurrentUserPaymentMethod(int userId);

}
