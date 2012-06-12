package tool.automator.database.table;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class AbstractRepositoryCustom {
	@PersistenceContext
	protected EntityManager em;

	public void setEntityManager(EntityManager em) {
		this.em = em;
	}
}
