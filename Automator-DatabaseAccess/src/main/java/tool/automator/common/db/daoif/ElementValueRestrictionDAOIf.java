package tool.automator.common.db.daoif;

import java.util.List;

import tool.automator.common.db.models.ElementValueRestrictionModel;

public interface ElementValueRestrictionDAOIf {

	public ElementValueRestrictionModel getElementValueRestrictionById(int id);

	public void saveElementValueRestriction(ElementValueRestrictionModel elementValueDependency);

	public List<ElementValueRestrictionModel> getAllElementValueRestrictions();

	public List<ElementValueRestrictionModel> getElementValueRestrictionsForElementValue(int elementValueId);

	public void removeElementValueRestriction(int id);
}
