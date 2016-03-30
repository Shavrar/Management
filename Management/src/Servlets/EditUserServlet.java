package Servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Project;
import Entities.User;
import SqlManag.Storage;



public class EditUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
                                    throws ServletException, IOException {
        try {
            
            if(req.getParameter("idU")!=null){
            	Integer id = Integer.parseInt(req.getParameter("idU"));	
            	User user = Storage.readUserById(id);
            
            	req.setAttribute("userT", user);
            
            }
            else{
            	User user = new User();
            	
            	req.setAttribute("userT", user);
            
            }
        } catch(NumberFormatException e) {

        } catch(SQLException e) {
            throw new ServletException(e);

        }
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/editUser.jsp")
                                                      .forward(req, resp);
    }
}
