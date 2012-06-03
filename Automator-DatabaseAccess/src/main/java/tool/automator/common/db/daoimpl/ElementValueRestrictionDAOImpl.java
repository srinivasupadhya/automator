package tool.automator.common.db.daoimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import tool.automator.common.db.daoif.ElementValueRestrictionDAOIf;
import tool.automator.common.db.models.ElementValueRestrictionModel;

public class ElementValueRestrictionDAOImpl implements ElementValueRestrictionDAOIf {
	protected EntityManager entityManager;

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public ElementValueRestrictionModel getElementValueRestrictionById(int id) {
		return entityManager.find(ElementValueRestrictionModel.class, id);
	}

	@Transactional
	public void saveElementValueRestriction(ElementValueRestrictionModel elementValueDependency) {
		entityManager.merge(elementValueDependency);
	}

	@SuppressWarnings("unchecked")
	public List<ElementValueRestrictionModel> getAllElementValueRestrictions() {
		return (List<ElementValueRestrictionModel>) entityManager.createQuery("from ElementValueRestrictionModel").getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<ElementValueRestrictionModel> getElementValueRestrictionsForElementValue(int elementValueId) {
		String jpaQL = "SELECT evd FROM ElementValueRestrictionModel evd WHERE evd.elementValueId = :elementValueId";
		Query query = entityManager.createQuery(jpaQL);
		query.setParameter("elementValueId", elementValueId);
		return (List<ElementValueRestrictionModel>) query.getResultList();
	}

	@Transactional
	public void removeElementValueRestriction(int id) {
		ElementValueRestrictionModel elementValueDependency = getElementValueRestrictionById(id);
		if (elementValueDependency != null)
			entityManager.remove(elementValueDependency);
	}
}
