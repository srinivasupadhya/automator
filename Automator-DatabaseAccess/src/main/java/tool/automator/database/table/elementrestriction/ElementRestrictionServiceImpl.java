package tool.automator.database.table.elementrestriction;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class ElementRestrictionServiceImpl implements ElementRestrictionService {
	@Autowired
	private ElementRestrictionRepository elementRestrictionRepository;

	public ElementRestrictionDTO getElementRestrictionById(Long id) {
		return elementRestrictionRepository.findOne(id);
	}

	@Transactional
	public void saveElementRestriction(ElementRestrictionDTO elementRestriction) {
		elementRestrictionRepository.save(elementRestriction);
	}

	public List<ElementRestrictionDTO> getAllElementRestrictions() {
		return (List<ElementRestrictionDTO>) elementRestrictionRepository.findAll();
	}

	public List<ElementRestrictionDTO> getElementRestrictionsForElement(Long elementId) {
		return elementRestrictionRepository.findByElementId(elementId);
	}

	@Transactional
	public void removeElementRestriction(Long id) {
		elementRestrictionRepository.delete(id);
	}
}
