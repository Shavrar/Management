package Servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import SqlManag.Storage;

public class DeleteProjectServlet extends HttpServlet {
	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
                                    throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
                                    throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp)
                                    throws ServletException, IOException {
    	String temp=null;
        for(String id : req.getParameterValues("id")) {
            try {
            	temp=Storage.readProjectById(Integer.parseInt(id)).getClient();
                Storage.deleteProject(Integer.parseInt(id));
            } catch(NumberFormatException e) {

            } catch(SQLException e) {
                throw new ServletException(e);

            }
        }
        
        resp.sendRedirect(req.getContextPath() + "/projects.html?ClientName="+temp);
    }
}

