package tool.automator.database.table.project;

import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository extends CrudRepository<ProjectDTO, Long>, ProjectRepositoryCustom {
	public ProjectDTO findByProjectName(String projectName);
}
