package tool.automator.database.table.elementvalue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.Query;

import tool.automator.database.table.AbstractRepositoryCustom;

public class ElementValueRepositoryImpl extends AbstractRepositoryCustom implements ElementValueRepositoryCustom {
	@SuppressWarnings("unchecked")
	public List<ElementValueDTO> getAllElementValuesByScriptValue() {
		return (List<ElementValueDTO>) em.createQuery("SELECT ev FROM ElementValueDTO ev order by ev.scriptValue").getResultList();
	}

	public HashMap<Long, String> getElementValueIdNameMap() {
		HashMap<Long, String> elementValueIdNameMap = new HashMap<Long, String>();
		List<ElementValueDTO> elementValueList = getAllElementValuesByScriptValue();
		for (ElementValueDTO currentElementValue : elementValueList)
			elementValueIdNameMap.put(currentElementValue.getId(), currentElementValue.getScriptValue());
		return elementValueIdNameMap;
	}

	@SuppressWarnings("unchecked")
	public List<String> getFilteredElementValues(Long elementId, String inputParam) {
		String jpaQL = "SELECT ev FROM ElementValueDTO ev WHERE ev.elementId = :elementId";
		if (inputParam != null && !inputParam.isEmpty())
			jpaQL += " AND ev.scriptValue like '%" + inputParam + "%'";
		Query query = em.createQuery(jpaQL);
		query.setParameter("elementId", elementId);

		List<ElementValueDTO> elementValueList = (List<ElementValueDTO>) query.getResultList();
		List<String> valuesList = new ArrayList<String>();
		for (ElementValueDTO currentElementValue : elementValueList)
			valuesList.add(currentElementValue.getScriptValue());
		return valuesList;
	}
}
