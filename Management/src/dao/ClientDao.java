package dao;

import java.util.Collection;

import Entities.Client;
import exception.DataException;

public interface ClientDao extends Dao<Client> {	
	Collection<Client> readAllClients() throws DataException;	
	Client readByName(String name) throws DataException;	
}
