package action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import SqlManag.Storage;
import exception.DataException;
import service.ProjectService;
import service.ServiceLocator;

public class DeleteProjectAction extends Action {

	public DeleteProjectAction(ServiceLocator serviceLocator) {
		super(serviceLocator);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws DataException {
		String temp=null;
		ProjectService projectservice = getService(ProjectService.class);
        for(String id : request.getParameterValues("id")) {
            try {
            	
            	temp=projectservice.getProjectById(Integer.parseInt(id)).getClient();
            	projectservice.DeleteProject(Integer.parseInt(id));
            } catch(NumberFormatException e) {

            } catch(Exception e) {
                throw new DataException(e);

            }
        }
        
        try {
			response.sendRedirect(request.getContextPath() + "/projects.html?ClientName="+temp);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
