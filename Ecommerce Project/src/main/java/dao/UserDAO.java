package dao;

import model.User;

public interface UserDAO {

	boolean userExists(String username);
	
	boolean addUser(User user);
	
	User getUserByUsernamePassword(String username, String password);

	User getUser(int userId);
	
}
