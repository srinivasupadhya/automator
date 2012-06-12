package tool.automator.database.table.elementcondition;

import java.util.List;

public interface ElementConditionService {
	public ElementConditionDTO getElementConditionById(Long id);

	public void saveElementCondition(ElementConditionDTO condition);

	public List<ElementConditionDTO> getAllElementConditions();

	public List<ElementConditionDTO> getElementConditionsByElementRestrictionId(Long conditionNumber);

	public void removeElementCondition(Long id);
}
