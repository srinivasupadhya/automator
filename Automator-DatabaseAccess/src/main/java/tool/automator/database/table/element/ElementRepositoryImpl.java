package tool.automator.database.table.element;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import tool.automator.database.table.AbstractRepositoryCustom;

public class ElementRepositoryImpl extends AbstractRepositoryCustom implements ElementRepositoryCustom {
	@SuppressWarnings("unchecked")
	public List<ElementDTO> getAllElementsSortedByRelativeOrder() {
		String jpaQL = "SELECT e FROM ElementModel e order by e.relativeOrder";
		Query query = em.createQuery(jpaQL);

		return (List<ElementDTO>) query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<String> getFilteredElementNames(Long pageId, String inputParam) {
		String jpaQL = "SELECT e FROM ElementModel e WHERE e.pageId = :pageId";
		if (inputParam != null && !inputParam.isEmpty())
			jpaQL += " AND e.scriptName like '%" + inputParam + "%'";
		Query query = em.createQuery(jpaQL);
		query.setParameter("pageId", pageId);

		List<ElementDTO> elementList = (List<ElementDTO>) query.getResultList();
		List<String> pageNameList = new ArrayList<String>();
		for (ElementDTO currentElement : elementList)
			pageNameList.add(currentElement.getScriptName());
		return pageNameList;
	}

	@SuppressWarnings("unchecked")
	public List<ElementDTO> getElementsOfPage(Long pageId) {
		String jpaQL = "SELECT e FROM ElementModel e WHERE e.pageId = :pageId order by e.relativeOrder, e.scriptName";
		Query query = em.createQuery(jpaQL);
		query.setParameter("pageId", pageId);

		return (List<ElementDTO>) query.getResultList();
	}
}
