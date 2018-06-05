package service;

import beans.User;
import dao.UserDao;

public class UserService {
	private UserDao userDao=new UserDao();
	
	public User login(User user) throws Exception {
		User us=userDao.getUserByName(user.getUseraccount(), user.getUserpassword());
		if (us==null) {
			throw new Exception("用户不存在");
		}
		if (!us.getUserpassword().equals(user.getUserpassword())) {
			throw new Exception("密码错误");
		}
		return us;
	}
	
}
