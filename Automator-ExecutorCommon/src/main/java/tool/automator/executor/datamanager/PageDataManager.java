package tool.automator.executor.datamanager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tool.automator.common.models.UIPageModelIf;

public class PageDataManager {
	private List<? extends UIPageModelIf> pageList;
	private Map<Long, UIPageModelIf> pageIdObjMap;
	private Map<String, UIPageModelIf> pageNameObjMap;

	public PageDataManager(List<? extends UIPageModelIf> pageList) {
		this.pageList = pageList;
		pageIdObjMap = new HashMap<Long, UIPageModelIf>();
		pageNameObjMap = new HashMap<String, UIPageModelIf>();
		
		for (UIPageModelIf currentPage : this.pageList) {
			pageIdObjMap.put(currentPage.getId(), currentPage);
			pageNameObjMap.put(currentPage.getPageName() + "_" + currentPage.getProjectId(), currentPage);
		}
	}

	public UIPageModelIf getPageById(int pageId) {
		return pageIdObjMap.get(pageId);
	}

	public UIPageModelIf getPageByName(String pageName, Long projectId) {
		return pageNameObjMap.get(pageName + "_" + projectId);
	}
}
