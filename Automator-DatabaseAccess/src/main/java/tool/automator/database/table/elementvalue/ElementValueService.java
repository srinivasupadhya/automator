package tool.automator.database.table.elementvalue;

import java.util.List;
import java.util.Map;

public interface ElementValueService {
	public ElementValueDTO getElementValueById(Long id);

	public void saveElementValue(ElementValueDTO elementValue);

	public List<ElementValueDTO> getAllElementValues();

	public Map<Long, String> getElementValueIdNameMap();

	public List<String> getFilteredElementValues(Long elementId, String inputParam);

	public ElementValueDTO getElementValueOfElement(String value, Long elementId);

	public List<ElementValueDTO> getAllElementValuesOfElement(Long elementId);

	public void removeElementValue(Long id);
}
