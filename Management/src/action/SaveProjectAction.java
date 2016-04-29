package action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Project;

import exception.DataException;
import service.ProjectService;
import service.ServiceLocator;

public class SaveProjectAction extends Action {

	public SaveProjectAction(ServiceLocator serviceLocator) {
		super(serviceLocator);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) throws DataException {
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
		ProjectService projectservice = getService(ProjectService.class);
        
        Boolean temp=true;
        
        Project project = new Project();
        
        try {
        	project.setId(Integer.parseInt(req.getParameter("id-t")));
        } catch(NumberFormatException e) {}
		
        project.setClient(req.getParameter("client-t"));
		
        project.setName(req.getParameter("name-t"));
		
        project.setBegining(Date.valueOf(req.getParameter("begining-t")));
        
        project.setPlaned(Date.valueOf(req.getParameter("planed-t")));
        
        project.setDomain_name(req.getParameter("domain_name-t"));
        
        //checking if beginning is earlier than planned
        if(Project.compareDate(Date.valueOf(req.getParameter("begining-t")),Date.valueOf(req.getParameter("planed-t")))){
        	temp=false;
        	req.setAttribute("ProjectT", project);
        	req.setAttribute("Fail", "fail");
        	try {
				req.getServletContext().getRequestDispatcher("/WEB-INF/jsp/editProject.jsp")
				.forward(req, resp);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
        //small check for right input of real
        if(temp){
		        if(req.getParameter("real-t").equalsIgnoreCase("")){
		        	project.setReal(null);
		        }      
		        else if(req.getParameter("real-t").length()==10){
		        project.setReal(Date.valueOf(req.getParameter("real-t")));
		        }
		        else{
		        	temp=false;
		        		        
		        	req.setAttribute("ProjectT", project);
		        	req.setAttribute("Fail", "fail");
		        	try {
						req.getServletContext().getRequestDispatcher("/WEB-INF/jsp/editProject.jsp")
						.forward(req, resp);
					} catch (ServletException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		        	
		        }
        }
        
        if(temp){
	
	        try {
	
	            if(project.getId() == null) {
	            	projectservice.AddProject(project);
	            } else {
	            	projectservice.UpdateProject(project);
	            }
	
	        } catch(DataException e) {
	        	//
	            throw new DataException(e);
	        }
	
	        
	        try {
				resp.sendRedirect(req.getContextPath() + "/projects.html?ClientName="+project.getClient());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
	}

}
