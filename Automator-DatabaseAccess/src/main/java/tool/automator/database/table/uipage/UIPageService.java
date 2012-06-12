package tool.automator.database.table.uipage;

import java.util.List;
import java.util.Map;

public interface UIPageService {
	public UIPageDTO getPageById(Long id);

	public void savePage(UIPageDTO page);

	public List<UIPageDTO> getAllPages();

	public Map<Long, String> getPageIdNameMap();

	public List<String> getFilteredPageNames(Long projectId, String inputParam);

	public List<UIPageDTO> getAllPagesOfProject(Long projectId);

	public UIPageDTO getPageByName(String pageName, Long projectId);

	public List<UIPageDTO> getStartPagesOfProject(Long projectId);

	public void removePage(Long id);
}
