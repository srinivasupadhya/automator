package tool.automator.common.db.daoimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import tool.automator.common.db.daoif.ElementConditionDAOIf;
import tool.automator.common.db.models.ElementConditionModel;

public class ElementConditionDAOImpl implements ElementConditionDAOIf {
	protected EntityManager entityManager;

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public ElementConditionModel getElementConditionById(int id) {
		return entityManager.find(ElementConditionModel.class, id);
	}

	@Transactional
	public void saveElementCondition(ElementConditionModel condition) {
		entityManager.merge(condition);
	}

	@SuppressWarnings("unchecked")
	public List<ElementConditionModel> getAllElementConditions() {
		return (List<ElementConditionModel>) entityManager.createQuery("from ElementConditionModel").getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<ElementConditionModel> getElementConditionsByElementRestrictionId(int elementRestrictionId) {
		String jpaQL = "SELECT c FROM ElementConditionModel c WHERE c.elementRestrictionId = :elementRestrictionId";
		Query query = entityManager.createQuery(jpaQL);
		query.setParameter("elementRestrictionId", elementRestrictionId);
		return (List<ElementConditionModel>) query.getResultList();
	}

	@Transactional
	public void removeElementCondition(int id) {
		ElementConditionModel condition = getElementConditionById(id);
		if (condition != null)
			entityManager.remove(condition);
	}
}
