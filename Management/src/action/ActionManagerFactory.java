package action;

public class ActionManagerFactory {
	public static ActionManager getManager() {
		return new ActionManagerImpl();
	}
}