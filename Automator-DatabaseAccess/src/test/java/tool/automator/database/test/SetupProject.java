package tool.automator.database.test;

import tool.automator.database.constants.UIElementGetTypesConst;
import tool.automator.database.constants.UIElementTypesConst;
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

public class SetupProject {
	public static void main(String[] args) {
		// services
		ProjectService projectService = DAOFactory.getInstance().getProjectService();
		UIPageService uiPageService = DAOFactory.getInstance().getUIPageService();
		ElementService elementService = DAOFactory.getInstance().getElementService();
		ElementValueService elementValueService = DAOFactory.getInstance().getElementValueService();

		PageDependencyService pageDependencyService = DAOFactory.getInstance().getPageDependencyService();
		PageConditionService pageConditionService = DAOFactory.getInstance().getPageConditionService();

		ElementRestrictionService elementRestrictionService = DAOFactory.getInstance().getElementRestrictionService();
		ElementConditionService elementConditionService = DAOFactory.getInstance().getElementConditionService();

		ElementValueRestrictionService elementValueRestrictionService = DAOFactory.getInstance().getElementValueRestrictionService();
		ElementValueConditionService elementValueConditionService = DAOFactory.getInstance().getElementValueConditionService();

		// project
		ProjectDTO projectYahoo = createProject(projectService, "Yahoo");

		// page
		UIPageDTO uiPageGlobal = createUIPage(uiPageService, "GLOBAL", projectYahoo, false);

		UIPageDTO uiPageSearch = createUIPage(uiPageService, "SEARCH-PAGE", projectYahoo, true);

		UIPageDTO uiPageResults = createUIPage(uiPageService, "RESULTS-PAGE", projectYahoo, false);

		// element
		ElementDTO elementProject = createElement(elementService, "PROJECT", uiPageGlobal, null, null, UIElementTypesConst.VARIABLE, 1);

		ElementDTO elementRelease = createElement(elementService, "RELEASE", uiPageGlobal, null, null, UIElementTypesConst.VARIABLE, 2);

		ElementDTO elementBrowser = createElement(elementService, "BROWSER", uiPageGlobal, null, null, UIElementTypesConst.VARIABLE, 3);

		ElementDTO elementSearchQuery = createElement(elementService, "SEARCH_QUERY", uiPageSearch, "p", UIElementGetTypesConst.NAME, UIElementTypesConst.TEXTBOX, 1);

		ElementDTO elementSearchSubmit = createElement(elementService, "SEARCH_SUBMIT", uiPageSearch, "search-submit", UIElementGetTypesConst.ID, UIElementTypesConst.BUTTON,
				2);

		ElementDTO elementSearchResult = createElement(elementService, "SEARCH_RESULT", uiPageResults, "url", UIElementGetTypesConst.CLASS, UIElementTypesConst.TXTMSG, 1);

		// element-value
		ElementValueDTO elementValueYahoo = createElementValue(elementValueService, "Yahoo", elementProject, false);

		ElementValueDTO elementValue1 = createElementValue(elementValueService, "1", elementRelease, false);

		ElementValueDTO elementValueFirefox = createElementValue(elementValueService, "Firefox", elementBrowser, false);

		ElementValueDTO elementValueThoughtWorks = createElementValue(elementValueService, "ThoughtWorks", elementSearchQuery, false);

		ElementValueDTO elementValueThoughtWorksStudios = createElementValue(elementValueService, "ThoughtWorks Studios", elementSearchQuery, false);

		ElementValueDTO elementValueClick = createElementValue(elementValueService, "CLICK", elementSearchSubmit, true);

		ElementValueDTO elementValueThoughtWorksTxtMsg = createElementValue(elementValueService, "www.thoughtworks.com", elementSearchResult, false);

		ElementValueDTO elementValueThoughtWorksStudiosTxtMsg = createElementValue(elementValueService, "www.thoughtworks-studios.com", elementSearchResult, false);

		// page-dependency
		PageDependencyDTO pageDependencySearchToResults = createPageDependency(pageDependencyService, uiPageSearch, uiPageResults);

		// page-condition
		PageConditionDTO pageCondition_SearchToResults_Search_SearchQuery_SearchSubmit = createPageCondition(pageConditionService, pageDependencySearchToResults,
				uiPageSearch, elementSearchSubmit, elementValueClick);

		// element-restrictions
		// ElementRestrictionDTO elementRestriction_Search_SearchQuery = createElementRestriction(elementRestrictionService, uiPageSearch, elementSearchQuery);

		// element-condition
		// ElementConditionDTO elementCondition_Search_SearchQuery = createElementCondition(elementConditionService, projectYahoo, uiPageSearch, elementValueThoughtWorks,
		// elementRestriction_Search_SearchQuery);

		// element-value-restrictions
		ElementValueRestrictionDTO elementValueRestriction_Results_SearchResult_ThoughtWorksTxtMsg = createElementValueRestriction(elementValueRestrictionService,
				uiPageResults, elementSearchResult, elementValueThoughtWorksTxtMsg);

		ElementValueRestrictionDTO elementValueRestriction_Results_SearchResult_ThoughtWorksStudiosTxtMsg = createElementValueRestriction(elementValueRestrictionService,
				uiPageResults, elementSearchResult, elementValueThoughtWorksStudiosTxtMsg);

		// element-value-condition
		ElementValueConditionDTO elementValueCondition_Results_SearchResult_ThoughtWorksTxtMsg = createElementValueCondition(elementValueConditionService,
				elementValueRestriction_Results_SearchResult_ThoughtWorksTxtMsg, uiPageSearch, elementSearchQuery, elementValueThoughtWorks);

		ElementValueConditionDTO elementValueCondition_Results_SearchResult_ThoughtWorksStudiosTxtMsg = createElementValueCondition(elementValueConditionService,
				elementValueRestriction_Results_SearchResult_ThoughtWorksStudiosTxtMsg, uiPageSearch, elementSearchQuery, elementValueThoughtWorksStudios);

		System.exit(0);
	}

	public static ElementValueConditionDTO createElementValueCondition(ElementValueConditionService elementValueConditionService,
			ElementValueRestrictionDTO elementValueRestriction_Search_SearchQuery_Amadeus, UIPageDTO uiPageSearch, ElementDTO elementSearchQuery,
			ElementValueDTO elementValueThoughtWorks) {
		ElementValueConditionDTO elementValueCondition_Search_SearchQuery_Amadeus = new ElementValueConditionDTO(elementValueRestriction_Search_SearchQuery_Amadeus.getId(),
				uiPageSearch.getId(), elementSearchQuery.getId(), elementValueThoughtWorks.getId());
		elementValueConditionService.saveElementValueCondition(elementValueCondition_Search_SearchQuery_Amadeus);
		System.out.println(elementValueCondition_Search_SearchQuery_Amadeus);
		return elementValueCondition_Search_SearchQuery_Amadeus;
	}

	public static ElementValueRestrictionDTO createElementValueRestriction(ElementValueRestrictionService elementValueRestrictionService, UIPageDTO uiPageSearch,
			ElementDTO elementSearchQuery, ElementValueDTO elementValueThoughtWorks) {
		ElementValueRestrictionDTO elementValueRestriction_Search_SearchQuery_Amadeus = new ElementValueRestrictionDTO(uiPageSearch.getId(), elementSearchQuery.getId(),
				elementValueThoughtWorks.getId());
		elementValueRestrictionService.saveElementValueRestriction(elementValueRestriction_Search_SearchQuery_Amadeus);
		System.out.println(elementValueRestriction_Search_SearchQuery_Amadeus);
		return elementValueRestriction_Search_SearchQuery_Amadeus;
	}

	public static ElementConditionDTO createElementCondition(ElementConditionService elementConditionService, ProjectDTO projectGoogle, UIPageDTO uiPageSearch,
			ElementValueDTO elementValueThoughtWorks, ElementRestrictionDTO elementRestriction_Search_SearchQuery) {
		ElementConditionDTO elementCondition_Search_SearchQuery = new ElementConditionDTO(elementRestriction_Search_SearchQuery.getId(), projectGoogle.getId(),
				uiPageSearch.getId(), elementValueThoughtWorks.getId());
		elementConditionService.saveElementCondition(elementCondition_Search_SearchQuery);
		System.out.println(elementCondition_Search_SearchQuery);
		return elementCondition_Search_SearchQuery;
	}

	public static ElementRestrictionDTO createElementRestriction(ElementRestrictionService elementRestrictionService, UIPageDTO uiPageSearch, ElementDTO elementSearchQuery) {
		ElementRestrictionDTO elementRestriction_Search_SearchQuery = new ElementRestrictionDTO(uiPageSearch.getId(), elementSearchQuery.getId());
		elementRestrictionService.saveElementRestriction(elementRestriction_Search_SearchQuery);
		System.out.println(elementRestriction_Search_SearchQuery);
		return elementRestriction_Search_SearchQuery;
	}

	public static PageConditionDTO createPageCondition(PageConditionService pageConditionService, PageDependencyDTO pageDependencySearchToResults, UIPageDTO uiPageSearch,
			ElementDTO elementSearchQuery, ElementValueDTO elementValueThoughtWorks) {
		PageConditionDTO pageCondition_SearchToResults_Search_SearchQuery_SearchSubmit = new PageConditionDTO(pageDependencySearchToResults.getId(), uiPageSearch.getId(),
				elementSearchQuery.getId(), elementValueThoughtWorks.getId());
		pageConditionService.savePageCondition(pageCondition_SearchToResults_Search_SearchQuery_SearchSubmit);
		System.out.println(pageCondition_SearchToResults_Search_SearchQuery_SearchSubmit);
		return pageCondition_SearchToResults_Search_SearchQuery_SearchSubmit;
	}

	public static PageDependencyDTO createPageDependency(PageDependencyService pageDependencyService, UIPageDTO uiPageSearch, UIPageDTO uiPageResults) {
		PageDependencyDTO pageDependencySearchToResults = new PageDependencyDTO(uiPageSearch.getId(), uiPageResults.getId());
		pageDependencyService.savePageDependency(pageDependencySearchToResults);
		System.out.println(pageDependencySearchToResults);
		return pageDependencySearchToResults;
	}

	public static ElementValueDTO createElementValue(ElementValueService elementValueService, String elementValue, ElementDTO elementSearchQuery, boolean turnsPage) {
		ElementValueDTO elementValueThoughtWorks = new ElementValueDTO(elementSearchQuery.getId(), elementValue, "", turnsPage, false);
		elementValueService.saveElementValue(elementValueThoughtWorks);
		System.out.println(elementValueThoughtWorks);
		return elementValueThoughtWorks;
	}

	public static ElementDTO createElement(ElementService elementService, String elementName, UIPageDTO uiPageGlobal, String elementIdentifer, String elementGetType,
			String elementType, int relativeOrder) {
		ElementDTO elementProject = new ElementDTO(elementName, uiPageGlobal.getId(), elementIdentifer, elementGetType, elementType, relativeOrder, 1, false, false);
		elementService.saveElement(elementProject);
		System.out.println(elementProject);
		return elementProject;
	}

	public static UIPageDTO createUIPage(UIPageService uiPageService, String pageName, ProjectDTO projectGoogle, boolean isStartPage) {
		UIPageDTO uiPageGlobal = new UIPageDTO(pageName, projectGoogle.getId(), isStartPage, 5, null, null, null, null);
		uiPageService.savePage(uiPageGlobal);
		System.out.println(uiPageGlobal);
		return uiPageGlobal;
	}

	public static ProjectDTO createProject(ProjectService projectService, String projectName) {
		ProjectDTO projectGoogle = new ProjectDTO(projectName);
		projectService.saveProject(projectGoogle);
		System.out.println(projectGoogle);
		return projectGoogle;
	}
}
