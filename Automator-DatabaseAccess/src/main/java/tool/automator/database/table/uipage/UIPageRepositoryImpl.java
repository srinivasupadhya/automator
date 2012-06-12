package tool.automator.database.table.uipage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import tool.automator.database.table.AbstractRepositoryCustom;

public class UIPageRepositoryImpl extends AbstractRepositoryCustom implements UIPageRepositoryCustom {

	@SuppressWarnings("unchecked")
	public List<UIPageDTO> getAllPagesByPageName() {
		return (List<UIPageDTO>) em.createQuery("SELECT up FROM UIPageModel up order by up.pageName").getResultList();
	}

	public Map<Long, String> getPageIdNameMap() {
		Map<Long, String> uiPageIdNameMap = new HashMap<Long, String>();
		List<UIPageDTO> pageList = getAllPagesByPageName();
		for (UIPageDTO currentPage : pageList)
			uiPageIdNameMap.put(currentPage.getId(), currentPage.getPageName());
		return uiPageIdNameMap;
	}

	@SuppressWarnings("unchecked")
	public List<String> getFilteredPageNames(Long projectId, String inputParam) {
		String jpaQL = "SELECT up FROM UIPageModel up WHERE up.projectId = :projectId";
		if (inputParam != null && !inputParam.isEmpty())
			jpaQL += " AND up.pageName like '%" + inputParam + "%'";
		Query query = em.createQuery(jpaQL);
		query.setParameter("projectId", projectId);

		List<UIPageDTO> uiPageList = (List<UIPageDTO>) query.getResultList();

		List<String> pageNameList = new ArrayList<String>();
		for (UIPageDTO currentUIPage : uiPageList)
			pageNameList.add(currentUIPage.getPageName());
		return pageNameList;
	}

	@SuppressWarnings("unchecked")
	public List<UIPageDTO> getAllPagesOfProject(Long projectId) {
		String jpaQL = "SELECT up FROM UIPageModel up WHERE up.projectId = :projectId order by up.pageName";
		Query query = em.createQuery(jpaQL);
		query.setParameter("projectId", projectId);

		return (List<UIPageDTO>) query.getResultList();
	}
}
