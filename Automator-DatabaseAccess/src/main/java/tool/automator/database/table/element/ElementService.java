package tool.automator.database.table.element;

import java.util.List;
import java.util.Map;

public interface ElementService {
	public ElementDTO getElementById(Long id);

	public void saveElement(ElementDTO element);

	public List<ElementDTO> getAllElements();

	public Map<Long, String> getElementIdNameMap();

	public List<String> getFilteredElementNames(Long pageId, String inputParam);

	public List<ElementDTO> getElementsOfPage(Long pageId);

	public ElementDTO getElementByScriptName(String elementScriptName, Long pageId);

	public ElementDTO getElementOfPageByRelativeOrder(Long pageId, int relativeOrder);

	public void removeElement(Long id);
}
