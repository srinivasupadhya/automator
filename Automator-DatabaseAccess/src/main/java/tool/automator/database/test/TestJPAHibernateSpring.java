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

public class TestJPAHibernateSpring {
	public static void main(String[] args) {
		// project
		ProjectService projectService = DAOFactory.getInstance().getProjectService();

		ProjectDTO projectNew1 = new ProjectDTO("Google");
		projectService.saveProject(projectNew1);
		System.out.println(projectNew1);

		ProjectDTO project1 = projectService.getProjectByName("Google");
		System.out.println(project1);

		// page
		UIPageService uiPageService = DAOFactory.getInstance().getUIPageService();

		UIPageDTO uiPageNew1 = new UIPageDTO("SEARCH-PAGE", project1.getId(), false, 5, null, null, null, null);
		uiPageService.savePage(uiPageNew1);
		System.out.println(uiPageNew1);

		UIPageDTO uiPageNew2 = new UIPageDTO("RESULTS-PAGE", project1.getId(), false, 5, null, null, null, null);
		uiPageService.savePage(uiPageNew2);
		System.out.println(uiPageNew2);

		UIPageDTO uiPage1 = uiPageService.getPageByName("SEARCH-PAGE", project1.getId());
		UIPageDTO uiPage2 = uiPageService.getPageByName("RESULTS-PAGE", project1.getId());
		System.out.println(uiPage1 + "\n" + uiPage2);

		// element
		ElementService elementDAO = DAOFactory.getInstance().getElementService();

		ElementDTO elementNew1 = new ElementDTO("SEARCH_QUERY", uiPage1.getId(), "q", "NAME", "TEXTBOX", 1, 1, false, false);
		elementDAO.saveElement(elementNew1);
		System.out.println(elementNew1);

		ElementDTO element1 = elementDAO.getElementOfPageByRelativeOrder(uiPage1.getId(), 1);
		System.out.println(element1);

		// element-value
		ElementValueService elementValueDAO = DAOFactory.getInstance().getElementValueService();

		ElementValueDTO elementValueNew1 = new ElementValueDTO(element1.getId(), "Amadeus", "", false, false);
		elementValueDAO.saveElementValue(elementValueNew1);
		System.out.println(elementValueNew1);

		List<ElementValueDTO> elementValues = elementValueDAO.getAllElementValuesOfElement(element1.getId());
		System.out.println(elementValues);

		// page-dependency
		PageDependencyService pageDependencyDAO = DAOFactory.getInstance().getPageDependencyService();

		PageDependencyDTO pageDependencyNew1 = new PageDependencyDTO(uiPage1.getId(), uiPage2.getId());
		pageDependencyDAO.savePageDependency(pageDependencyNew1);
		System.out.println(pageDependencyNew1);

		List<PageDependencyDTO> pageDependencies = pageDependencyDAO.getPossibleNextPages(uiPage1.getId());
		System.out.println(pageDependencies);

		// page-condition
		PageConditionService pageConditionDAO = DAOFactory.getInstance().getPageConditionService();

		PageConditionDTO pageConditionNew1 = new PageConditionDTO(pageDependencies.get(0).getId(), uiPage1.getId(), element1.getId(), elementValues.get(0).getId());
		pageConditionDAO.savePageCondition(pageConditionNew1);
		System.out.println(pageConditionNew1);

		List<PageConditionDTO> pageConditions = pageConditionDAO.getPageConditionsByPageDependencyId(pageDependencies.get(0).getId());
		System.out.println(pageConditions);

		// element-restrictions
		ElementRestrictionService elementRestrictionDAO = DAOFactory.getInstance().getElementRestrictionService();

		ElementRestrictionDTO elementRestrictionNew1 = new ElementRestrictionDTO(uiPage1.getId(), element1.getId());
		elementRestrictionDAO.saveElementRestriction(elementRestrictionNew1);
		System.out.println(elementRestrictionNew1);

		List<ElementRestrictionDTO> elementRestrictions = elementRestrictionDAO.getElementRestrictionsForElement(element1.getId());
		System.out.println(elementRestrictions);

		// element-condition
		ElementConditionService elementConditionDAO = DAOFactory.getInstance().getElementConditionService();

		ElementConditionDTO elementConditionNew1 = new ElementConditionDTO(elementRestrictions.get(0).getId(), project1.getId(), uiPage1.getId(), element1.getId());
		elementConditionDAO.saveElementCondition(elementConditionNew1);
		System.out.println(elementConditionNew1);

		List<ElementConditionDTO> elementConditions = elementConditionDAO.getElementConditionsByElementRestrictionId(elementRestrictions.get(0).getId());
		System.out.println(elementConditions);

		// element-value-restrictions
		ElementValueRestrictionService elementValueRestrictionDAO = DAOFactory.getInstance().getElementValueRestrictionService();

		ElementValueRestrictionDTO elementValueRestrictionNew1 = new ElementValueRestrictionDTO(uiPage1.getId(), element1.getId(), elementValues.get(0).getId());
		elementValueRestrictionDAO.saveElementValueRestriction(elementValueRestrictionNew1);
		System.out.println(elementValueRestrictionNew1);

		List<ElementValueRestrictionDTO> elementValueRestrictions = elementValueRestrictionDAO.getElementValueRestrictionsForElementValue(elementValues.get(0).getId());
		System.out.println(elementValueRestrictions);

		// element-value-condition
		ElementValueConditionService elementValueConditionDAO = DAOFactory.getInstance().getElementValueConditionService();

		ElementValueConditionDTO elementValueConditionNew1 = new ElementValueConditionDTO(elementValueRestrictions.get(0).getId(), uiPage1.getId(), element1.getId(),
				elementValues.get(0).getId());
		elementValueConditionDAO.saveElementValueCondition(elementValueConditionNew1);
		System.out.println(elementValueConditionNew1);

		List<ElementValueConditionDTO> elementValueConditions = elementValueConditionDAO.getElementValueConditionsByElementValueRestrictionId(elementValueRestrictions.get(0)
				.getId());
		System.out.println(elementValueConditions);
	}
}
