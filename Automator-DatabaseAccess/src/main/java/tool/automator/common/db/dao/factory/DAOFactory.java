package tool.automator.common.db.dao.factory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import tool.automator.common.db.daoif.*;

public class DAOFactory {
	private static DAOFactory instance;
	private ApplicationContext applicationContext;

	private DAOFactory() {
		applicationContext = new ClassPathXmlApplicationContext("config/applicationContext.xml");
	}

	public static DAOFactory getInstance() {
		if (instance == null)
			instance = new DAOFactory();
		return instance;
	}

	public ProjectDAOIf getProjectDAO() {
		return (ProjectDAOIf) applicationContext.getBean("projectDAO");
	}

	public UIPageDAOIf getUIPageDAO() {
		return (UIPageDAOIf) applicationContext.getBean("pageDAO");
	}

	public ElementDAOIf getElementDAO() {
		return (ElementDAOIf) applicationContext.getBean("elementDAO");
	}

	public ElementValueDAOIf getElementValueDAO() {
		return (ElementValueDAOIf) applicationContext.getBean("elementValueDAO");
	}

	public PageDependencyDAOIf getPageDependencyDAO() {
		return (PageDependencyDAOIf) applicationContext.getBean("pageDependencyDAO");
	}

	public PageConditionDAOIf getPageConditionDAO() {
		return (PageConditionDAOIf) applicationContext.getBean("pageConditionDAO");
	}

	public ElementRestrictionDAOIf getElementRestrictionDAO() {
		return (ElementRestrictionDAOIf) applicationContext.getBean("elementRestrictionDAO");
	}

	public ElementConditionDAOIf getElementConditionDAO() {
		return (ElementConditionDAOIf) applicationContext.getBean("elementConditionDAO");
	}

	public ElementValueRestrictionDAOIf getElementValueRestrictionDAO() {
		return (ElementValueRestrictionDAOIf) applicationContext.getBean("elementValueRestrictionDAO");
	}

	public ElementValueConditionDAOIf getElementValueConditionDAO() {
		return (ElementValueConditionDAOIf) applicationContext.getBean("elementValueConditionDAO");
	}
}
