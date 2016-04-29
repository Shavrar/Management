package service;

import dao.DaoManager;

abstract public class ServiceImpl implements Service {
	private DaoManager manager = null;

	public ServiceImpl(DaoManager manager) {
		this.manager = manager;
	}

	@Override
	public DaoManager getManager() {
		return manager;
	}
}