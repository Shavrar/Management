package Servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Entities.Client;
import SqlManag.Storage;



public class ListServlet extends HttpServlet {

    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost/witch?"
                                   + "useUnicode=true&"
                                   + "characterEncoding=UTF-8";
    public static final String USER = "root";
    public static final String PASSWORD = "root";


    @Override
    public void init() throws ServletException {

        try {
            Storage.init(DRIVER, URL, USER, PASSWORD);
        } catch(ClassNotFoundException e) {
            throw new ServletException(e);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
                                    throws ServletException, IOException {

        try {

            Collection<Client> clients = Storage.readAllClients();
            req.setAttribute("clients", clients);
            getServletContext().
                            getRequestDispatcher("/WEB-INF/jsp/index.jsp")
                                                      .forward(req, resp);

        } catch(SQLException e) {
            throw new ServletException(e);
        }

    }
}