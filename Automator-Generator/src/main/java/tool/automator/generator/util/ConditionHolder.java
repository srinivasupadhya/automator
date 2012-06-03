package tool.automator.generator.util;

import tool.automator.common.db.models.ElementModel;
import tool.automator.common.db.models.ElementValueModel;
import tool.automator.common.db.models.UIPageModel;
import tool.automator.common.models.interfaces.ConditionIf;

public class ConditionHolder {
	private UIPageModel page;
	private ElementModel element;
	private ElementValueModel elementValue;

	public ConditionHolder(UIPageModel page, ElementModel element, ElementValueModel elementValue) {
		super();
		this.page = page;
		this.element = element;
		this.elementValue = elementValue;
	}

	public void setPage(UIPageModel page) {
		this.page = page;
	}

	public UIPageModel getPage() {
		return page;
	}

	public ElementModel getElement() {
		return element;
	}

	public void setElement(ElementModel element) {
		this.element = element;
	}

	public ElementValueModel getElementValue() {
		return elementValue;
	}

	public void setElementValue(ElementValueModel elementValue) {
		this.elementValue = elementValue;
	}

	public boolean matchesCondition(ConditionIf condition) {
		if (condition.getPageId() == page.getId() && condition.getElementId() == element.getId() && condition.getElementValueId() == elementValue.getId())
			return true;

		return false;
	}
}
