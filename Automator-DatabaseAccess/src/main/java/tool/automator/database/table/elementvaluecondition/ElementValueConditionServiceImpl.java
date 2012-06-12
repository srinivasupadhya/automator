package tool.automator.database.table.elementvaluecondition;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class ElementValueConditionServiceImpl implements ElementValueConditionService {
	@Autowired
	private ElementValueConditionRepository elementValueConditionRepository;

	public ElementValueConditionDTO getElementValueConditionById(Long id) {
		return elementValueConditionRepository.findOne(id);
	}

	@Transactional
	public void saveElementValueCondition(ElementValueConditionDTO condition) {
		elementValueConditionRepository.save(condition);
	}

	public List<ElementValueConditionDTO> getAllElementValueConditions() {
		return (List<ElementValueConditionDTO>) elementValueConditionRepository.findAll();
	}

	public List<ElementValueConditionDTO> getElementValueConditionsByElementValueRestrictionId(Long elementValueRestrictionId) {
		return elementValueConditionRepository.findByElementValueRestrictionId(elementValueRestrictionId);
	}

	@Transactional
	public void removeElementValueCondition(Long id) {
		elementValueConditionRepository.delete(id);
	}
}
