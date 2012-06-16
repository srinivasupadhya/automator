package tool.automator.generator.main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import tool.automator.database.constants.UIElementTypesConst;
import tool.automator.database.factory.DAOFactory;
import tool.automator.database.table.ConditionIf;
import tool.automator.database.table.element.ElementDTO;
import tool.automator.database.table.element.ElementService;
import tool.automator.database.table.elementcondition.ElementConditionService;
import tool.automator.database.table.elementrestriction.ElementRestrictionDTO;
import tool.automator.database.table.elementrestriction.ElementRestrictionService;
import tool.automator.database.table.elementvalue.ElementValueDTO;
import tool.automator.database.table.elementvalue.ElementValueService;
import tool.automator.database.table.elementvaluecondition.ElementValueConditionService;
import tool.automator.database.table.elementvaluerestriction.ElementValueRestrictionDTO;
import tool.automator.database.table.elementvaluerestriction.ElementValueRestrictionService;
import tool.automator.database.table.pagecondition.PageConditionService;
import tool.automator.database.table.pagedependency.PageDependencyDTO;
import tool.automator.database.table.pagedependency.PageDependencyService;
import tool.automator.database.table.project.ProjectDTO;
import tool.automator.database.table.project.ProjectService;
import tool.automator.database.table.uipage.UIPageDTO;
import tool.automator.database.table.uipage.UIPageService;
import tool.automator.generator.constants.TestScriptConstants;
import tool.automator.generator.util.ConditionHolder;

public class TestScriptsGenerator {
	// Services
	private ProjectService projectService;
	private UIPageService uiPageService;
	private ElementService elementService;
	private ElementValueService elementValueService;

	private PageDependencyService pageDependencyService;
	private PageConditionService pageConditionService;

	private ElementRestrictionService elementRestrictionService;
	private ElementConditionService elementConditionService;

	private ElementValueRestrictionService elementValueRestrictionService;
	private ElementValueConditionService elementValueConditionService;

	// Globals
	private String project, release, browser;
	private ConditionHolder projectElementNValueMap, releaseElementNValueMap, browserElementNValueMap;
	private Map<String, Integer> elementValueSizeMap = new HashMap<String, Integer>();
	private int testScriptCount = 0;

	public TestScriptsGenerator(String project, String release, String browser) {
		projectService = DAOFactory.getInstance().getProjectService();
		uiPageService = DAOFactory.getInstance().getUIPageService();
		elementService = DAOFactory.getInstance().getElementService();
		elementValueService = DAOFactory.getInstance().getElementValueService();
		pageDependencyService = DAOFactory.getInstance().getPageDependencyService();
		pageConditionService = DAOFactory.getInstance().getPageConditionService();
		elementRestrictionService = DAOFactory.getInstance().getElementRestrictionService();
		elementConditionService = DAOFactory.getInstance().getElementConditionService();
		elementValueRestrictionService = DAOFactory.getInstance().getElementValueRestrictionService();
		elementValueConditionService = DAOFactory.getInstance().getElementValueConditionService();
	}

	/**
	 * initialize globals get start-pages of current-project process each page individually
	 */
	public void start() {
		// initialize globals
		ProjectDTO currentProject = projectService.getProjectByName(project);

		UIPageDTO globalPage = uiPageService.getPageByName("Global", currentProject.getId());

		// initialize project element
		ElementDTO projectElement = elementService.getElementByScriptName(TestScriptConstants.PROJECT, globalPage.getId());
		ElementValueDTO projectValueElement = elementValueService.getElementValueOfElement(project, projectElement.getId());
		projectElementNValueMap = new ConditionHolder(globalPage, projectElement, projectValueElement);

		// initialize page element
		ElementDTO releaseElement = elementService.getElementByScriptName(TestScriptConstants.RELEASE, globalPage.getId());
		ElementValueDTO releaseValueElement = elementValueService.getElementValueOfElement(release, releaseElement.getId());
		releaseElementNValueMap = new ConditionHolder(globalPage, releaseElement, releaseValueElement);

		// initialize browser element
		ElementDTO browserElement = elementService.getElementByScriptName(TestScriptConstants.BROWSER, globalPage.getId());
		ElementValueDTO browserValueElement = elementValueService.getElementValueOfElement(browser, browserElement.getId());
		browserElementNValueMap = new ConditionHolder(globalPage, browserElement, browserValueElement);

		// find startPages of project & start processing each of them individually
		List<UIPageDTO> startPages = uiPageService.getStartPagesOfProject(currentProject.getId());

		for (int i = 0; i < startPages.size(); i++) {
			processPage(currentProject, startPages.get(i), new ArrayList<ConditionHolder>());
		}

		Set<String> keys = elementValueSizeMap.keySet();
		Iterator<String> iterator = keys.iterator();
		String currentKey;
		while (iterator.hasNext()) {
			currentKey = iterator.next();
			System.out.println(currentKey + " : " + elementValueSizeMap.get(currentKey));
		}
	}

	/**
	 * get 1st element of current-page process that element
	 */
	public void processPage(ProjectDTO currentProject, UIPageDTO currentPage, List<ConditionHolder> list) {
		UIPageDTO genericPage = null;
		ElementDTO currentElement = null;
		int index = -1;

		// get 1st element
		// handle array index of a page - go to generic of current page to fetch element definition
		if ((index = currentPage.getPageName().indexOf('[')) > 0) {
			genericPage = uiPageService.getPageByName(currentPage.getPageName().substring(0, index), currentProject.getId());
			currentElement = elementService.getElementOfPageByRelativeOrder(genericPage.getId(), 1);
		}
		else {
			currentElement = elementService.getElementOfPageByRelativeOrder(currentPage.getId(), 1);
		}

		processElement(currentProject, currentPage, currentElement, list);
	}

	/**
	 * get all possible values of element & process each individually
	 */
	public void processElement(ProjectDTO currentProject, UIPageDTO currentPage, ElementDTO currentElement, List<ConditionHolder> list) {
		UIPageDTO genericPage = null;
		ElementDTO genericElement = null;
		List<ElementValueDTO> values = null;
		int indexPage = -1, indexElement = -1;

		// get values of element
		// handle array index of a element - go to generic of current element to fetch element-value(s) definition
		if ((indexElement = currentElement.getScriptName().indexOf('[')) > 0) {
			// handle array index of a page - go to generic of current page to fetch element definition
			if ((indexPage = currentPage.getPageName().indexOf('[')) > 0) {
				genericPage = uiPageService.getPageByName(currentPage.getPageName().substring(0, indexPage), currentProject.getId());
				genericElement = elementService.getElementByScriptName(currentElement.getScriptName().substring(0, indexElement), genericPage.getId());
			}
			else {
				genericElement = elementService.getElementByScriptName(currentElement.getScriptName().substring(0, indexElement), currentPage.getId());
			}

			values = elementValueService.getAllElementValuesOfElement(genericElement.getId());
		}
		else {
			values = elementValueService.getAllElementValuesOfElement(currentElement.getId());
		}

		// should the element be considered in this path?
		// TODO: use pageid also?
		List<ElementRestrictionDTO> elementDependencies = elementRestrictionService.getElementRestrictionsForElement(currentElement.getId());
		List<ConditionIf> conditions = new ArrayList<ConditionIf>();

		for (int i = 0; i < elementDependencies.size(); i++) {
			conditions.addAll(elementConditionService.getElementConditionsByElementRestrictionId(elementDependencies.get(i).getId()));
		}

		boolean conditionsMet = checkIfAllConditionsSatisfied(list, conditions);

		if (conditionsMet) {
			boolean inserted = false;

			if (values.size() > 1 && elementValueSizeMap.get(currentElement.getScriptName()) == null) {
				elementValueSizeMap.put(currentElement.getScriptName(), values.size());
			}

			for (int i = 0; i < values.size(); i++) {
				List<ConditionHolder> newList = new ArrayList<ConditionHolder>(list);

				inserted = processElementValue(currentProject, currentPage, currentElement, values.get(i), newList);

				if (currentElement.getUIElementType().equals(UIElementTypesConst.TXTMSG) && inserted)
					break;
			}
		}
		else if (currentElement.isOptional()) {
			processNextElementOfPage(currentProject, currentPage, currentElement, list);
		}
	}

	/**
	 * process every element value
	 */
	public boolean processElementValue(ProjectDTO currentProject, UIPageDTO currentPage, ElementDTO currentElement, ElementValueDTO currentElementValue,
			List<ConditionHolder> list) {
		// TODO: use pageid & elementid also?
		List<ElementValueRestrictionDTO> elementValueDependencies = elementValueRestrictionService.getElementValueRestrictionsForElementValue(currentElementValue.getId());
		List<ConditionIf> conditions = new ArrayList<ConditionIf>();

		for (int i = 0; i < elementValueDependencies.size(); i++) {
			conditions.addAll(elementValueConditionService.getElementValueConditionsByElementValueRestrictionId(elementValueDependencies.get(i).getId()));
		}

		boolean conditionsMet = checkIfAllConditionsSatisfied(list, conditions);

		if (conditionsMet) {
			list.add(new ConditionHolder(currentPage, currentElement, currentElementValue));

			if (currentElementValue.getTurnsPage()) {
				processNextPageOfProject(currentProject, currentPage, list);
				return true;
			}
			else {
				processNextElementOfPage(currentProject, currentPage, currentElement, list);
				return true;
			}
		}
		// if its a TXTMSG & was not inserted, the path is invalid so skip. continue if currentElement is optional
		else if (!currentElement.getUIElementType().equals(UIElementTypesConst.TXTMSG) && currentElement.isOptional()) {
			processNextElementOfPage(currentProject, currentPage, currentElement, list);
			return true;
		}

		return false;
	}

	/**
	 * get next page & process
	 */
	public void processNextPageOfProject(ProjectDTO currentProject, UIPageDTO currentPage, List<ConditionHolder> list) {
		List<? extends ConditionIf> conditions;
		boolean conditionsMet;
		PageDependencyDTO currentPageDependency;

		List<PageDependencyDTO> pageDependencies = pageDependencyService.getPossibleNextPages(currentPage.getId());

		for (int i = 0; i < pageDependencies.size(); i++) {
			currentPageDependency = pageDependencies.get(i);
			
			conditions = pageConditionService.getPageConditionsByPageDependencyId(currentPageDependency.getId());

			conditionsMet = checkIfAllConditionsSatisfied(list, conditions);

			if (conditionsMet) {
				currentPage = uiPageService.getPageById(currentPageDependency.getDestinationPageId());
				processPage(currentProject, currentPage, list);
				return;
			}
		}

		// no next page. end of test-script
		generateTestScript(list);
	}

	/**
	 * get next element of page & process
	 */
	public void processNextElementOfPage(ProjectDTO currentProject, UIPageDTO currentPage, ElementDTO currentElement, List<ConditionHolder> list) {
		UIPageDTO genericPage = null;
		ElementDTO nextElement = null;
		int index = -1;

		if ((index = currentPage.getPageName().indexOf('[')) > 0) {
			genericPage = uiPageService.getPageByName(currentPage.getPageName().substring(0, index), currentProject.getId());
			nextElement = elementService.getElementOfPageByRelativeOrder(genericPage.getId(), currentElement.getRelativeOrder() + 1);
		}
		else
			nextElement = elementService.getElementOfPageByRelativeOrder(currentPage.getId(), currentElement.getRelativeOrder() + 1);

		if (nextElement != null)
			processElement(currentProject, currentPage, nextElement, list);
		// no next element. end of test-script
		else
			generateTestScript(list);
	}

	/**
	 * check if all conditions are met
	 */
	public boolean checkIfAllConditionsSatisfied(List<ConditionHolder> list, List<? extends ConditionIf> conditions) {
		for (int i = 0; i < conditions.size(); i++) {
			// scan reverse for performance. more likely to find condition at other end.
			for (int j = list.size() - 1; j >= 0; j--) {
				if (list.get(j).equals(conditions.get(i))) {
					conditions.remove(i);
					i--;
					break;
				}
			}
		}

		if (conditions.size() == 0)
			return true;

		return false;
	}

	/**
	 * generate test-script from list
	 */
	public void generateTestScript(List<ConditionHolder> list) {
		String testScript = TestScriptConstants.START_MODULE + TestScriptConstants.START_STEP + TestScriptConstants.START_GLOBAL + "\t\t\t$"
				+ projectElementNValueMap.getElement().getScriptName() + " = " + projectElementNValueMap.getElementValue().getScriptValue() + "\n" + "\t\t\t$"
				+ releaseElementNValueMap.getElement().getScriptName() + " = " + releaseElementNValueMap.getElementValue().getScriptValue() + "\n" + "\t\t\t$"
				+ browserElementNValueMap.getElement().getScriptName() + " = " + browserElementNValueMap.getElementValue().getScriptValue() + "\n"
				+ TestScriptConstants.END_GLOBAL + TestScriptConstants.END_STEP + TestScriptConstants.START_STEP + TestScriptConstants.START_CASE;

		for (int i = 0; i < list.size(); i++) {
			// initialize new page-element if applicable
			if (i == 0)
				testScript += "\t\t\t$" + TestScriptConstants.PAGE + " = " + list.get(i).getPage().getPageName() + "\n";
			else if (list.get(i).getPage().getId() != list.get(i - 1).getPage().getId()) {
				testScript += TestScriptConstants.END_CASE + TestScriptConstants.END_STEP + TestScriptConstants.START_STEP + TestScriptConstants.START_CASE;
				if ((list.get(i).getPage().getPageGetURL() == null || list.get(i).getPage().getPageGetURL().trim().isEmpty()))
					testScript += "\t\t\t$" + TestScriptConstants.PAGE + " == " + list.get(i).getPage().getPageName() + "\n";
				else
					testScript += "\t\t\t$" + TestScriptConstants.PAGE + " = " + list.get(i).getPage().getPageName() + "\n";
			}

			if (list.get(i).getElement().getUIElementType().equals(UIElementTypesConst.TXTMSG))
				testScript += "\t\t\t$" + list.get(i).getElement().getScriptName() + " == " + list.get(i).getElementValue().getScriptValue() + "\n";
			else if (list.get(i).getElement().getUIElementType().equals("FUNCTIONCALL"))
				testScript += "\t\t\t@" + list.get(i).getElement().getScriptName() + " = " + list.get(i).getElementValue().getScriptValue() + "\n";
			else
				testScript += "\t\t\t$" + list.get(i).getElement().getScriptName() + " = " + list.get(i).getElementValue().getScriptValue() + "\n";
		}

		testScript += TestScriptConstants.END_CASE + TestScriptConstants.END_STEP + TestScriptConstants.END_MODULE;

		testScriptCount++;

		System.out.println("Test-Script: " + testScriptCount);
		// System.out.println(testScript);

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("GeneratedTestScripts/" + testScriptCount + ".txt"));
			writer.write(testScript);
			writer.flush();
			writer.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
