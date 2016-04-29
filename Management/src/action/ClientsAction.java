package action;


import java.util.Collection;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Client;
import exception.DataException;
import service.ClientService;
import service.ProjectService;
import service.ServiceLocator;

public class ClientsAction extends Action{
	
	public ClientsAction(ServiceLocator serviceLocator) {
		super(serviceLocator);
	}

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws DataException {
		try {
			ProjectService projectservice = getService(ProjectService.class);
            Collection<Client> clients = getService(ClientService.class).getAllClients();
            
            for(Client client : clients){
            	client.setAll(projectservice.getProjectsCount(client.getName()));
            	client.setFinished(projectservice.getFinishedProjectsCount(client.getName()));
            }
            
            request.setAttribute("clients", clients);
            request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);

        } catch(Exception e) {
            throw new DataException();
        }
		
	}

}
