package service;

import java.util.Collection;

import Entities.Client;
import dao.ClientDao;
import dao.DaoManager;
import exception.DataException;

public class ClientServiceImpl extends ServiceImpl implements ClientService {

	public ClientServiceImpl(DaoManager manager) {
		super(manager);		
	}

	@Override
	public Collection<Client> getAllClients() throws DataException {
		ClientDao clientdao = getManager().createDao(ClientDao.class);
		return clientdao.readAllClients();
	}

	@Override
	public Client getClientById(int identity) throws DataException {
		ClientDao clientdao = getManager().createDao(ClientDao.class);
		return clientdao.read(identity);
	}

	@Override
	public Client getClientByName(String name) throws DataException {
		ClientDao clientdao = getManager().createDao(ClientDao.class);
		return clientdao.readByName(name);
	}

	@Override
	public void AddClient(Client client) throws DataException {
		ClientDao clientdao = getManager().createDao(ClientDao.class);
		clientdao.create(client);
	}

	@Override
	public void UpdateClient(Client client) throws DataException {
		ClientDao clientdao = getManager().createDao(ClientDao.class);
		clientdao.update(client);
	}

	@Override
	public void DeleteClient(int identity) throws DataException {
		ClientDao clientdao = getManager().createDao(ClientDao.class);
		clientdao.delete(identity);
	}

}
