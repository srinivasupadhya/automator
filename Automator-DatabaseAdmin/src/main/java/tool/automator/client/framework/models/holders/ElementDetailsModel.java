package tool.automator.client.framework.models.holders;

import java.util.ArrayList;
import java.util.List;

import tool.automator.database.factory.DAOFactory;
import tool.automator.database.table.element.ElementDTO;
import tool.automator.database.table.elementcondition.ElementConditionDTO;
import tool.automator.database.table.elementcondition.ElementConditionService;
import tool.automator.database.table.elementrestriction.ElementRestrictionDTO;
import tool.automator.database.table.elementrestriction.ElementRestrictionService;
import tool.automator.database.table.elementvalue.ElementValueDTO;
import tool.automator.database.table.elementvalue.ElementValueService;

public class ElementDetailsModel {
	private ElementDTO element;
	private List<ElementRestrictionDTO> elementRestrictions;
	private List<ElementConditionDTO> elementConditions;
	private List<ElementValueDetailsModel> elementValueDetailsList;

	public ElementDetailsModel(ElementDTO element) {
		this.element = element;
		ElementRestrictionService elementRestrictionDAO = DAOFactory.getInstance().getElementRestrictionService();
		elementRestrictions = elementRestrictionDAO.getElementRestrictionsForElement(element.getId());
		elementConditions = new ArrayList<ElementConditionDTO>();
		if (elementRestrictions != null) {
			ElementConditionService elementConditionDAO = DAOFactory.getInstance().getElementConditionService();
			for (ElementRestrictionDTO currentElementRestriction : elementRestrictions)
				elementConditions.addAll(elementConditionDAO.getElementConditionsByElementRestrictionId(currentElementRestriction.getId()));
		}
		else
			elementRestrictions = new ArrayList<ElementRestrictionDTO>();
		ElementValueService elementValueDAO = DAOFactory.getInstance().getElementValueService();
		List<ElementValueDTO> elementValueList = elementValueDAO.getAllElementValuesOfElement(element.getId());
		elementValueDetailsList = new ArrayList<ElementValueDetailsModel>();
		for (int i = 0; i < elementValueList.size(); i++) {
			elementValueDetailsList.add(new ElementValueDetailsModel(elementValueList.get(i)));
		}
	}

	public ElementDTO getElement() {
		return element;
	}

	public void setElement(ElementDTO element) {
		this.element = element;
	}

	public List<ElementRestrictionDTO> getElementRestrictions() {
		return elementRestrictions;
	}

	public void setElementRestrictions(List<ElementRestrictionDTO> elementRestrictions) {
		this.elementRestrictions = elementRestrictions;
	}

	public List<ElementConditionDTO> getElementConditions() {
		return elementConditions;
	}

	public void setElementConditions(List<ElementConditionDTO> elementConditions) {
		this.elementConditions = elementConditions;
	}

	public List<ElementValueDetailsModel> getElementValueDetailsList() {
		return elementValueDetailsList;
	}

	public void setElementValueDetailsList(List<ElementValueDetailsModel> elementValueDetailsList) {
		this.elementValueDetailsList = elementValueDetailsList;
	}

	public String toString() {
		return element.getScriptName() + " - " + elementRestrictions.toString() + " - " + " - " + elementValueDetailsList;
	}
}
