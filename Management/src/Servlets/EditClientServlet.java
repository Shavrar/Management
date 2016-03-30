package Servlets;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Entities.Client;
import Entities.User;
import SqlManag.Storage;



public class EditClientServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
                                    throws ServletException, IOException {
        try {
        	 if(req.getParameter("id")!=null){
             	Integer id = Integer.parseInt(req.getParameter("id"));	
             	Client client = Storage.readClientById(id);
             
             	req.setAttribute("client", client);
             
             }
             else{
            	 Client client = new Client();            	
            	 req.setAttribute("client", client);
             
             }
            
        } catch(NumberFormatException e) {

        } catch(SQLException e) {
            throw new ServletException(e);

        }
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/editClient.jsp")
                                                      .forward(req, resp);
    }
}