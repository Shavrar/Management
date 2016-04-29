package service;

import java.util.Collection;

import Entities.User;
import exception.DataException;

public interface UserService extends Service {
	Collection<User> getAllUsers() throws DataException;
	User getUserById (int identity) throws DataException;
	void AddUser (User user) throws DataException;
	void UpdateUser (User user) throws DataException;
	void DeleteUser (int identity) throws DataException;
	String CheckUser(User user)throws DataException;
}
