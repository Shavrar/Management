package dao;

import java.util.Collection;

import Entities.Project;
import exception.DataException;

public interface ProjectDao extends Dao<Project> {	
	Collection<Project> readAllProjects(String nameOfClient) throws DataException;	
	Integer getNumberOfAllProjects(String nameOfClient) throws DataException;
	Integer getNumberOfFinishedProjects(String nameOfClient) throws DataException;
}
