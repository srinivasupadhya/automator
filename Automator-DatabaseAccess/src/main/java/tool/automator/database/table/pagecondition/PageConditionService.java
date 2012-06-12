package tool.automator.database.table.pagecondition;

import java.util.List;

public interface PageConditionService {
	public PageConditionDTO getPageConditionById(Long id);

	public void savePageCondition(PageConditionDTO condition);

	public List<PageConditionDTO> getAllPageConditions();

	public List<PageConditionDTO> getPageConditionsByPageDependencyId(Long conditionNumber);

	public void removePageCondition(Long id);
}
