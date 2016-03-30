package Servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import Entities.User;
import SqlManag.Storage;

public class SaveUserServlet  extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
                                    throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        User user = new User();
		
        user.setLogin(req.getParameter("login-t"));
		
        user.setPassword(req.getParameter("password-t"));
		
        user.setRole(req.getParameter("role-t"));

        try {
            user.setId(Integer.parseInt(req.getParameter("id-t")));
        } catch(NumberFormatException e) {}

        try {

            if(user.getId() == null) {
                Storage.createUser(user);
            } else {
                Storage.updateUser(user);
            }

        } catch(SQLException e) {
            throw new ServletException(e);
        }


        resp.sendRedirect(req.getContextPath() + "/editUsers.html");
    }
}
