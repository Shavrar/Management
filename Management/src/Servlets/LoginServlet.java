package Servlets;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Entities.User;
import SqlManag.Storage;


public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
                                    throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
                                    throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp)
                                    throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        
        if(login != null && password != null) {
            /* условие выполняется, если сервлету была передана
             * форма авторизации */
            User user = new User();
            user.setLogin(login);
            user.setPassword(password);
            try {
                if(Storage.checkUser(user).equalsIgnoreCase("admin")) {
                    HttpSession session = req.getSession();
                    user.setRole("admin");
                    session.setAttribute("user", user);
                    resp.sendRedirect(req.getContextPath());
                } 
                else if(Storage.checkUser(user).equalsIgnoreCase("manager")){
                	HttpSession session = req.getSession();
                    user.setRole("manager");
                    session.setAttribute("user", user);
                    resp.sendRedirect(req.getContextPath());
                } else {
                    String message = "Имя пользователя или пароль неопознанны";
                    String url = req.getContextPath()
                               + "/login-form.jsp?message="
                               + URLEncoder.encode(message, "UTF-8");
                    resp.sendRedirect(url);
                }
            } catch(SQLException e) {
                throw new ServletException(e);
            }
        } else {
            resp.sendRedirect(req.getContextPath() + "/login-form.jsp");
        }
    }
}
