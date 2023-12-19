package dao;

import java.util.List;

import model.User;

public interface UserDAO {

	boolean userExists(String username);
	
	boolean addUser(User user);
	
	User getUserByUsernamePassword(String username, String password);

	User getUser(int userId);
	
	boolean updateUserDetails(int userId, String firstName, String lastName, String phoneNumber);

	public List<User> getAllUsers();
}
