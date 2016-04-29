package action;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Project;
import exception.DataException;
import service.ProjectService;
import service.ServiceLocator;

public class EditProjectAction extends Action {

	public EditProjectAction(ServiceLocator serviceLocator) {
		super(serviceLocator);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws DataException {
		 try {
			 ProjectService projectservice = getService(ProjectService.class);
	            
	            if(request.getParameter("id")!=null){
	            	Integer id = Integer.parseInt(request.getParameter("id"));	
	            	Project project = projectservice.getProjectById(id);
	            
	            	request.setAttribute("ProjectT", project);
	            
	            }
	            else{
	            	Project project = new Project();
	            	project.setClient(request.getParameter("ClientName"));
	            	request.setAttribute("ProjectT", project);
	            
	            }
	            
	        } catch(NumberFormatException e) {

	        } catch(Exception e) {
	            //throw new ServletException(e);

	        }
	            
	        try {
				request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/editProject.jsp")
				                                              .forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
