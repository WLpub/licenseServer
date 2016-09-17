package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.UserDao;
import model.User;

@Service
public class UserServiceImpl implements UserService {
	@Autowired  
    private UserDao userDao;  
  
    public User selectUserById(Integer userId) {  
        return userDao.selectUserById(userId);  
          
    }

	public int createUser(User user) {
		if(userDao.checkUserByPhone(user) != null){
			return -1;
		}
    	userDao.createUser(user);
    	return user.getId();
	}

	public int updateUser(User user) {
		userDao.updateUser(user);
		return user.getId();
	}

	@Override
	public int judgeUserByEmail(User user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public User judgeUserByPhone(User user) {
		return userDao.checkUserByPhone(user);
	} 
}
