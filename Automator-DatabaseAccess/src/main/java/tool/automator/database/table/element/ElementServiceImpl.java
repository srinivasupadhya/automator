package tool.automator.database.table.element;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class ElementServiceImpl implements ElementService {
	@Autowired
	private ElementRepository elementRepository;

	public ElementDTO getElementById(Long id) {
		return elementRepository.findOne(id);
	}

	@Transactional
	public void saveElement(ElementDTO element) {
		elementRepository.save(element);
	}

	public List<ElementDTO> getAllElements() {
		return (List<ElementDTO>) elementRepository.findAll();
	}

	public Map<Long, String> getElementIdNameMap() {
		Map<Long, String> elementIdNameMap = new HashMap<Long, String>();
		List<ElementDTO> elementList = getAllElements();
		for (ElementDTO currentElement : elementList)
			elementIdNameMap.put(currentElement.getId(), currentElement.getScriptName());
		return elementIdNameMap;
	}

	public List<ElementDTO> getAllElementsSortedByRelativeOrder() {
		return elementRepository.getAllElementsSortedByRelativeOrder();
	}

	public List<String> getFilteredElementNames(Long pageId, String inputParam) {
		return elementRepository.getFilteredElementNames(pageId, inputParam);
	}

	public List<ElementDTO> getElementsOfPage(Long pageId) {
		return elementRepository.getElementsOfPage(pageId);
	}

	public ElementDTO getElementByScriptName(String elementScriptName, Long pageId) {
		return elementRepository.findByScriptNameAndPageId(elementScriptName, pageId);
	}

	public ElementDTO getElementOfPageByRelativeOrder(Long pageId, int relativeOrder) {
		return elementRepository.findByPageIdAndRelativeOrder(pageId, relativeOrder);
	}

	@Transactional
	public void removeElement(Long id) {
		elementRepository.delete(id);
	}

}
