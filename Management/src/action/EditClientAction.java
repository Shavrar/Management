package action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Client;
import SqlManag.Storage;
import exception.DataException;
import service.ClientService;
import service.ServiceLocator;

public class EditClientAction extends Action {

	public EditClientAction(ServiceLocator serviceLocator) {
		super(serviceLocator);	
	}

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws DataException {
		try {
       	 if(request.getParameter("id")!=null){
            	Integer id = Integer.parseInt(request.getParameter("id"));	
            	Client client = getService(ClientService.class).getClientById(id);
            
            	request.setAttribute("client", client);
            
            }
            else{
           	 Client client = new Client();            	
           	 request.setAttribute("client", client);
            
            }
           
       	request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/editClient.jsp").forward(request, response);
       } catch(NumberFormatException e) {

       } catch(Exception e) {
           throw new DataException();

       }
       

	}

}
