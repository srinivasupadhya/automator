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
import tool.automator.generator.constants.TestScriptConst;
import tool.automator.generator.util.ConditionHolder;

public class TestScriptsGeneratorMain {

	private static Map<String, Integer> elementValueSizeMap = new HashMap<String, Integer>();

	private static ProjectService projectService;
	private static UIPageService uiPageService;
	private static ElementService elementService;
	private static ElementValueService elementValueService;

	private static PageDependencyService pageDependencyService;
	private static PageConditionService pageConditionService;

	private static ElementRestrictionService elementRestrictionService;
	private static ElementConditionService elementConditionService;

	private static ElementValueRestrictionService elementValueRestrictionService;
	private static ElementValueConditionService elementValueConditionService;

	public static void main(String[] args) {
		// String project = args[0];
		// String release = args[1];
		// String browser = args[2];

		// String project = "Google";
		String project = "Air-Canada";
		// String release = "1";
		String release = "60";
		String browser = "Firefox";

		// System.out.println(project + " " + release + " " + browser);

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

		start(project, release, browser);
	}

	// Globals
	private static ConditionHolder projectElementNValueMapObj, releaseElementNValueMapObj, browserElementNValueMapObj;
	private static int testScriptCount = 0;

	/**
	 * initialize globals get start-pages of current-project process each page individually
	 */
	public static void start(String project, String release, String browser) {

		// initialize globals
		ProjectDTO currentProjectObj = projectService.getProjectByName(project);

		UIPageDTO globalPageObj = uiPageService.getPageByName("Global", currentProjectObj.getId());

		// initialize project element
		ElementDTO projectElementObj = elementService.getElementByScriptName(TestScriptConst.PROJECT, globalPageObj.getId());
		ElementValueDTO projectValueElementObj = elementValueService.getElementValueOfElement(project, projectElementObj.getId());
		projectElementNValueMapObj = new ConditionHolder(globalPageObj, projectElementObj, projectValueElementObj);

		// initialize page element
		ElementDTO releaseElementObj = elementService.getElementByScriptName(TestScriptConst.RELEASE, globalPageObj.getId());
		ElementValueDTO releaseValueElementObj = elementValueService.getElementValueOfElement(release, releaseElementObj.getId());
		releaseElementNValueMapObj = new ConditionHolder(globalPageObj, releaseElementObj, releaseValueElementObj);

		// initialize browser element
		ElementDTO browserElementObj = elementService.getElementByScriptName(TestScriptConst.BROWSER, globalPageObj.getId());
		ElementValueDTO browserValueElementObj = elementValueService.getElementValueOfElement(browser, browserElementObj.getId());
		browserElementNValueMapObj = new ConditionHolder(globalPageObj, browserElementObj, browserValueElementObj);

		// find startPages of project & start processing each of them individually
		List<UIPageDTO> startPages = uiPageService.getStartPagesOfProject(currentProjectObj.getId());

		for (int i = 0; i < startPages.size(); i++) {
			processPage(currentProjectObj, startPages.get(i), new ArrayList<ConditionHolder>());
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
	public static void processPage(ProjectDTO currentProjectObj, UIPageDTO currentPageObj, List<ConditionHolder> list) {
		UIPageDTO genericPageObj = null;
		ElementDTO currentElementObj = null;
		int index = -1;

		// get 1st element
		if ((index = currentPageObj.getPageName().indexOf('[')) > 0) {
			genericPageObj = uiPageService.getPageByName(currentPageObj.getPageName().substring(0, index), currentProjectObj.getId());
			currentElementObj = elementService.getElementOfPageByRelativeOrder(genericPageObj.getId(), 1);
		}
		else
			currentElementObj = elementService.getElementOfPageByRelativeOrder(currentPageObj.getId(), 1);

		processElement(currentProjectObj, currentPageObj, currentElementObj, list);
	}

	/**
	 * get all possible values of element & process each individually
	 */
	public static void processElement(ProjectDTO currentProjectObj, UIPageDTO currentPageObj, ElementDTO currentElementObj, List<ConditionHolder> list) {
		ElementDTO genericElementObj = null;
		List<ElementValueDTO> values = null;
		int indexPage = -1, indexElement = -1;

		// get values of element
		if ((indexElement = currentElementObj.getScriptName().indexOf('[')) > 0) {
			UIPageDTO genericPageObj = null;

			if ((indexPage = currentPageObj.getPageName().indexOf('[')) > 0) {
				genericPageObj = uiPageService.getPageByName(currentPageObj.getPageName().substring(0, indexPage), currentProjectObj.getId());
				genericElementObj = elementService.getElementByScriptName(currentElementObj.getScriptName().substring(0, indexElement), genericPageObj.getId());
			}
			else
				genericElementObj = elementService.getElementByScriptName(currentElementObj.getScriptName().substring(0, indexElement), currentPageObj.getId());

			values = elementValueService.getAllElementValuesOfElement(genericElementObj.getId());
		}
		else
			values = elementValueService.getAllElementValuesOfElement(currentElementObj.getId());

		// should the element be considered in this path?
		List<ElementRestrictionDTO> elementDependencies = elementRestrictionService.getElementRestrictionsForElement(currentElementObj.getId());
		List<ConditionIf> conditions = new ArrayList<ConditionIf>();

		for (int i = 0; i < elementDependencies.size(); i++) {
			conditions.addAll(elementConditionService.getElementConditionsByElementRestrictionId(elementDependencies.get(i).getId()));
		}

		boolean conditionsMet = checkIfAllConditionsSatisfied(list, conditions);

		if (conditionsMet) {
			boolean inserted = false;

			if (values.size() > 1 && elementValueSizeMap.get(currentElementObj.getScriptName()) == null) {
				elementValueSizeMap.put(currentElementObj.getScriptName(), values.size());
			}

			for (int i = 0; i < values.size(); i++) {
				List<ConditionHolder> newList = new ArrayList<ConditionHolder>(list);

				inserted = processElementValue(currentProjectObj, currentPageObj, currentElementObj, values.get(i), newList);

				if (currentElementObj.getUIElementType().equals(UIElementTypesConst.TXTMSG) && inserted)
					break;
			}
		}
		else if (currentElementObj.isOptional()) {
			processNextElementOfPage(currentProjectObj, currentPageObj, currentElementObj, list);
		}
	}

	/**
	 * process every element value
	 */
	public static boolean processElementValue(ProjectDTO currentProjectObj, UIPageDTO currentPageObj, ElementDTO currentElementObj, ElementValueDTO currentElementValueObj,
			List<ConditionHolder> list) {
		List<ElementValueRestrictionDTO> elementValueDependencies = elementValueRestrictionService.getElementValueRestrictionsForElementValue(currentElementValueObj.getId());
		List<ConditionIf> conditions = new ArrayList<ConditionIf>();

		for (int i = 0; i < elementValueDependencies.size(); i++) {
			conditions.addAll(elementValueConditionService.getElementValueConditionsByElementValueRestrictionId(elementValueDependencies.get(i).getId()));
		}

		boolean conditionsMet = checkIfAllConditionsSatisfied(list, conditions);

		if (conditionsMet) {
			list.add(new ConditionHolder(currentPageObj, currentElementObj, currentElementValueObj));

			if (currentElementValueObj.getTurnsPage()) {
				processNextPageOfProject(currentProjectObj, currentPageObj, list);
				return true;
			}
			else {
				processNextElementOfPage(currentProjectObj, currentPageObj, currentElementObj, list);
				return true;
			}
		}
		// if its a TXTMSG & was not inserted, the path is invalid so skip. continue if currentElement is optional
		else if (!currentElementObj.getUIElementType().equals(UIElementTypesConst.TXTMSG) && currentElementObj.isOptional()) {
			processNextElementOfPage(currentProjectObj, currentPageObj, currentElementObj, list);
			return true;
		}

		return false;
	}

	/**
	 * get next page & process
	 */
	public static void processNextPageOfProject(ProjectDTO currentProjectObj, UIPageDTO currentPageObj, List<ConditionHolder> list) {
		List<? extends ConditionIf> conditions;
		boolean conditionsMet;

		List<PageDependencyDTO> pageDependencies = pageDependencyService.getPossibleNextPages(currentPageObj.getId());

		for (int i = 0; i < pageDependencies.size(); i++) {
			conditions = pageConditionService.getPageConditionsByPageDependencyId(pageDependencies.get(i).getId());

			conditionsMet = checkIfAllConditionsSatisfied(list, conditions);

			if (conditionsMet) {
				currentPageObj = uiPageService.getPageById(pageDependencies.get(i).getDestinationPageId());
				processPage(currentProjectObj, currentPageObj, list);
				return;
			}
		}

		// no next page. end of test-script
		generateTestScript(list);
	}

	/**
	 * get next element of page & process
	 */
	public static void processNextElementOfPage(ProjectDTO currentProjectObj, UIPageDTO currentPageObj, ElementDTO currentElementObj, List<ConditionHolder> list) {
		UIPageDTO genericPageObj = null;
		ElementDTO nextElementObj = null;
		int index = -1;

		if ((index = currentPageObj.getPageName().indexOf('[')) > 0) {
			genericPageObj = uiPageService.getPageByName(currentPageObj.getPageName().substring(0, index), currentProjectObj.getId());
			nextElementObj = elementService.getElementOfPageByRelativeOrder(genericPageObj.getId(), currentElementObj.getRelativeOrder() + 1);
		}
		else
			nextElementObj = elementService.getElementOfPageByRelativeOrder(currentPageObj.getId(), currentElementObj.getRelativeOrder() + 1);

		if (nextElementObj != null)
			processElement(currentProjectObj, currentPageObj, nextElementObj, list);
		// no next element. end of test-script
		else
			generateTestScript(list);
	}

	/**
	 * check if all conditions are met
	 */
	public static boolean checkIfAllConditionsSatisfied(List<ConditionHolder> list, List<? extends ConditionIf> conditions) {
		for (int i = 0; i < conditions.size(); i++) {
			for (int j = list.size() - 1; j >= 0; j--) {
				if (list.get(j).matchesCondition(conditions.get(i))) {
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
	public static void generateTestScript(List<ConditionHolder> list) {
		String testScript = TestScriptConst.START_MODULE + TestScriptConst.START_STEP + TestScriptConst.START_GLOBAL + "\t\t\t$"
				+ projectElementNValueMapObj.getElement().getScriptName() + " = " + projectElementNValueMapObj.getElementValue().getScriptValue() + "\n" + "\t\t\t$"
				+ releaseElementNValueMapObj.getElement().getScriptName() + " = " + releaseElementNValueMapObj.getElementValue().getScriptValue() + "\n" + "\t\t\t$"
				+ browserElementNValueMapObj.getElement().getScriptName() + " = " + browserElementNValueMapObj.getElementValue().getScriptValue() + "\n"
				+ TestScriptConst.END_GLOBAL + TestScriptConst.END_STEP + TestScriptConst.START_STEP + TestScriptConst.START_CASE;

		for (int i = 0; i < list.size(); i++) {
			// initialize new page-element if applicable
			if (i == 0)
				testScript += "\t\t\t$" + TestScriptConst.PAGE + " = " + list.get(i).getPage().getPageName() + "\n";
			else if (list.get(i).getPage().getId() != list.get(i - 1).getPage().getId()) {
				testScript += TestScriptConst.END_CASE + TestScriptConst.END_STEP + TestScriptConst.START_STEP + TestScriptConst.START_CASE;
				if ((list.get(i).getPage().getPageGetURL() == null || list.get(i).getPage().getPageGetURL().trim().isEmpty()))
					testScript += "\t\t\t$" + TestScriptConst.PAGE + " == " + list.get(i).getPage().getPageName() + "\n";
				else
					testScript += "\t\t\t$" + TestScriptConst.PAGE + " = " + list.get(i).getPage().getPageName() + "\n";
			}

			if (list.get(i).getElement().getUIElementType().equals(UIElementTypesConst.TXTMSG))
				testScript += "\t\t\t$" + list.get(i).getElement().getScriptName() + " == " + list.get(i).getElementValue().getScriptValue() + "\n";
			else if (list.get(i).getElement().getUIElementType().equals("FUNCTIONCALL"))
				testScript += "\t\t\t@" + list.get(i).getElement().getScriptName() + " = " + list.get(i).getElementValue().getScriptValue() + "\n";
			else
				testScript += "\t\t\t$" + list.get(i).getElement().getScriptName() + " = " + list.get(i).getElementValue().getScriptValue() + "\n";
		}

		testScript += TestScriptConst.END_CASE + TestScriptConst.END_STEP + TestScriptConst.END_MODULE;

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
