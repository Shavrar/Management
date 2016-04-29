package dao;

import java.util.Collection;

import Entities.User;
import exception.DataException;

public interface UserDao extends Dao<User> {
	String checkUser(User user) throws DataException;
	Collection<User> readAllUsers() throws DataException;	
}
