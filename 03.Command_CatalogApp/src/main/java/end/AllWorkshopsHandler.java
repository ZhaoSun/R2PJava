package end;

import java.util.Iterator;
import java.util.Map;

public class AllWorkshopsHandler extends Handler  {
	
	private CatalogApp catalogApp;

	public AllWorkshopsHandler(CatalogApp catalogApp) {
		super(catalogApp);
	}

	public HandlerResponse execute(Map parameters) {
		String formattedXml = getAllWorkshop();
		return new HandlerResponse(new StringBuffer(formattedXml), catalogApp.ALL_WORKSHOPS_STYLESHEET);
	}

	private String getAllWorkshop() {
		XMLBuilder allWorkshopsXml = new XMLBuilder("workshops");
		WorkshopRepository repository = catalogApp.getWorkshopManager().getWorkshopRepository();
		fillAttribute(allWorkshopsXml, repository);
		String formattedXml = catalogApp.getFormattedData(allWorkshopsXml.toString());
		return formattedXml;
	}

	private void fillAttribute(XMLBuilder allWorkshopsXml, WorkshopRepository repository) {
		Iterator ids = repository.keyIterator();
		while (ids.hasNext()) {
			String id = (String) ids.next();
			Workshop workshop = repository.getWorkshop(id);
			allWorkshopsXml.addBelowParent("workshop");
			allWorkshopsXml.addAttribute("id", workshop.getID());
			allWorkshopsXml.addAttribute("name", workshop.getName());
			allWorkshopsXml.addAttribute("status", workshop.getStatus());
			allWorkshopsXml.addAttribute("duration", workshop.getDurationAsString());
		}
	}
}
