package action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.Service;
import service.ServiceLocator;


import exception.DataException;

abstract public class Action {
	
	private ServiceLocator serviceLocator;

	public Action(ServiceLocator serviceLocator) {
		this.serviceLocator = serviceLocator;
	}
	
	public <Type extends Service> Type getService(Class<Type> key) throws DataException {
		return serviceLocator.getService(key);
	}

	abstract public void exec(HttpServletRequest request, HttpServletResponse response) throws DataException;
}