package tool.automator.common.db.daoif;

import java.util.List;

import tool.automator.common.db.models.ElementRestrictionModel;

public interface ElementRestrictionDAOIf {

	public ElementRestrictionModel getElementRestrictionById(int id);

	public void saveElementRestriction(ElementRestrictionModel elementDependency);

	public List<ElementRestrictionModel> getAllElementRestrictions();

	public List<ElementRestrictionModel> getElementRestrictionsForElement(int elementId);

	public void removeElementRestriction(int id);
}
