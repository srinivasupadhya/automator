package tool.automator.database.table.elementrestriction;

import java.util.List;

public interface ElementRestrictionService {
	public ElementRestrictionDTO getElementRestrictionById(Long id);

	public void saveElementRestriction(ElementRestrictionDTO elementDependency);

	public List<ElementRestrictionDTO> getAllElementRestrictions();

	public List<ElementRestrictionDTO> getElementRestrictionsForElement(Long elementId);

	public void removeElementRestriction(Long id);
}
