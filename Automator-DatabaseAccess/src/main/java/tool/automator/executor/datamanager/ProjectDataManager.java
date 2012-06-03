package tool.automator.executor.datamanager;

import java.util.HashMap;
import java.util.List;

import tool.automator.common.models.interfaces.ProjectModelIf;

public class ProjectDataManager {
	private List<? extends ProjectModelIf> projectList;
	private HashMap<Integer, ProjectModelIf> projectIdObjMap;
	private HashMap<String, ProjectModelIf> projectNameObjMap;

	public ProjectDataManager(List<? extends ProjectModelIf> projectList) {
		this.projectList = projectList;
		projectIdObjMap = new HashMap<Integer, ProjectModelIf>();
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
