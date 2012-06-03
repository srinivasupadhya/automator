package tool.automator.executor.util;

import tool.automator.common.models.interfaces.ElementModelIf;
import tool.automator.common.models.interfaces.ElementValueModelIf;

public class ElementNValuePair {
	private ElementModelIf element;
	private ElementValueModelIf elementValue;

	public ElementNValuePair(ElementModelIf element, ElementValueModelIf elementValue) {
		super();
		this.element = element;
		this.elementValue = elementValue;
	}

	public ElementModelIf getElement() {
		return element;
	}

	public void setElement(ElementModelIf element) {
		this.element = element;
	}

	public ElementValueModelIf getElementValue() {
		return elementValue;
	}

	public void setElementValue(ElementValueModelIf elementValue) {
		this.elementValue = elementValue;
	}
}
