package tool.automator.common.db.daoimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import tool.automator.common.db.daoif.UIPageDAOIf;
import tool.automator.common.db.models.UIPageModel;

public class UIPageDAOImpl implements UIPageDAOIf {
	protected EntityManager entityManager;

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public UIPageModel getPageById(int id) {
		return entityManager.find(UIPageModel.class, id);
	}

	@Transactional
	public void savePage(UIPageModel page) {
		entityManager.merge(page);
	}

	@SuppressWarnings("unchecked")
	public List<UIPageModel> getAllPages() {
		return (List<UIPageModel>) entityManager.createQuery("SELECT up FROM UIPageModel up order by up.pageName").getResultList();
	}

	public HashMap<Integer, String> getPageIdNameMap() {
		HashMap<Integer, String> uiPageIdNameMap = new HashMap<Integer, String>();
		List<UIPageModel> pageList = getAllPages();
		for (UIPageModel currentPage : pageList)
			uiPageIdNameMap.put(currentPage.getId(), currentPage.getPageName());
		return uiPageIdNameMap;
	}

	@SuppressWarnings("unchecked")
	public List<String> getFilteredPageNames(int projectId, String inputParam) {
		String jpaQL = "SELECT up FROM UIPageModel up WHERE up.projectId = :projectId";
		if(inputParam != null && !inputParam.isEmpty())
			jpaQL += " AND up.pageName like '%" + inputParam + "%'";
		Query query = entityManager.createQuery(jpaQL);
		query.setParameter("projectId", projectId);
		List<UIPageModel> uiPageList = (List<UIPageModel>) query.getResultList();
		List<String> pageNameList = new ArrayList<String>();
		for (UIPageModel currentUIPage : uiPageList)
			pageNameList.add(currentUIPage.getPageName());
		return pageNameList;
	}

	@SuppressWarnings("unchecked")
	public List<UIPageModel> getAllPagesOfProject(int projectId) {
		String jpaQL = "SELECT up FROM UIPageModel up WHERE up.projectId = :projectId order by up.pageName";
		Query query = entityManager.createQuery(jpaQL);
		query.setParameter("projectId", projectId);
		return (List<UIPageModel>) query.getResultList();
	}

	public UIPageModel getPageByName(String pageName, int projectId) {
		String jpaQL = "SELECT up FROM UIPageModel up WHERE up.pageName = :pageName AND up.projectId = :projectId";
		Query query = entityManager.createQuery(jpaQL);
		query.setParameter("pageName", pageName);
		query.setParameter("projectId", projectId);
		return (UIPageModel) query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public List<UIPageModel> getStartPagesOfProject(int projectId) {
		String jpaQL = "SELECT up FROM UIPageModel up WHERE up.startPage = :startPage AND up.projectId = :projectId";
		Query query = entityManager.createQuery(jpaQL);
		query.setParameter("startPage", true);
		query.setParameter("projectId", projectId);
		return (List<UIPageModel>) query.getResultList();
	}

	@Transactional
	public void removePage(int id) {
		UIPageModel page = getPageById(id);
		if (page != null)
			entityManager.remove(page);
	}
}
