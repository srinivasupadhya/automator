package tool.automator.database.table.elementvaluerestriction;

import java.util.List;

public interface ElementValueRestrictionService {
	public ElementValueRestrictionDTO getElementValueRestrictionById(Long id);

	public void saveElementValueRestriction(ElementValueRestrictionDTO elementValueDependency);

	public List<ElementValueRestrictionDTO> getAllElementValueRestrictions();

	public List<ElementValueRestrictionDTO> getElementValueRestrictionsForElementValue(Long elementValueId);

	public void removeElementValueRestriction(Long id);
}
