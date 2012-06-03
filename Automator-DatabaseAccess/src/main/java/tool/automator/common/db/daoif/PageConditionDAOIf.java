package tool.automator.common.db.daoif;

import java.util.List;

import tool.automator.common.db.models.PageConditionModel;

public interface PageConditionDAOIf {
	public PageConditionModel getPageConditionById(int id);

	public void savePageCondition(PageConditionModel condition);

	public List<PageConditionModel> getAllPageConditions();

	public List<PageConditionModel> getPageConditionsByPageDependencyId(int conditionNumber);

	public void removePageCondition(int id);
}
