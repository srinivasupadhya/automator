package tool.automator.common.db.daoimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import tool.automator.common.db.daoif.ElementRestrictionDAOIf;
import tool.automator.common.db.models.ElementRestrictionModel;

public class ElementRestrictionDAOImpl implements ElementRestrictionDAOIf {
	protected EntityManager entityManager;

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public ElementRestrictionModel getElementRestrictionById(int id) {
		return entityManager.find(ElementRestrictionModel.class, id);
	}

	@Transactional
	public void saveElementRestriction(ElementRestrictionModel elementRestriction) {
		entityManager.merge(elementRestriction);
	}

	@SuppressWarnings("unchecked")
	public List<ElementRestrictionModel> getAllElementRestrictions() {
		return (List<ElementRestrictionModel>) entityManager.createQuery("from ElementRestrictionModel")
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<ElementRestrictionModel> getElementRestrictionsForElement(int elementId) {
		String jpaQL = "SELECT ed FROM ElementRestrictionModel ed WHERE ed.elementId = :elementId";
		Query query = entityManager.createQuery(jpaQL);
		query.setParameter("elementId", elementId);
		return (List<ElementRestrictionModel>) query.getResultList();
	}

	@Transactional
	public void removeElementRestriction(int id) {
		ElementRestrictionModel elementRestriction = getElementRestrictionById(id);
		if (elementRestriction != null)
			entityManager.remove(elementRestriction);
	}
}
