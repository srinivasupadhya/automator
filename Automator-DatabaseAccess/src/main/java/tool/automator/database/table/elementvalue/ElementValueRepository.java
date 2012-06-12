package tool.automator.database.table.elementvalue;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ElementValueRepository extends CrudRepository<ElementValueDTO, Long>, ElementValueRepositoryCustom {
	public ElementValueDTO findByScriptValueAndElementId(String scriptValue, Long elementId);

	public List<ElementValueDTO> findByElementId(Long elementId);
}
