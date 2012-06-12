package tool.automator.database.table.uipage;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface UIPageRepository extends CrudRepository<UIPageDTO, Long>, UIPageRepositoryCustom {
	public UIPageDTO findByPageNameAndProjectId(String pageName, Long projectId);

	public List<UIPageDTO> findByStartPageAndProjectId(boolean startPage, Long projectId);
}
