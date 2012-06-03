package tool.automator.common.db.daoimpl;

import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import tool.automator.common.db.daoif.ProjectDAOIf;
import tool.automator.common.db.models.ProjectModel;

public class ProjectDAOImpl implements ProjectDAOIf {
	protected EntityManager entityManager;

	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public ProjectModel getProjectById(int id) {
		return entityManager.find(ProjectModel.class, id);
	}

	@Transactional
	public void saveProject(ProjectModel project) {
		entityManager.merge(project);
	}

	@SuppressWarnings("unchecked")
	public List<ProjectModel> getAllProjects() {
		return (List<ProjectModel>) entityManager.createQuery("SELECT p FROM ProjectModel p order by p.projectName").getResultList();
	}

	public HashMap<Integer, String> getProjectIdNameMap() {
		HashMap<Integer, String> projectIdNameMap = new HashMap<Integer, String>();
		List<ProjectModel> projectList = getAllProjects();
		for (ProjectModel currentProject : projectList)
			projectIdNameMap.put(currentProject.getId(), currentProject.getProjectName());
		return projectIdNameMap;
	}

	public ProjectModel getProjectByName(String projectName) {
		String jpaQL = "SELECT p FROM ProjectModel p WHERE p.projectName = :projectName";
		Query query = entityManager.createQuery(jpaQL);
		query.setParameter("projectName", projectName);
		return (ProjectModel) query.getSingleResult();
	}

	@Transactional
	public void removeProject(int id) {
		ProjectModel project = getProjectById(id);
		if (project != null)
			entityManager.remove(project);
	}
}
