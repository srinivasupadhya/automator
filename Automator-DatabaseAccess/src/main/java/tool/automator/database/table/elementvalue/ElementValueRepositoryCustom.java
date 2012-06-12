package tool.automator.database.table.elementvalue;

import java.util.HashMap;
import java.util.List;

public interface ElementValueRepositoryCustom {
	public List<ElementValueDTO> getAllElementValuesByScriptValue();

	public HashMap<Long, String> getElementValueIdNameMap();

	public List<String> getFilteredElementValues(Long elementId, String inputParam);
}
