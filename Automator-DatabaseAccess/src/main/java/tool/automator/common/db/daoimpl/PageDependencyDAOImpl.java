package tool.automator.common.db.daoimpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import tool.automator.common.db.daoif.PageDependencyDAOIf;
import tool.automator.common.db.models.PageDependencyModel;

public class PageDependencyDAOImpl implements PageDependencyDAOIf {
	protected EntityManager entityManager;

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public PageDependencyModel getPageDependencyById(int id) {
		return entityManager.find(PageDependencyModel.class, id);
	}

	@Transactional
	public void savePageDependency(PageDependencyModel pageDependency) {
		entityManager.merge(pageDependency);
	}

	@SuppressWarnings("unchecked")
	public List<PageDependencyModel> getAllPageDependecies() {
		return (List<PageDependencyModel>) entityManager.createQuery("from PageDependencyModel").getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<PageDependencyModel> getPageDependencies(int sourcePageId, int destinationPageId) {
		String jpaQL = "SELECT pd FROM PageDependencyModel pd WHERE pd.sourcePageId = :sourcePageId AND pd.destinationPageId = :destinationPageId";
		Query query = entityManager.createQuery(jpaQL);
		query.setParameter("sourcePageId", sourcePageId);
		query.setParameter("destinationPageId", destinationPageId);
		return (List<PageDependencyModel>) query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<PageDependencyModel> getPossibleNextPages(int pageId) {
		String jpaQL = "SELECT pd FROM PageDependencyModel pd WHERE pd.sourcePageId = :sourcePageId";
		Query query = entityManager.createQuery(jpaQL);
		query.setParameter("sourcePageId", pageId);
		return (List<PageDependencyModel>) query.getResultList();
	}

	@Transactional
	public void removePageDependency(int id) {
		PageDependencyModel pageDependency = getPageDependencyById(id);
		if (pageDependency != null)
			entityManager.remove(pageDependency);
	}
}
