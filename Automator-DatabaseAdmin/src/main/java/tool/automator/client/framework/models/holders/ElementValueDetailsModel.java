package tool.automator.client.framework.models.holders;

import java.util.ArrayList;
import java.util.List;

import tool.automator.database.factory.DAOFactory;
import tool.automator.database.table.elementvalue.ElementValueDTO;
import tool.automator.database.table.elementvaluecondition.ElementValueConditionDTO;
import tool.automator.database.table.elementvaluecondition.ElementValueConditionService;
import tool.automator.database.table.elementvaluerestriction.ElementValueRestrictionDTO;
import tool.automator.database.table.elementvaluerestriction.ElementValueRestrictionService;

public class ElementValueDetailsModel {
	private ElementValueDTO elementValue;
	private List<ElementValueRestrictionDTO> elementValueRestrictions;
	private List<ElementValueConditionDTO> elementValueConditions;

	public ElementValueDetailsModel(ElementValueDTO elementValue) {
		this.elementValue = elementValue;
		ElementValueRestrictionService elementValueRestrictionDAO = DAOFactory.getInstance().getElementValueRestrictionService();
		elementValueRestrictions = elementValueRestrictionDAO.getElementValueRestrictionsForElementValue(elementValue.getId());
		elementValueConditions = new ArrayList<ElementValueConditionDTO>();
		if (elementValueRestrictions != null) {
			ElementValueConditionService elementValueConditionDAO = DAOFactory.getInstance().getElementValueConditionService();
			for (ElementValueRestrictionDTO currentElementValueRestriction : elementValueRestrictions)
				elementValueConditions.addAll(elementValueConditionDAO.getElementValueConditionsByElementValueRestrictionId(currentElementValueRestriction.getId()));
		}
		else
			elementValueRestrictions = new ArrayList<ElementValueRestrictionDTO>();
	}

	public void setElementValue(ElementValueDTO elementValue) {
		this.elementValue = elementValue;
	}

	public ElementValueDTO getElementValue() {
		return elementValue;
	}

	public List<ElementValueRestrictionDTO> getElementValueRestrictions() {
		return elementValueRestrictions;
	}

	public void setElementValueRestrictions(List<ElementValueRestrictionDTO> elementValueRestrictions) {
		this.elementValueRestrictions = elementValueRestrictions;
	}

	public List<ElementValueConditionDTO> getElementValueConditions() {
		return elementValueConditions;
	}

	public void setElementValueConditions(List<ElementValueConditionDTO> elementValueConditions) {
		this.elementValueConditions = elementValueConditions;
	}

	public String toString() {
		return elementValue.getScriptValue() + " - " + elementValueRestrictions.toString();
	}
}
