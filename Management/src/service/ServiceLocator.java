package service;

import exception.DataException;

abstract public class ServiceLocator {
	abstract public <Type extends Service> Type getService(Class<Type> key) throws DataException; 
}