
package dao;

import Entities.Entity;
import exception.DataException;

public interface Dao<Type extends Entity> {
	Integer create(Type entity) throws DataException;

	Type read(Integer identity) throws DataException;

	void update(Type entity) throws DataException;

	void delete(Integer identity) throws DataException;
}