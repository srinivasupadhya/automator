package tool.automator.executor.datamanager;

import java.util.HashMap;
import java.util.List;

import tool.automator.common.models.interfaces.UIPageModelIf;

public class PageDataManager {
	private List<? extends UIPageModelIf> pageList;
	private HashMap<Integer, UIPageModelIf> pageIdObjMap;
	private HashMap<String, UIPageModelIf> pageNameObjMap;

	public PageDataManager(List<? extends UIPageModelIf> pageList) {
		this.pageList = pageList;
		pageIdObjMap = new HashMap<Integer, UIPageModelIf>();
		pageNameObjMap = new HashMap<String, UIPageModelIf>();
		for (UIPageModelIf currentPage : this.pageList) {
			pageIdObjMap.put(currentPage.getId(), currentPage);
			pageNameObjMap.put(currentPage.getPageName() + "_" + currentPage.getProjectId(), currentPage);
		}
	}

	public UIPageModelIf getPageById(int pageId) {
		return pageIdObjMap.get(pageId);
	}

	public UIPageModelIf getPageByName(String pageName, int projectId) {
		return pageNameObjMap.get(pageName + "_" + projectId);
	}
}
