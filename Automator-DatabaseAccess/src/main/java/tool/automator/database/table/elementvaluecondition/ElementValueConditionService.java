package tool.automator.database.table.elementvaluecondition;

import java.util.List;

public interface ElementValueConditionService {
	public ElementValueConditionDTO getElementValueConditionById(Long id);

	public void saveElementValueCondition(ElementValueConditionDTO condition);

	public List<ElementValueConditionDTO> getAllElementValueConditions();

	public List<ElementValueConditionDTO> getElementValueConditionsByElementValueRestrictionId(Long conditionNumber);

	public void removeElementValueCondition(Long id);
}
