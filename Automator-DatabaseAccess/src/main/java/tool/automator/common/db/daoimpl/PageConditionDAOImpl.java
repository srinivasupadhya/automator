package tool.automator.common.db.daoimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import tool.automator.common.db.daoif.PageConditionDAOIf;
import tool.automator.common.db.models.PageConditionModel;

public class PageConditionDAOImpl implements PageConditionDAOIf {
	protected EntityManager entityManager;

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public PageConditionModel getPageConditionById(int id) {
		return entityManager.find(PageConditionModel.class, id);
	}

	@Transactional
	public void savePageCondition(PageConditionModel condition) {
		entityManager.merge(condition);
	}

	@SuppressWarnings("unchecked")
	public List<PageConditionModel> getAllPageConditions() {
		return (List<PageConditionModel>) entityManager.createQuery("from PageConditionModel").getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<PageConditionModel> getPageConditionsByPageDependencyId(int pageDependencyId) {
		String jpaQL = "SELECT c FROM PageConditionModel c WHERE c.pageDependencyId = :pageDependencyId";
		Query query = entityManager.createQuery(jpaQL);
		query.setParameter("pageDependencyId", pageDependencyId);
		return (List<PageConditionModel>) query.getResultList();
	}

	@Transactional
	public void removePageCondition(int id) {
		PageConditionModel condition = getPageConditionById(id);
		if (condition != null)
			entityManager.remove(condition);
	}
}
