package tool.automator.database.table.pagedependency;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface PageDependencyRepository extends CrudRepository<PageDependencyDTO, Long>, PageDependencyRepositoryCustom {
	public List<PageDependencyDTO> findBySourcePageIdAndDestinationPageId(Long sourcePageId, Long destinationPageId);

	public List<PageDependencyDTO> findBySourcePageId(Long sourcePageId);
}
