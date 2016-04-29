package action;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.ServiceLocator;
import service.ServiceLocatorFactory;

import exception.DataException;

public class ActionManagerImpl implements ActionManager {
	private static Map<String, Class<? extends Action>> actions = new ConcurrentHashMap<String, Class<? extends Action>>();

	static {
		actions.put("", ClientsAction.class);
		actions.put("index.html", ClientsAction.class);
		actions.put("editClient.html", EditClientAction.class);
		actions.put("saveClient.html", SaveClientAction.class);
		actions.put("deleteClient.html", DeleteClientAction.class);

		actions.put("login.html", LoginAction.class);
		actions.put("logout.html", LogoutAction.class);

		actions.put("deleteUser.html", DeleteUserAction.class);

		actions.put("editUser.html", EditUserAction.class);

		actions.put("saveUser.html", SaveUserAction.class);

		actions.put("editUsers.html", UsersAction.class);
		actions.put("deleteProject.html", DeleteProjectAction.class);
		actions.put("editProject.html", EditProjectAction.class);
		actions.put("saveProject.html", SaveProjectAction.class);
		actions.put("projects.html", ProjectsAction.class);
		
	}

	@Override
	public void execute(String actionName, HttpServletRequest request, HttpServletResponse response) throws DataException {
		try {
			ServiceLocator serviceLocator = ServiceLocatorFactory.getInstance();
			Class<? extends Action> actionClass = actions.get(actionName);
			if(actionClass != null) {
				Action action = actionClass.getConstructor(ServiceLocator.class).newInstance(serviceLocator);
				
				action.exec(request, response);
				
			}
		} catch(DataException e) {			
			throw e;
		} catch(Exception e) {
			
		}		
	}
}