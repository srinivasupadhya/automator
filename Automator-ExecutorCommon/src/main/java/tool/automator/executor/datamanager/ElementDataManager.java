package tool.automator.executor.datamanager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tool.automator.common.models.ElementModelIf;

public class ElementDataManager {
	private List<? extends ElementModelIf> elementList;
	private Map<Long, ElementModelIf> elementIdObjMap;
	private Map<String, ElementModelIf> elementNameObjMap;

	public ElementDataManager(List<? extends ElementModelIf> elementList) {
		this.elementList = elementList;
		elementIdObjMap = new HashMap<Long, ElementModelIf>();
		elementNameObjMap = new HashMap<String, ElementModelIf>();

		for (ElementModelIf currentElement : this.elementList) {
			elementIdObjMap.put(currentElement.getId(), currentElement);
			elementNameObjMap.put(currentElement.getScriptName() + "_" + currentElement.getPageId(), currentElement);
		}
	}

	public ElementModelIf getElementById(int elementId) {
		return elementIdObjMap.get(elementId);
	}

	public ElementModelIf getElementByName(String elementName, Long pageId) {
		return elementNameObjMap.get(elementName + "_" + pageId);
	}
}
