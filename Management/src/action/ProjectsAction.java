package action;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Project;
import exception.DataException;
import service.ProjectService;
import service.ServiceLocator;

public class ProjectsAction extends Action {

	public ProjectsAction(ServiceLocator serviceLocator) {
		super(serviceLocator);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws DataException {
		try {
			ProjectService projectservice = getService(ProjectService.class);
        	String name=request.getParameter("ClientName");
            Collection<Project> projects = projectservice.getAllProjects(name);
            request.setAttribute("projects", projects);
            request.setAttribute("ClientName", name);
            try {
				request.getServletContext().
				                getRequestDispatcher("/WEB-INF/jsp/projects.jsp")
				                                          .forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

        } catch(Exception e) {
            throw new DataException(e);
        }

	}

}
