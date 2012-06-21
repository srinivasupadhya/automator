package tool.automator.executor.datamanager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tool.automator.common.models.ProjectModelIf;

public class ProjectDataManager {
	private List<? extends ProjectModelIf> projectList;
	private Map<Long, ProjectModelIf> projectIdObjMap;
	private Map<String, ProjectModelIf> projectNameObjMap;

	public ProjectDataManager(List<? extends ProjectModelIf> projectList) {
		this.projectList = projectList;
		projectIdObjMap = new HashMap<Long, ProjectModelIf>();
		projectNameObjMap = new HashMap<String, ProjectModelIf>();
		
		for (ProjectModelIf currentProject : this.projectList) {
			projectIdObjMap.put(currentProject.getId(), currentProject);
			projectNameObjMap.put(currentProject.getProjectName(), currentProject);
		}
	}

	public ProjectModelIf getProjectById(int projectId) {
		return projectIdObjMap.get(projectId);
	}

	public ProjectModelIf getProjectByName(String projectName) {
		return projectNameObjMap.get(projectName);
	}
}
