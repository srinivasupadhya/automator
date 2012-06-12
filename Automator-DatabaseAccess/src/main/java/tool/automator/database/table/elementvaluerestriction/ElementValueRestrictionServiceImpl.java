package tool.automator.database.table.elementvaluerestriction;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class ElementValueRestrictionServiceImpl implements ElementValueRestrictionService {
	@Autowired
	private ElementValueRestrictionRepository elementValueRestrictionRepository;

	public ElementValueRestrictionDTO getElementValueRestrictionById(Long id) {
		return elementValueRestrictionRepository.findOne(id);
	}

	@Transactional
	public void saveElementValueRestriction(ElementValueRestrictionDTO elementValueDependency) {
		elementValueRestrictionRepository.save(elementValueDependency);
	}

	public List<ElementValueRestrictionDTO> getAllElementValueRestrictions() {
		return (List<ElementValueRestrictionDTO>) elementValueRestrictionRepository.findAll();
	}

	public List<ElementValueRestrictionDTO> getElementValueRestrictionsForElementValue(Long elementValueId) {
		return elementValueRestrictionRepository.findByElementValueId(elementValueId);
	}

	@Transactional
	public void removeElementValueRestriction(Long id) {
		elementValueRestrictionRepository.delete(id);
	}
}
