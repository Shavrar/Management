package service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import exception.DataException;

public class ServiceInvocationHandlerImpl implements InvocationHandler {
	private Service service;

	public ServiceInvocationHandlerImpl(Service service) {
		this.service = service;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] arguments) throws Throwable {
		try {
			Object result = method.invoke(service, arguments);
			service.getManager().transactionCommit();
			return result;
		} catch(DataException e) {
			try {
				service.getManager().transactionRollback();
			} catch(DataException e1) {}
			throw e;
		} catch(IllegalArgumentException e) {
		} catch(IllegalAccessException e) {
		} catch(InvocationTargetException e) {
			try {
				service.getManager().transactionRollback();
			} catch(DataException e1) {}
			throw e.getCause();
		} finally {
			service.getManager().close();
		}
		return null;
	}
}