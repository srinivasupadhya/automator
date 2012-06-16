package tool.automator.generator.util;

import tool.automator.database.table.ConditionIf;
import tool.automator.database.table.element.ElementDTO;
import tool.automator.database.table.elementvalue.ElementValueDTO;
import tool.automator.database.table.uipage.UIPageDTO;

public class ConditionHolder {
	private UIPageDTO page;
	private ElementDTO element;
	private ElementValueDTO elementValue;

	public ConditionHolder(UIPageDTO page, ElementDTO element, ElementValueDTO elementValue) {
		super();
		this.page = page;
		this.element = element;
		this.elementValue = elementValue;
	}

	public void setPage(UIPageDTO page) {
		this.page = page;
	}

	public UIPageDTO getPage() {
		return page;
	}

	public ElementDTO getElement() {
		return element;
	}

	public void setElement(ElementDTO element) {
		this.element = element;
	}

	public ElementValueDTO getElementValue() {
		return elementValue;
	}

	public void setElementValue(ElementValueDTO elementValue) {
		this.elementValue = elementValue;
	}

	public boolean equals(Object that) {
		if (that == null || !(that instanceof ConditionIf))
			return false;
		if (this == that)
			return true;

		ConditionIf condition = (ConditionIf) that;
		if (condition.getPageId().equals(page.getId()) && condition.getElementId().equals(element.getId()) && condition.getElementValueId().equals(elementValue.getId()))
			return true;

		return false;
	}
}
