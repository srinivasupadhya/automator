package tool.automator.common.db.daoif;

import java.util.HashMap;
import java.util.List;

import tool.automator.common.db.models.ProjectModel;

public interface ProjectDAOIf {
	public ProjectModel getProjectById(int id);

	public void saveProject(ProjectModel project);

	public List<ProjectModel> getAllProjects();
	
	public HashMap<Integer, String> getProjectIdNameMap();

	public ProjectModel getProjectByName(String projectName);

	public void removeProject(int id);
}
