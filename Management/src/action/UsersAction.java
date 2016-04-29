package action;


import java.util.Collection;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.User;

import exception.DataException;
import service.ServiceLocator;
import service.UserService;

public class UsersAction extends Action {

	public UsersAction(ServiceLocator serviceLocator) {
		super(serviceLocator);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws DataException {
		try {
			
            Collection<User> users = getService(UserService.class).getAllUsers();
            request.setAttribute("users", users);
            request.getServletContext().getRequestDispatcher("/WEB-INF/jsp/users.jsp").forward(request, response);

        } catch(Exception e) {
            throw new DataException(e);
        }

	}

}
