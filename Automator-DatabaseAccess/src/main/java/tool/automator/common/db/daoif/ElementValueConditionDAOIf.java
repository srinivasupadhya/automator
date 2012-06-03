package tool.automator.common.db.daoif;

import java.util.List;

import tool.automator.common.db.models.ElementValueConditionModel;

public interface ElementValueConditionDAOIf {
	public ElementValueConditionModel getElementValueConditionById(int id);

	public void saveElementValueCondition(ElementValueConditionModel condition);

	public List<ElementValueConditionModel> getAllElementValueConditions();

	public List<ElementValueConditionModel> getElementValueConditionsByElementValueRestrictionId(int conditionNumber);

	public void removeElementValueCondition(int id);
}
