package dao;

import java.util.List;

import model.UserPaymentMethod;

public interface UserPaymentDAO {

	boolean addDefUserPaymentMethod(int userId);
	
	boolean addUserPaymentMethod(UserPaymentMethod paymentMethod);
	
	boolean updateUserPaymentMethod(UserPaymentMethod updatedPaymentMethod);
	
	UserPaymentMethod getLatestUserPaymentMethod(int userId);
	
	List<UserPaymentMethod> getAllUserPaymentMethods(int userId);

}
