package tool.automator.database.table.elementvalue;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class ElementValueServiceImpl implements ElementValueService {
	@Autowired
	private ElementValueRepository elementValueRepository;

	public ElementValueDTO getElementValueById(Long id) {
		return elementValueRepository.findOne(id);
	}

	@Transactional
	public void saveElementValue(ElementValueDTO elementValue) {
		elementValueRepository.save(elementValue);
	}

	public List<ElementValueDTO> getAllElementValues() {
		return (List<ElementValueDTO>) elementValueRepository.findAll();
	}

	@Override
	public Map<Long, String> getElementValueIdNameMap() {
		return elementValueRepository.getElementValueIdNameMap();
	}

	@Override
	public List<String> getFilteredElementValues(Long elementId, String inputParam) {
		return elementValueRepository.getFilteredElementValues(elementId, inputParam);
	}

	@Override
	public ElementValueDTO getElementValueOfElement(String value, Long elementId) {
		return elementValueRepository.findByScriptValueAndElementId(value, elementId);
	}

	@Override
	public List<ElementValueDTO> getAllElementValuesOfElement(Long elementId) {
		return elementValueRepository.findByElementId(elementId);
	}

	@Transactional
	public void removeElementValue(Long id) {
		elementValueRepository.delete(id);
	}
}
