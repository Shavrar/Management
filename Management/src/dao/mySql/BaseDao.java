package dao.mySql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

import Entities.Entity;
import Entities.Project;

public abstract class BaseDao {
	
	static HashMap<Integer, Entity> map;
	
	protected Connection connection;

	public BaseDao(Connection connection) {
		this.connection = connection;
	}

	final protected void close(ResultSet set, Statement statement) {
		try {
			set.close();
		} catch(Exception e) {}
		try {
			statement.close();
		} catch(Exception e) {}
	}
}
