package tool.automator.database.table.project;

import java.util.List;
import java.util.Map;

public interface ProjectService {
	public ProjectDTO getProjectById(Long id);

	public void saveProject(ProjectDTO project);

	public List<ProjectDTO> getAllProjects();

	public Map<Long, String> getProjectIdNameMap();

	public ProjectDTO getProjectByName(String projectName);

	public void removeProject(Long id);
}
