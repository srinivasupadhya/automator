package tool.automator.database.table.project;

import java.util.List;
import java.util.Map;

public interface ProjectRepositoryCustom {
	public List<ProjectDTO> getAllProjectsByProjectName();

	public Map<Long, String> getProjectIdNameMap();
}
