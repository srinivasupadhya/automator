package tool.automator.common.db.daoimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import tool.automator.common.db.daoif.ElementDAOIf;
import tool.automator.common.db.models.ElementModel;

public class ElementDAOImpl implements ElementDAOIf {
	protected EntityManager entityManager;

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public ElementModel getElementById(int id) {
		return entityManager.find(ElementModel.class, id);
	}

	@Transactional
	public void saveElement(ElementModel element) {
		entityManager.merge(element);
	}

	@SuppressWarnings("unchecked")
	public List<ElementModel> getAllElements() {
		return (List<ElementModel>) entityManager.createQuery("SELECT e FROM ElementModel e order by e.relativeOrder").getResultList();
	}

	public HashMap<Integer, String> getElementIdNameMap() {
		HashMap<Integer, String> elementIdNameMap = new HashMap<Integer, String>();
		List<ElementModel> elementList = getAllElements();
		for (ElementModel currentElement : elementList)
			elementIdNameMap.put(currentElement.getId(), currentElement.getScriptName());
		return elementIdNameMap;
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getFilteredElementNames(int pageId, String inputParam) {
		String jpaQL = "SELECT e FROM ElementModel e WHERE e.pageId = :pageId";
		if(inputParam != null && !inputParam.isEmpty())
			jpaQL += " AND e.scriptName like '%" + inputParam + "%'";
		Query query = entityManager.createQuery(jpaQL);
		query.setParameter("pageId", pageId);
		List<ElementModel> elementList = (List<ElementModel>) query.getResultList();
		List<String> pageNameList = new ArrayList<String>();
		for (ElementModel currentElement : elementList)
			pageNameList.add(currentElement.getScriptName());
		return pageNameList;
	}

	@SuppressWarnings("unchecked")
	public List<ElementModel> getElementsOfPage(int pageId) {
		String jpaQL = "SELECT e FROM ElementModel e WHERE e.pageId = :pageId order by e.relativeOrder, e.scriptName";
		Query query = entityManager.createQuery(jpaQL);
		query.setParameter("pageId", pageId);
		return (List<ElementModel>) query.getResultList();
	}

	public ElementModel getElementByScriptName(String elementScriptName, int pageId) {
		String jpaQL = "SELECT e FROM ElementModel e WHERE e.scriptName = :scriptName AND e.pageId = :pageId";
		Query query = entityManager.createQuery(jpaQL);
		query.setParameter("scriptName", elementScriptName);
		query.setParameter("pageId", pageId);
		return (ElementModel) query.getSingleResult();
	}

	public ElementModel getElementOfPageByRelativeOrder(int pageId, int relativeOrder) {
		String jpaQL = "SELECT e FROM ElementModel e WHERE e.pageId = :pageId AND e.relativeOrder = :relativeOrder";
		Query query = entityManager.createQuery(jpaQL);
		query.setParameter("pageId", pageId);
		query.setParameter("relativeOrder", relativeOrder);
		return (ElementModel) query.getSingleResult();
	}

	@Transactional
	public void removeElement(int id) {
		ElementModel element = getElementById(id);
		if (element != null)
			entityManager.remove(element);
	}
}
