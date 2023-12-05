package dao;

import model.User;

public interface UserDAO {

	boolean addUser(User user);

	User getUser(int userId);
	
	User getUserByUsernamePassword(String username, String password);
	
}
