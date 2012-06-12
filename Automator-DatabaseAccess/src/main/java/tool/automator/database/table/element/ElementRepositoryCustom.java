package tool.automator.database.table.element;

import java.util.List;

public interface ElementRepositoryCustom {
	public List<ElementDTO> getAllElementsSortedByRelativeOrder();
	
	public List<String> getFilteredElementNames(Long pageId, String inputParam);
	
	public List<ElementDTO> getElementsOfPage(Long pageId);
}
