package tool.automator.database.factory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import tool.automator.database.table.element.ElementService;
import tool.automator.database.table.elementcondition.ElementConditionService;
import tool.automator.database.table.elementrestriction.ElementRestrictionService;
import tool.automator.database.table.elementvalue.ElementValueService;
import tool.automator.database.table.elementvaluecondition.ElementValueConditionService;
import tool.automator.database.table.elementvaluerestriction.ElementValueRestrictionService;
import tool.automator.database.table.pagecondition.PageConditionService;
import tool.automator.database.table.pagedependency.PageDependencyService;
import tool.automator.database.table.project.ProjectService;
import tool.automator.database.table.uipage.UIPageService;

public class DAOFactory {
	private static DAOFactory instance;
	private ApplicationContext applicationContext;

	private DAOFactory() {
		applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
	}

	public static DAOFactory getInstance() {
		if (instance == null)
			instance = new DAOFactory();
		return instance;
	}

	public ProjectService getProjectService() {
		return (ProjectService) applicationContext.getBean("projectService");
	}

	public UIPageService getUIPageService() {
		return (UIPageService) applicationContext.getBean("pageService");
	}

	public ElementService getElementService() {
		return (ElementService) applicationContext.getBean("elementService");
	}

	public ElementValueService getElementValueService() {
		return (ElementValueService) applicationContext.getBean("elementValueService");
	}

	public PageDependencyService getPageDependencyService() {
		return (PageDependencyService) applicationContext.getBean("pageDependencyService");
	}

	public PageConditionService getPageConditionService() {
		return (PageConditionService) applicationContext.getBean("pageConditionService");
	}

	public ElementRestrictionService getElementRestrictionService() {
		return (ElementRestrictionService) applicationContext.getBean("elementRestrictionService");
	}

	public ElementConditionService getElementConditionService() {
		return (ElementConditionService) applicationContext.getBean("elementConditionService");
	}

	public ElementValueRestrictionService getElementValueRestrictionService() {
		return (ElementValueRestrictionService) applicationContext.getBean("elementValueRestrictionService");
	}

	public ElementValueConditionService getElementValueConditionService() {
		return (ElementValueConditionService) applicationContext.getBean("elementValueConditionService");
	}
}
