package tool.automator.database.test;

import java.util.List;

import tool.automator.database.factory.DAOFactory;
import tool.automator.database.table.element.ElementDTO;
import tool.automator.database.table.element.ElementService;
import tool.automator.database.table.elementcondition.ElementConditionDTO;
import tool.automator.database.table.elementcondition.ElementConditionService;
import tool.automator.database.table.elementrestriction.ElementRestrictionDTO;
import tool.automator.database.table.elementrestriction.ElementRestrictionService;
import tool.automator.database.table.elementvalue.ElementValueDTO;
import tool.automator.database.table.elementvalue.ElementValueService;
import tool.automator.database.table.elementvaluecondition.ElementValueConditionDTO;
import tool.automator.database.table.elementvaluecondition.ElementValueConditionService;
import tool.automator.database.table.elementvaluerestriction.ElementValueRestrictionDTO;
import tool.automator.database.table.elementvaluerestriction.ElementValueRestrictionService;
import tool.automator.database.table.pagecondition.PageConditionDTO;
import tool.automator.database.table.pagecondition.PageConditionService;
import tool.automator.database.table.pagedependency.PageDependencyDTO;
import tool.automator.database.table.pagedependency.PageDependencyService;
import tool.automator.database.table.project.ProjectDTO;
import tool.automator.database.table.project.ProjectService;
import tool.automator.database.table.uipage.UIPageDTO;
import tool.automator.database.table.uipage.UIPageService;

public class CheckData {
	public static void main(String[] args) {
		ProjectService projectService = DAOFactory.getInstance().getProjectService();
		UIPageService uiPageService = DAOFactory.getInstance().getUIPageService();
		ElementService elementDAO = DAOFactory.getInstance().getElementService();
		ElementValueService elementValueDAO = DAOFactory.getInstance().getElementValueService();

		PageDependencyService pageDependencyDAO = DAOFactory.getInstance().getPageDependencyService();
		PageConditionService pageConditionDAO = DAOFactory.getInstance().getPageConditionService();

		ElementRestrictionService elementRestrictionDAO = DAOFactory.getInstance().getElementRestrictionService();
		ElementConditionService elementConditionDAO = DAOFactory.getInstance().getElementConditionService();

		ElementValueRestrictionService elementValueRestrictionDAO = DAOFactory.getInstance().getElementValueRestrictionService();
		ElementValueConditionService elementValueConditionDAO = DAOFactory.getInstance().getElementValueConditionService();

		ProjectDTO project1 = projectService.getProjectByName("Google");
		System.out.println(project1);

		UIPageDTO uiPage1 = uiPageService.getPageByName("SEARCH-PAGE", project1.getId());
		UIPageDTO uiPage2 = uiPageService.getPageByName("RESULTS-PAGE", project1.getId());
		System.out.println(uiPage1 + "\n" + uiPage2);

		ElementDTO element1 = elementDAO.getElementOfPageByRelativeOrder(uiPage1.getId(), 1);
		System.out.println(element1);

		List<ElementValueDTO> elementValues = elementValueDAO.getAllElementValuesOfElement(element1.getId());
		System.out.println(elementValues);

		List<PageDependencyDTO> pageDependencies = pageDependencyDAO.getPossibleNextPages(uiPage1.getId());
		System.out.println(pageDependencies);

		List<PageConditionDTO> pageConditions = pageConditionDAO.getPageConditionsByPageDependencyId(pageDependencies.get(0).getId());
		System.out.println(pageConditions);

		List<ElementRestrictionDTO> elementRestrictions = elementRestrictionDAO.getElementRestrictionsForElement(element1.getId());
		System.out.println(elementRestrictions);

		List<ElementConditionDTO> elementConditions = elementConditionDAO.getElementConditionsByElementRestrictionId(elementRestrictions.get(0).getId());
		System.out.println(elementConditions);

		List<ElementValueRestrictionDTO> elementValueRestrictions = elementValueRestrictionDAO.getElementValueRestrictionsForElementValue(elementValues.get(0).getId());
		System.out.println(elementValueRestrictions);

		List<ElementValueConditionDTO> elementValueConditions = elementValueConditionDAO.getElementValueConditionsByElementValueRestrictionId(elementValueRestrictions.get(0)
				.getId());
		System.out.println(elementValueConditions);
	}
}
