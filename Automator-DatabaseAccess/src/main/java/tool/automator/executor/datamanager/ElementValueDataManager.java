package tool.automator.executor.datamanager;

import java.util.HashMap;
import java.util.List;

import tool.automator.common.models.interfaces.ElementValueModelIf;

public class ElementValueDataManager {
	private List<? extends ElementValueModelIf> elementValueList;
	private HashMap<Integer, ElementValueModelIf> elementValueIdObjMap;
	private HashMap<String, ElementValueModelIf> elementValueObjMap;

	public ElementValueDataManager(List<? extends ElementValueModelIf> elementValueList) {
		this.elementValueList = elementValueList;
		elementValueIdObjMap = new HashMap<Integer, ElementValueModelIf>();
		elementValueObjMap = new HashMap<String, ElementValueModelIf>();
		for (ElementValueModelIf currentElementValue : this.elementValueList) {
			elementValueIdObjMap.put(currentElementValue.getId(), currentElementValue);
			elementValueObjMap.put(currentElementValue.getScriptValue() + "_" + currentElementValue.getElementId(),
					currentElementValue);
		}
	}

	public ElementValueModelIf getElementValueById(int elementId) {
		return elementValueIdObjMap.get(elementId);
	}

	public ElementValueModelIf getElementValueObj(String elementValue, int elementId) {
		return elementValueObjMap.get(elementValue + "_" + elementId);
	}
}
