package action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import SqlManag.Storage;
import exception.DataException;
import service.ClientService;
import service.ServiceLocator;

public class DeleteClientAction extends Action {

	public DeleteClientAction(ServiceLocator serviceLocator) {
		super(serviceLocator);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws DataException {
		
		ClientService clientService = getService(ClientService.class);
		
		for(String id : request.getParameterValues("id")) {
            try {
                clientService.DeleteClient(Integer.parseInt(id));
            } catch(NumberFormatException e) {

            } catch(Exception e) {
                throw new DataException(e);
                
            }
        }
        try {
			response.sendRedirect(request.getContextPath() + "/index.html");
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}

}
