package service;

import java.util.Collection;

import Entities.User;
import dao.DaoManager;
import dao.UserDao;
import exception.DataException;

public class UserServiceImpl extends ServiceImpl implements UserService {

	public UserServiceImpl(DaoManager manager) {
		super(manager);		
	}
	
	@Override
	public Collection<User> getAllUsers() throws DataException {
		UserDao userdao = getManager().createDao(UserDao.class);
		return userdao.readAllUsers();
	}

	@Override
	public User getUserById(int identity) throws DataException {
		UserDao userdao = getManager().createDao(UserDao.class);
		return userdao.read(identity);
	}

	@Override
	public void AddUser(User user) throws DataException {
		UserDao userdao = getManager().createDao(UserDao.class);
		userdao.create(user);
	}

	@Override
	public void UpdateUser(User user) throws DataException {
		UserDao userdao = getManager().createDao(UserDao.class);
		userdao.update(user);
	}

	@Override
	public void DeleteUser(int identity) throws DataException {
		UserDao userdao = getManager().createDao(UserDao.class);
		userdao.delete(identity);
	}

	@Override
	public String CheckUser(User user) throws DataException {
		UserDao userdao = getManager().createDao(UserDao.class);
		return userdao.checkUser(user);
	}

}
