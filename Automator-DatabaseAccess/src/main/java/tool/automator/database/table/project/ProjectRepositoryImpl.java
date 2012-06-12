package tool.automator.database.table.project;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tool.automator.database.table.AbstractRepositoryCustom;

public class ProjectRepositoryImpl extends AbstractRepositoryCustom implements ProjectRepositoryCustom {
	@SuppressWarnings("unchecked")
	public List<ProjectDTO> getAllProjectsByProjectName() {
		return (List<ProjectDTO>) em.createQuery("SELECT p FROM ProjectModel p order by p.projectName").getResultList();
	}

	public Map<Long, String> getProjectIdNameMap() {
		Map<Long, String> projectIdNameMap = new HashMap<Long, String>();
		List<ProjectDTO> projectList = getAllProjectsByProjectName();
		for (ProjectDTO currentProject : projectList)
			projectIdNameMap.put(currentProject.getId(), currentProject.getProjectName());
		return projectIdNameMap;
	}
}
