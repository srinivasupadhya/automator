package tool.automator.database.table.elementcondition;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class ElementConditionServiceImpl implements ElementConditionService {
	@Autowired
	private ElementConditionRepository elementConditionRepository;

	public ElementConditionDTO getElementConditionById(Long id) {
		return elementConditionRepository.findOne(id);
	}

	@Transactional
	public void saveElementCondition(ElementConditionDTO condition) {
		elementConditionRepository.save(condition);
	}

	public List<ElementConditionDTO> getAllElementConditions() {
		return (List<ElementConditionDTO>) elementConditionRepository.findAll();
	}

	@Override
	public List<ElementConditionDTO> getElementConditionsByElementRestrictionId(Long conditionNumber) {
		return elementConditionRepository.findByElementRestrictionId(conditionNumber);
	}

	@Transactional
	public void removeElementCondition(Long id) {
		elementConditionRepository.delete(id);
	}
}
