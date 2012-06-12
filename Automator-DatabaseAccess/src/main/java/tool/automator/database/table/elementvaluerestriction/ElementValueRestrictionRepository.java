package tool.automator.database.table.elementvaluerestriction;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ElementValueRestrictionRepository extends CrudRepository<ElementValueRestrictionDTO, Long>, ElementValueRestrictionRepositoryCustom {
	public List<ElementValueRestrictionDTO> findByElementValueId(Long elementValueId);
}
