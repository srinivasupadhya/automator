package tool.automator.common.db.daoif;

import java.util.HashMap;
import java.util.List;

import tool.automator.common.db.models.ElementModel;

public interface ElementDAOIf {
	public ElementModel getElementById(int id);

	public void saveElement(ElementModel element);

	public List<ElementModel> getAllElements();
	
	public HashMap<Integer, String> getElementIdNameMap();
	
	public List<String> getFilteredElementNames(int projectId, String inputParam);
	
	public List<ElementModel> getElementsOfPage(int pageId);

	public ElementModel getElementByScriptName(String elementScriptName, int pageId);

	public ElementModel getElementOfPageByRelativeOrder(int pageId, int relativeOrder);

	public void removeElement(int id);
}
