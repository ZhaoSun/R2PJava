package end;

import java.util.Map;

public abstract class Handler {

	protected CatalogApp catalogApp;

	public Handler(CatalogApp catalogApp) {
	    this.catalogApp = catalogApp;
	  }
	
	public abstract HandlerResponse execute(Map parameters);
}