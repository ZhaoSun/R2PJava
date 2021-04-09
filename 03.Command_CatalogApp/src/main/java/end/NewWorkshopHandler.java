package end;

import java.util.Map;

public class NewWorkshopHandler extends Handler {
	
	public NewWorkshopHandler(CatalogApp catalogApp) {
		super(catalogApp);
	}
	
	public HandlerResponse execute(Map parameters) {
		createNewWorkshop(parameters);
		catalogApp.executeActionAndGetResponse(catalogApp.ALL_WORKSHOPS, parameters);
		return null;
	}

	private void createNewWorkshop(Map parameters) {
		String nextWorkshopID = catalogApp.getWorkshopManager().getNextWorkshopID();
		StringBuffer newWorkshopContents = newWorkshopContents(nextWorkshopID);
		catalogApp.getWorkshopManager().addWorkshop(newWorkshopContents);
		parameters.put("id", nextWorkshopID);
	}

	private StringBuffer newWorkshopContents(String nextWorkshopID) {
		StringBuffer newWorkshopContents = catalogApp.getWorkshopManager().createNewFileFromTemplate(nextWorkshopID,
				catalogApp.getWorkshopManager().getWorkshopDir(), catalogApp.getWorkshopManager().getWorkshopTemplate());
		return newWorkshopContents;
	}
}
