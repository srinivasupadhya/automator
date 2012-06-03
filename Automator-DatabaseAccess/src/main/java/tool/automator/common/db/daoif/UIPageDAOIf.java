package tool.automator.common.db.daoif;

import java.util.HashMap;
import java.util.List;

import tool.automator.common.db.models.UIPageModel;

public interface UIPageDAOIf {
	public UIPageModel getPageById(int id);

	public void savePage(UIPageModel page);

	public List<UIPageModel> getAllPages();

	public HashMap<Integer, String> getPageIdNameMap();

	public List<String> getFilteredPageNames(int projectId, String inputParam);

	public List<UIPageModel> getAllPagesOfProject(int projectId);

	public UIPageModel getPageByName(String pageName, int projectId);

	public List<UIPageModel> getStartPagesOfProject(int projectId);

	public void removePage(int id);
}
