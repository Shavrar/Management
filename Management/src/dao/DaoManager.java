package dao;

import Entities.Entity;
import exception.DataException;

abstract public class DaoManager {
	abstract public <Type extends Dao<? extends Entity>> Type createDao(Class<Type> key);

	abstract public void transactionCommit() throws DataException;

	abstract public void transactionRollback() throws DataException;

	abstract public void close();
}