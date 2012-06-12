package tool.automator.database.table.pagecondition;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface PageConditionRepository extends CrudRepository<PageConditionDTO, Long>, PageConditionRepositoryCustom {
	public List<PageConditionDTO> findByPageDependencyId(Long pageDependencyId);
}
