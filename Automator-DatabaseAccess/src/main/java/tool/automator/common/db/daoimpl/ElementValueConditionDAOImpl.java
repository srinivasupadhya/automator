package tool.automator.common.db.daoimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import tool.automator.common.db.daoif.ElementValueConditionDAOIf;
import tool.automator.common.db.models.ElementValueConditionModel;

public class ElementValueConditionDAOImpl implements ElementValueConditionDAOIf {
	protected EntityManager entityManager;

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public ElementValueConditionModel getElementValueConditionById(int id) {
		return entityManager.find(ElementValueConditionModel.class, id);
	}

	@Transactional
	public void saveElementValueCondition(ElementValueConditionModel condition) {
		entityManager.merge(condition);
	}

	@SuppressWarnings("unchecked")
	public List<ElementValueConditionModel> getAllElementValueConditions() {
		return (List<ElementValueConditionModel>) entityManager.createQuery("from ElementValueConditionModel").getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<ElementValueConditionModel> getElementValueConditionsByElementValueRestrictionId(int elementValueRestrictionId) {
		String jpaQL = "SELECT c FROM ElementValueConditionModel c WHERE c.elementValueRestrictionId = :elementValueRestrictionId";
		Query query = entityManager.createQuery(jpaQL);
		query.setParameter("elementValueRestrictionId", elementValueRestrictionId);
		return (List<ElementValueConditionModel>) query.getResultList();
	}

	@Transactional
	public void removeElementValueCondition(int id) {
		ElementValueConditionModel condition = getElementValueConditionById(id);
		if (condition != null)
			entityManager.remove(condition);
	}
}
