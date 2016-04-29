package action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Client;
import exception.DataException;
import service.ClientService;
import service.ServiceLocator;

public class SaveClientAction extends Action {

	public SaveClientAction(ServiceLocator serviceLocator) {
		super(serviceLocator);
	}

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws DataException {
		
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        Client client = new Client();

        client.setName(request.getParameter("name-t"));
		
        client.setAdress(request.getParameter("adress-t"));
	
        
        
        try {
        	client.setId(Integer.parseInt(request.getParameter("id-t")));
        } catch(NumberFormatException e) {}

        try {
        	ClientService clientservice = getService(ClientService.class);
            if(client.getId() == null) {
                
                clientservice.AddClient(client);
            } else {              
                clientservice.UpdateClient(client);
            }

        } catch(Exception e) {
            throw new DataException(e);
        }


        try {
			response.sendRedirect(request.getContextPath() + "/index.html");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
