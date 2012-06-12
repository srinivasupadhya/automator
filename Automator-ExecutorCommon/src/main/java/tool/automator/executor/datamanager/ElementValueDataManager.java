package tool.automator.executor.datamanager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tool.automator.database.table.elementvalue.ElementValueModelIf;

public class ElementValueDataManager {
	private List<? extends ElementValueModelIf> elementValueList;
	private Map<Long, ElementValueModelIf> elementValueIdObjMap;
	private Map<String, ElementValueModelIf> elementValueObjMap;

	public ElementValueDataManager(List<? extends ElementValueModelIf> elementValueList) {
		this.elementValueList = elementValueList;
		elementValueIdObjMap = new HashMap<Long, ElementValueModelIf>();
		elementValueObjMap = new HashMap<String, ElementValueModelIf>();

		for (ElementValueModelIf currentElementValue : this.elementValueList) {
			elementValueIdObjMap.put(currentElementValue.getId(), currentElementValue);
			elementValueObjMap.put(currentElementValue.getScriptValue() + "_" + currentElementValue.getElementId(), currentElementValue);
		}
	}

	public ElementValueModelIf getElementValueById(int elementId) {
		return elementValueIdObjMap.get(elementId);
	}

	public ElementValueModelIf getElementValueObj(String elementValue, Long elementId) {
		return elementValueObjMap.get(elementValue + "_" + elementId);
	}
}
