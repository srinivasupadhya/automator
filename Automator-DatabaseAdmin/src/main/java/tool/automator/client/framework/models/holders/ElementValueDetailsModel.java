package tool.automator.client.framework.models.holders;

import java.util.ArrayList;
import java.util.List;

import tool.automator.common.db.dao.factory.DAOFactory;
import tool.automator.common.db.daoif.*;
import tool.automator.common.db.models.*;

public class ElementValueDetailsModel {
	private ElementValueModel elementValue;
	private List<ElementValueRestrictionModel> elementValueRestrictions;
	private List<ElementValueConditionModel> elementValueConditions;

	public ElementValueDetailsModel(ElementValueModel elementValue) {
		this.elementValue = elementValue;
		ElementValueRestrictionDAOIf elementValueRestrictionDAO = DAOFactory.getInstance().getElementValueRestrictionDAO();
		elementValueRestrictions = elementValueRestrictionDAO.getElementValueRestrictionsForElementValue(elementValue.getId());
		elementValueConditions = new ArrayList<ElementValueConditionModel>();
		if (elementValueRestrictions != null) {
			ElementValueConditionDAOIf elementValueConditionDAO = DAOFactory.getInstance().getElementValueConditionDAO();
			for (ElementValueRestrictionModel currentElementValueRestriction : elementValueRestrictions)
				elementValueConditions.addAll(elementValueConditionDAO.getElementValueConditionsByElementValueRestrictionId(currentElementValueRestriction.getId()));
		}
		else
			elementValueRestrictions = new ArrayList<ElementValueRestrictionModel>();
	}

	public void setElementValue(ElementValueModel elementValue) {
		this.elementValue = elementValue;
	}

	public ElementValueModel getElementValue() {
		return elementValue;
	}

	public List<ElementValueRestrictionModel> getElementValueRestrictions() {
		return elementValueRestrictions;
	}

	public void setElementValueRestrictions(List<ElementValueRestrictionModel> elementValueRestrictions) {
		this.elementValueRestrictions = elementValueRestrictions;
	}

	public List<ElementValueConditionModel> getElementValueConditions() {
		return elementValueConditions;
	}

	public void setElementValueConditions(List<ElementValueConditionModel> elementValueConditions) {
		this.elementValueConditions = elementValueConditions;
	}

	public String toString() {
		return elementValue.getScriptValue() + " - " + elementValueRestrictions.toString();
	}
}
