package dao;

import model.User;

public interface UserDao {
	public User selectUserById(Integer userId);

	public int createUser(User user);

	public void updateUser(User user);
	
	public void updateUserPaword(User user);

	public User checkUserByPhone(User user);

	public User checkUserByEmail(User user);  
}
