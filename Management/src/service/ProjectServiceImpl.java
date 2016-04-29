package service;

import java.util.Collection;

import Entities.Project;
import dao.DaoManager;
import dao.ProjectDao;
import exception.DataException;

public class ProjectServiceImpl extends ServiceImpl implements ProjectService {

	public ProjectServiceImpl(DaoManager manager) {
		super(manager);		
	}

	@Override
	public Collection<Project> getAllProjects(String clientName) throws DataException {
		ProjectDao projectdao = getManager().createDao(ProjectDao.class);
		return projectdao.readAllProjects(clientName);
	}

	@Override
	public Project getProjectById(int identity) throws DataException {
		ProjectDao projectdao = getManager().createDao(ProjectDao.class);
		return projectdao.read(identity);
	}

	@Override
	public void AddProject(Project project) throws DataException {
		ProjectDao projectdao = getManager().createDao(ProjectDao.class);
		projectdao.create(project);
	}

	@Override
	public void UpdateProject(Project project) throws DataException {
		ProjectDao projectdao = getManager().createDao(ProjectDao.class);
		projectdao.update(project);
	}

	@Override
	public void DeleteProject(int identity) throws DataException {
		ProjectDao projectdao = getManager().createDao(ProjectDao.class);
		projectdao.delete(identity);
	}

	@Override
	public Integer getProjectsCount(String name) throws DataException {
		ProjectDao projectdao = getManager().createDao(ProjectDao.class);
		return projectdao.getNumberOfAllProjects(name);
	}

	@Override
	public Integer getFinishedProjectsCount(String name) throws DataException {
		ProjectDao projectdao = getManager().createDao(ProjectDao.class);
		return projectdao.getNumberOfFinishedProjects(name);
	}

}
