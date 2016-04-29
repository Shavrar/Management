package action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.User;
import SqlManag.Storage;
import exception.DataException;
import service.ServiceLocator;
import service.UserService;

public class EditUserAction extends Action {

	public EditUserAction(ServiceLocator serviceLocator) {
		super(serviceLocator);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws DataException {
		try {
			UserService userservice = getService(UserService.class);
            if(request.getParameter("idU")!=null){
            	Integer id = Integer.parseInt(request.getParameter("idU"));	
            	User user = userservice.getUserById(id);
            
            	request.setAttribute("userT", user);
            
            }
            else{
            	User user = new User();
            	
            	request.setAttribute("userT", user);
            
            }
        } catch(NumberFormatException e) {

        } catch(Exception e) {
            throw new DataException(e);

        }
        try {
			request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/editUser.jsp")
			                                              .forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
