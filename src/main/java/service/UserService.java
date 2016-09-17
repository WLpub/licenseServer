package service;

import model.User;

public interface UserService {
	User selectUserById(Integer userId);

	int createUser(User user);

	int updateUser(User user);

	int judgeUserByEmail(User user);

	User judgeUserByPhone(User user);

}
