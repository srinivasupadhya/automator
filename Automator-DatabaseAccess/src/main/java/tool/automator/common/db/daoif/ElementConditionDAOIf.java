package tool.automator.common.db.daoif;

import java.util.List;

import tool.automator.common.db.models.ElementConditionModel;

public interface ElementConditionDAOIf {
	public ElementConditionModel getElementConditionById(int id);

	public void saveElementCondition(ElementConditionModel condition);

	public List<ElementConditionModel> getAllElementConditions();

	public List<ElementConditionModel> getElementConditionsByElementRestrictionId(int conditionNumber);

	public void removeElementCondition(int id);
}
