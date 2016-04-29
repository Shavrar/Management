package service;

import java.util.Collection;

import Entities.Client;
import exception.DataException;

public interface ClientService extends Service{
	Collection<Client> getAllClients() throws DataException;
	Client getClientById (int identity) throws DataException;
	Client getClientByName (String name) throws DataException;
	void AddClient (Client client) throws DataException;
	void UpdateClient (Client client) throws DataException;
	void DeleteClient (int identity) throws DataException;
}
