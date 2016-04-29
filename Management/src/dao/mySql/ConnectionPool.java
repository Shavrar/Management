package dao.mySql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import exception.DataException;


final public class ConnectionPool {
	public static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
	public static final String DB_URL = "jdbc:mysql://localhost/witch?useUnicode=true&characterEncoding=UTF-8";
	public static final String DB_USER = "root";
	public static final String DB_PASSWORD = "root";

	public static final String ERROR_LOAD_DRIVER = "ошибка загрузки драйвера MySQL";
	public static final String ERROR_WAITING_POOL_READ = "ошибка одновременного чтения из пула соединений";
	public static final String ERROR_WAITING_POOL_WRITE = "ошибка одновременной записи в пул соединений";
	public static final String ERROR_DATA_BASE_CONNECT = "ошибка установления соединения";

	private BlockingQueue<Connection> connections = new LinkedBlockingQueue<Connection>();

	private ConnectionPool() {}

	synchronized public Connection getConnection() throws DataException {
		Connection connection = null;
		while(connection == null) {
			try {
				if(connections.isEmpty()) {
					connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
					connection.setAutoCommit(false);
				} else {
					connection = connections.take();
					if(!connection.isValid(0)) {
						connection = null;
					}
				}
			} catch(InterruptedException e) {
				
				throw new DataException(e);
			} catch(SQLException e) {
				
				throw new DataException(e);
			}
		}
		return connection;
	}

	public void freeConnection(Connection connection) {
		try {
			connections.put(connection);
		} catch(InterruptedException e) {
			
		}
	}

	public void init() throws DataException {
		try {
			Class.forName(DRIVER_CLASS);
		} catch(ClassNotFoundException e) {
			
			throw new DataException(e);
		}
	}

	private static ConnectionPool instance = new ConnectionPool();

	public static ConnectionPool getInstance() {
		return instance;
	}
}