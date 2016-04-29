package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exception.DataException;

public interface ActionManager {
	void execute(String actionName, HttpServletRequest request, HttpServletResponse response) throws DataException;
}