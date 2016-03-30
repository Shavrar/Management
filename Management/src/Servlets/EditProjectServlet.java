package Servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Project;
import SqlManag.Storage;


public class EditProjectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
                                    throws ServletException, IOException {
        try {
            
            
            if(req.getParameter("idP")!=null){
            	Integer id = Integer.parseInt(req.getParameter("idP"));	
            	Project project = Storage.readProjectById(id);
            
            	req.setAttribute("ProjectT", project);
            
            }
            else{
            	Project project = new Project();
            	project.setClient(req.getParameter("ClientName"));
            	req.setAttribute("ProjectT", project);
            
            }
            
        } catch(NumberFormatException e) {

        } catch(SQLException e) {
            //throw new ServletException(e);

        }
            
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/editProject.jsp")
                                                      .forward(req, resp);
    }
}

