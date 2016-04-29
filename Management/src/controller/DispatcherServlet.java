package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.mySql.ConnectionPool;

import exception.DataException;

import action.ActionManager;
import action.ActionManagerFactory;

public class DispatcherServlet extends HttpServlet {
	public static final String ERROR_DATA_BASE = "ошибка базы данных";

	public void init() {
		
		try {
			ConnectionPool.getInstance().init();
		} catch(DataException e) {
			destroy();
		}
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		process(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		int beginActionName = contextPath.length();
		
		String actionName = uri.substring(beginActionName+1);
	    
		ActionManager actionManager = ActionManagerFactory.getManager();
		try {
			actionManager.execute(actionName, request, response);
			
		} catch(DataException e) {
			//
		}
	}
}