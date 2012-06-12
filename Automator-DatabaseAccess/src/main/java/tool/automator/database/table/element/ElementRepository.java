package tool.automator.database.table.element;

import org.springframework.data.repository.CrudRepository;

public interface ElementRepository extends CrudRepository<ElementDTO, Long>, ElementRepositoryCustom {
	public ElementDTO findByScriptNameAndPageId(String elementScriptName, Long pageId);

	public ElementDTO findByPageIdAndRelativeOrder(Long pageId, int relativeOrder);
}
