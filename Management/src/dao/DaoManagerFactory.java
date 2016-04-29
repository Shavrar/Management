package dao;

import dao.mySql.DaoManagerImpl;
import exception.DataException;


public class DaoManagerFactory {
	public static DaoManager getInstance() throws DataException{
		return new DaoManagerImpl();
	}
}