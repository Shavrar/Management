package Servlets;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Project;
import SqlManag.Storage;


public class SaveProjectServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
                                    throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        
        
        Boolean temp=true;
        
        Project project = new Project();
		
        project.setClient(req.getParameter("client-t"));
		
        project.setName(req.getParameter("name-t"));
		
        project.setBegining(Date.valueOf(req.getParameter("begining-t")));
        
        project.setPlaned(Date.valueOf(req.getParameter("planed-t")));
        
        project.setDomain_name(req.getParameter("domain_name-t"));
        //checking if beginning is earlier than planned
        if(Project.compareDate(Date.valueOf(req.getParameter("begining-t")),Date.valueOf(req.getParameter("planed-t")))){
        	temp=false;
        	req.setAttribute("ClientName", req.getParameter("client-t"));
        	req.setAttribute("Fail", "fail");
        	getServletContext().getRequestDispatcher("/WEB-INF/jsp/editProject.jsp")
            .forward(req, resp);
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
		        	req.setAttribute("ClientName", project.getClient());
		        	req.setAttribute("Fail", "fail");
		        	getServletContext().getRequestDispatcher("/WEB-INF/jsp/editProject.jsp")
		            .forward(req, resp);
		        	
		        }
        }
        
        if(temp){
        try {
        	project.setId(Integer.parseInt(req.getParameter("id-t")));
        } catch(NumberFormatException e) {}

        try {

            if(project.getId() == null) {
                Storage.createProject(project);
            } else {
                Storage.updateProject(project);
            }

        } catch(SQLException e) {
            throw new ServletException(e);
        }


        resp.sendRedirect(req.getContextPath() + "/projects.html?ClientName="+project.getClient());
        }
    }
}
