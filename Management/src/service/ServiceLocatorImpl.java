package service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import dao.DaoManager;
import dao.DaoManagerFactory;
import exception.DataException;

public class ServiceLocatorImpl extends ServiceLocator {
	private static Map<Class<? extends Service>, Class<? extends Service>> services = new ConcurrentHashMap<Class<? extends Service>, Class<? extends Service>>();

	static {
		services.put(UserService.class, UserServiceImpl.class);
		services.put(ClientService.class,ClientServiceImpl.class);
		services.put(ProjectService.class, ProjectServiceImpl.class);
	}

	@SuppressWarnings("unchecked")
	public <Type extends Service> Type getService(Class<Type> key) throws DataException {
		Class<? extends Service> value = services.get(key);
		if(value != null) {
			try {
				ClassLoader classLoader = value.getClassLoader();
				Class<?>[] interfaces = {key};
				Service service = value.getConstructor(DaoManager.class).newInstance(DaoManagerFactory.getInstance());
				InvocationHandler handler = new ServiceInvocationHandlerImpl(service);
				return (Type)Proxy.newProxyInstance(classLoader, interfaces, handler);
			} catch(DataException e) {
				throw e;
			} catch(Exception e) {}
		}
		return null;
	}
}