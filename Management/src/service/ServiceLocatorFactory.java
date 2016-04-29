package service;

public class ServiceLocatorFactory {
	public static ServiceLocator getInstance() {
		return new ServiceLocatorImpl();
	}
}