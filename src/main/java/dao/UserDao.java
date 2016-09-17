package dao;

import model.User;

public interface UserDao {
	public User selectUserById(Integer userId);

	public int createUser(User user);

	public void updateUser(User user);

	public User checkUserByPhone(User user);  
}
