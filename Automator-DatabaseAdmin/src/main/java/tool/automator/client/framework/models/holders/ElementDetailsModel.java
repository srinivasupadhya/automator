package tool.automator.client.framework.models.holders;

import java.util.ArrayList;
import java.util.List;

import tool.automator.common.db.dao.factory.DAOFactory;
import tool.automator.common.db.daoif.*;
import tool.automator.common.db.models.*;

public class ElementDetailsModel {
	private ElementModel element;
	private List<ElementRestrictionModel> elementRestrictions;
	private List<ElementConditionModel> elementConditions;
	private List<ElementValueDetailsModel> elementValueDetailsList;

	public ElementDetailsModel(ElementModel element) {
		this.element = element;
		ElementRestrictionDAOIf elementRestrictionDAO = DAOFactory.getInstance().getElementRestrictionDAO();
		elementRestrictions = elementRestrictionDAO.getElementRestrictionsForElement(element.getId());
		elementConditions = new ArrayList<ElementConditionModel>();
		if (elementRestrictions != null) {
			ElementConditionDAOIf elementConditionDAO = DAOFactory.getInstance().getElementConditionDAO();
			for (ElementRestrictionModel currentElementRestriction : elementRestrictions)
				elementConditions.addAll(elementConditionDAO.getElementConditionsByElementRestrictionId(currentElementRestriction.getId()));
		}
		else
			elementRestrictions = new ArrayList<ElementRestrictionModel>();
		ElementValueDAOIf elementValueDAO = DAOFactory.getInstance().getElementValueDAO();
		List<ElementValueModel> elementValueList = elementValueDAO.getAllElementValuesOfElement(element.getId());
		elementValueDetailsList = new ArrayList<ElementValueDetailsModel>();
		for (int i = 0; i < elementValueList.size(); i++) {
			elementValueDetailsList.add(new ElementValueDetailsModel(elementValueList.get(i)));
		}
	}

	public ElementModel getElement() {
		return element;
	}

	public void setElement(ElementModel element) {
		this.element = element;
	}

	public List<ElementRestrictionModel> getElementRestrictions() {
		return elementRestrictions;
	}

	public void setElementRestrictions(List<ElementRestrictionModel> elementRestrictions) {
		this.elementRestrictions = elementRestrictions;
	}

	public List<ElementConditionModel> getElementConditions() {
		return elementConditions;
	}

	public void setElementConditions(List<ElementConditionModel> elementConditions) {
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
