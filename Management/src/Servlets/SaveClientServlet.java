package Servlets;

import java.io.IOException;
import java.sql.SQLException;



import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Client;
import SqlManag.Storage;



public class SaveClientServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
                                    throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Client client = new Client();

        client.setName(req.getParameter("name-t"));
		
        client.setAdress(req.getParameter("adress-t"));
	
        
        
        try {
        	client.setId(Integer.parseInt(req.getParameter("id-t")));
        } catch(NumberFormatException e) {}

        try {

            if(client.getId() == null) {
                Storage.createClient(client);
            } else {
                Storage.updateClient(client);
            }

        } catch(SQLException e) {
            throw new ServletException(e);
        }


        resp.sendRedirect(req.getContextPath() + "/index.html");
    }
}