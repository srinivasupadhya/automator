package tool.automator.common.db.daoif;

import java.util.HashMap;
import java.util.List;

import tool.automator.common.db.models.ElementValueModel;

public interface ElementValueDAOIf {
	public ElementValueModel getElementValueById(int id);

	public void saveElementValue(ElementValueModel elementValue);

	public List<ElementValueModel> getAllElementValues();
	
	public HashMap<Integer, String> getElementValueIdNameMap();
	
	public List<String> getFilteredElementValues(int pageId, String inputParam);

	public ElementValueModel getElementValueOfElement(String value, int elementId);

	public List<ElementValueModel> getAllElementValuesOfElement(int elementId);

	public void removeElementValue(int id);
}
