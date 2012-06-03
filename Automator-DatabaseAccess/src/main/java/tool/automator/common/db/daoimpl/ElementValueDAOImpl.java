package tool.automator.common.db.daoimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import tool.automator.common.db.daoif.ElementValueDAOIf;
import tool.automator.common.db.models.ElementValueModel;

public class ElementValueDAOImpl implements ElementValueDAOIf {
	protected EntityManager entityManager;

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public ElementValueModel getElementValueById(int id) {
		return entityManager.find(ElementValueModel.class, id);
	}

	@Transactional
	public void saveElementValue(ElementValueModel elementValue) {
		entityManager.merge(elementValue);
	}

	@SuppressWarnings("unchecked")
	public List<ElementValueModel> getAllElementValues() {
		return (List<ElementValueModel>) entityManager.createQuery("SELECT ev FROM ElementValueModel ev order by ev.scriptValue").getResultList();
	}

	public HashMap<Integer, String> getElementValueIdNameMap() {
		HashMap<Integer, String> elementValueIdNameMap = new HashMap<Integer, String>();
		List<ElementValueModel> elementValueList = getAllElementValues();
		for (ElementValueModel currentElementValue : elementValueList)
			elementValueIdNameMap.put(currentElementValue.getId(), currentElementValue.getScriptValue());
		return elementValueIdNameMap;
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getFilteredElementValues(int elementId, String inputParam) {
		String jpaQL = "SELECT ev FROM ElementValueModel ev WHERE ev.elementId = :elementId";
		if(inputParam != null && !inputParam.isEmpty())
			jpaQL += " AND ev.scriptValue like '%" + inputParam + "%'";
		Query query = entityManager.createQuery(jpaQL);
		query.setParameter("elementId", elementId);
		List<ElementValueModel> elementValueList = (List<ElementValueModel>) query.getResultList();
		List<String> valuesList = new ArrayList<String>();
		for (ElementValueModel currentElementValue : elementValueList)
			valuesList.add(currentElementValue.getScriptValue());
		return valuesList;
	}

	public ElementValueModel getElementValueOfElement(String value, int elementId) {
		String jpaQL = "SELECT ev FROM ElementValueModel ev WHERE ev.scriptValue = :scriptValue AND ev.elementId = :elementId";
		Query query = entityManager.createQuery(jpaQL);
		query.setParameter("scriptValue", value);
		query.setParameter("elementId", elementId);
		return (ElementValueModel) query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public List<ElementValueModel> getAllElementValuesOfElement(int elementId) {
		String jpaQL = "SELECT ev FROM ElementValueModel ev WHERE ev.elementId = :elementId order by ev.scriptValue";
		Query query = entityManager.createQuery(jpaQL);
		query.setParameter("elementId", elementId);
		return (List<ElementValueModel>) query.getResultList();
	}

	@Transactional
	public void removeElementValue(int id) {
		ElementValueModel elementValue = getElementValueById(id);
		if (elementValue != null)
			entityManager.remove(elementValue);
	}
}
