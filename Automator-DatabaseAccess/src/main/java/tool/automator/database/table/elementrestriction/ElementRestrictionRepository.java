package tool.automator.database.table.elementrestriction;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ElementRestrictionRepository extends CrudRepository<ElementRestrictionDTO, Long>, ElementRestrictionRepositoryCustom {
	public List<ElementRestrictionDTO> findByElementId(Long elementId);
}
