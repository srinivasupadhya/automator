package tool.automator.database.table.project;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class ProjectServiceImpl implements ProjectService {
	@Autowired
	private ProjectRepository projectRepository;

	public ProjectDTO getProjectById(Long id) {
		return projectRepository.findOne(id);
	}

	@Transactional
	public void saveProject(ProjectDTO project) {
		projectRepository.save(project);
	}

	public List<ProjectDTO> getAllProjects() {
		return (List<ProjectDTO>) projectRepository.findAll();
	}

	public Map<Long, String> getProjectIdNameMap() {
		return projectRepository.getProjectIdNameMap();
	}

	public ProjectDTO getProjectByName(String projectName) {
		return projectRepository.findByProjectName(projectName);
	}

	@Transactional
	public void removeProject(Long id) {
		projectRepository.delete(id);
	}
}
