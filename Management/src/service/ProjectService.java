package service;

import java.util.Collection;

import Entities.Project;
import exception.DataException;

public interface ProjectService extends Service{
	Collection<Project> getAllProjects(String clientName) throws DataException;
	Project getProjectById (int identity) throws DataException;
	void AddProject (Project project) throws DataException;
	void UpdateProject (Project project) throws DataException;
	void DeleteProject (int identity) throws DataException;
	Integer getProjectsCount(String name)throws DataException;
	Integer getFinishedProjectsCount(String name)throws DataException;
}
