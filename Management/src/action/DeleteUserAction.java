package action;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import SqlManag.Storage;
import exception.DataException;
import service.ServiceLocator;
import service.UserService;

public class DeleteUserAction extends Action {

	public DeleteUserAction(ServiceLocator serviceLocator) {
		super(serviceLocator);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws DataException {
		UserService userservice = getService(UserService.class);
		for(String id : request.getParameterValues("id")) {
            try {
                userservice.DeleteUser(Integer.parseInt(id));
            } catch(NumberFormatException e) {

            } catch(Exception e) {
                throw new DataException(e);

            }
        }
        try {
			response.sendRedirect(request.getContextPath() + "/editUsers.html");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
