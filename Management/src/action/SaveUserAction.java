package action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.User;
import SqlManag.Storage;
import exception.DataException;
import service.ServiceLocator;
import service.UserService;

public class SaveUserAction extends Action {

	public SaveUserAction(ServiceLocator serviceLocator) {
		super(serviceLocator);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws DataException {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		UserService userservice = getService(UserService.class);
		
        User user = new User();
		
        user.setLogin(request.getParameter("login-t"));
		
        user.setPassword(request.getParameter("password-t"));
		
        user.setRole(request.getParameter("role-t"));

        try {
            user.setId(Integer.parseInt(request.getParameter("id-t")));
        } catch(NumberFormatException e) {}

        try {

            if(user.getId() == null) {
            	userservice.AddUser(user);
            } else {
            	userservice.UpdateUser(user);
            }

        } catch(Exception e) {
            throw new DataException(e);
        }


        try {
			response.sendRedirect(request.getContextPath() + "/editUsers.html");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
