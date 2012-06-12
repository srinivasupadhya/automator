package tool.automator.database.table.elementvaluecondition;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ElementValueConditionRepository extends CrudRepository<ElementValueConditionDTO, Long>, ElementValueConditionRepositoryCustom {
	public List<ElementValueConditionDTO> findByElementValueRestrictionId(Long elementValueRestrictionId);
}
