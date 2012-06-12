package tool.automator.database.table.uipage;

import java.util.List;
import java.util.Map;

public interface UIPageRepositoryCustom {
	public List<UIPageDTO> getAllPagesByPageName();

	public Map<Long, String> getPageIdNameMap();

	public List<String> getFilteredPageNames(Long projectId, String inputParam);

	public List<UIPageDTO> getAllPagesOfProject(Long projectId);
}
