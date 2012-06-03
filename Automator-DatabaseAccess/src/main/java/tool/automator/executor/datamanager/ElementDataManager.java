package tool.automator.executor.datamanager;

import java.util.HashMap;
import java.util.List;

import tool.automator.common.models.interfaces.ElementModelIf;

public class ElementDataManager {
	private List<? extends ElementModelIf> elementList;
	private HashMap<Integer, ElementModelIf> elementIdObjMap;
	private HashMap<String, ElementModelIf> elementNameObjMap;

	public ElementDataManager(List<? extends ElementModelIf> elementList) {
		this.elementList = elementList;
		elementIdObjMap = new HashMap<Integer, ElementModelIf>();
		elementNameObjMap = new HashMap<String, ElementModelIf>();
		for (ElementModelIf currentElement : this.elementList) {
			elementIdObjMap.put(currentElement.getId(), currentElement);
			elementNameObjMap.put(currentElement.getScriptName() + "_" + currentElement.getPageId(), currentElement);
		}
	}

	public ElementModelIf getElementById(int elementId) {
		return elementIdObjMap.get(elementId);
	}

	public ElementModelIf getElementByName(String elementName, int pageId) {
		return elementNameObjMap.get(elementName + "_" + pageId);
	}
}
