package dao.mySql;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


import dao.Dao;
import dao.DaoManager;
import dao.ClientDao;
import dao.ProjectDao;
import dao.UserDao;
import exception.DataException;
import Entities.Entity;


public class DaoManagerImpl extends DaoManager {
	public static final String ERROR_COMMIT = "ошибка завершения транзакции";
	public static final String ERROR_ROLLBACK = "ошибка отмены транзакции";

	private static final Map<Class<? extends Dao<? extends Entity>>, Class<? extends Dao<? extends Entity>>> classes = new ConcurrentHashMap<Class<? extends Dao<? extends Entity>>, Class<? extends Dao<? extends Entity>>>();

	static {
		classes.put(ClientDao.class, ClientDaoImpl.class);
		classes.put(ProjectDao.class, ProjectDaoImpl.class);
		classes.put(UserDao.class, UserDaoImpl.class);
		
	}

	private Connection connection;

	public DaoManagerImpl() throws DataException {
		connection = ConnectionPool.getInstance().getConnection();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <Type extends Dao<? extends Entity>> Type createDao(Class<Type> key) {
		Class<? extends Dao<? extends Entity>> value = classes.get(key);
		if(value != null) {
			try {
				return (Type)value.getConstructor(Connection.class).newInstance(connection);
			} catch(Exception e) {}
		}
		return null;
	}

	@Override
	public void transactionCommit() throws DataException {
		try {
			connection.commit();
		} catch(SQLException e) {
			
			throw new DataException(e);
		}
	}

	@Override
	public void transactionRollback() throws DataException {
		try {
			connection.rollback();
		} catch(SQLException e) {
			
			throw new DataException(e);
		}
	}

	@Override
	public void close() {
		ConnectionPool.getInstance().freeConnection(connection);
	}
}