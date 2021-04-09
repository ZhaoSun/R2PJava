package end;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CatalogApp {
	
	private Map handlers;

	private static final String NEW_WORKSHOP = "NEW_WORKSHOP";
	static final String ALL_WORKSHOPS = "ALL_WORKSHOPS";
	static final String ALL_WORKSHOPS_STYLESHEET = "ALL_WORKSHOPS_STYLESHEET";

	private WorkshopManager workshopManager;
	
	public CatalogApp() {
		createHandlers();
	}

	HandlerResponse executeActionAndGetResponse(String actionName, Map parameters) {
		Handler handler = lookupHandlerBy(actionName);
	    return handler.execute(parameters);
	}	

	String getFormattedData(String string) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public WorkshopManager getWorkshopManager() {
		return workshopManager;
	}

	public void setWorkshopManager(WorkshopManager workshopManager) {
		this.workshopManager = workshopManager;
	}
	
	public void createHandlers() {
	    handlers = new HashMap();
	    handlers.put(NEW_WORKSHOP, new NewWorkshopHandler(this));
	    handlers.put(ALL_WORKSHOPS, new AllWorkshopsHandler(this));
	  }
	
	private Handler lookupHandlerBy(String actionName) {
	    return (Handler)handlers.get(actionName);
	  }
}
