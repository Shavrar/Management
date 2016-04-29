package action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Entities.User;
import SqlManag.Storage;
import exception.DataException;
import service.ServiceLocator;
import service.UserService;

public class LoginAction extends Action {

	public LoginAction(ServiceLocator serviceLocator) {
		super(serviceLocator);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void exec(HttpServletRequest request, HttpServletResponse response) throws DataException {
		UserService userservice = getService(UserService.class);
		String login = request.getParameter("login");
        String password = request.getParameter("password");
        
        if(login != null && password != null) {
            /* условие выполняется, если сервлету была передана
             * форма авторизации */
            User user = new User();
            user.setLogin(login);
            user.setPassword(password);
            try {
                if(userservice.CheckUser(user).equalsIgnoreCase("admin")) {
                    HttpSession session = request.getSession();
                    user.setRole("admin");
                    session.setAttribute("user", user);
                    try {
						response.sendRedirect(request.getContextPath());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                } 
                else if(userservice.CheckUser(user).equalsIgnoreCase("manager")){
                	HttpSession session = request.getSession();
                    user.setRole("manager");
                    session.setAttribute("user", user);
                    try {
						response.sendRedirect(request.getContextPath());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                } else {
                    String message = "Имя пользователя или пароль неопознанны";
                    try{
                    String url = request.getContextPath()
                               + "/login-form.jsp?message="
                               + URLEncoder.encode(message, "UTF-8");
                    
						response.sendRedirect(url);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                }
            } catch(Exception e) {
                throw new DataException(e);
            }
        } else {
            try {
				response.sendRedirect(request.getContextPath() + "/login-form.jsp");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }

	}

}
