package tool.automator.database.table.elementcondition;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ElementConditionRepository extends CrudRepository<ElementConditionDTO, Long>, ElementConditionRepositoryCustom {
	public List<ElementConditionDTO> findByElementRestrictionId(Long elementRestrictionId);
}
