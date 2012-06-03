package tool.automator.common.test;

import java.util.List;

import tool.automator.common.db.dao.factory.DAOFactory;
import tool.automator.common.db.daoif.ElementConditionDAOIf;
import tool.automator.common.db.daoif.ElementDAOIf;
import tool.automator.common.db.daoif.ElementRestrictionDAOIf;
import tool.automator.common.db.daoif.ElementValueConditionDAOIf;
import tool.automator.common.db.daoif.ElementValueDAOIf;
import tool.automator.common.db.daoif.ElementValueRestrictionDAOIf;
import tool.automator.common.db.daoif.PageConditionDAOIf;
import tool.automator.common.db.daoif.PageDependencyDAOIf;
import tool.automator.common.db.daoif.ProjectDAOIf;
import tool.automator.common.db.daoif.UIPageDAOIf;
import tool.automator.common.db.models.ElementConditionModel;
import tool.automator.common.db.models.ElementModel;
import tool.automator.common.db.models.ElementRestrictionModel;
import tool.automator.common.db.models.ElementValueConditionModel;
import tool.automator.common.db.models.ElementValueModel;
import tool.automator.common.db.models.ElementValueRestrictionModel;
import tool.automator.common.db.models.PageConditionModel;
import tool.automator.common.db.models.PageDependencyModel;
import tool.automator.common.db.models.ProjectModel;
import tool.automator.common.db.models.UIPageModel;

public class TestJPAHibernateSpring {
	public static void main(String[] args) {
		// project
		ProjectDAOIf projectDAO = DAOFactory.getInstance().getProjectDAO();

		// ProjectModel projectNew1 = new ProjectModel("Google");
		// projectDAO.saveProject(projectNew1);
		// System.out.println(projectNew1);

		ProjectModel project1 = projectDAO.getProjectByName("Google");
		System.out.println(project1);

		// page
		UIPageDAOIf uiPageDAO = DAOFactory.getInstance().getUIPageDAO();

		// UIPageModel uiPageNew1 = new UIPageModel("SEARCH-PAGE", project1.getId(), false, 5, null, null, null, null);
		// uiPageDAO.savePage(uiPageNew1);
		// System.out.println(uiPageNew1);
		//
		// UIPageModel uiPageNew2 = new UIPageModel("RESULTS-PAGE", project1.getId(), false, 5, null, null, null, null);
		// uiPageDAO.savePage(uiPageNew2);
		// System.out.println(uiPageNew2);

		UIPageModel uiPage1 = uiPageDAO.getPageByName("SEARCH-PAGE", project1.getId());
		UIPageModel uiPage2 = uiPageDAO.getPageByName("RESULTS-PAGE", project1.getId());
		System.out.println(uiPage1 + "\n" + uiPage2);

		// element
		ElementDAOIf elementDAO = DAOFactory.getInstance().getElementDAO();

		// ElementModel elementNew1 = new ElementModel("SEARCH_QUERY", uiPage1.getId(), "q", "NAME", "TEXTBOX", 1, 1, false, false);
		// elementDAO.saveElement(elementNew1);
		// System.out.println(elementNew1);

		ElementModel element1 = elementDAO.getElementOfPageByRelativeOrder(uiPage1.getId(), 1);
		System.out.println(element1);

		// element-value
		ElementValueDAOIf elementValueDAO = DAOFactory.getInstance().getElementValueDAO();

		// ElementValueModel elementValueNew1 = new ElementValueModel(element1.getId(), "Amadeus", "", false, false);
		// elementValueDAO.saveElementValue(elementValueNew1);
		// System.out.println(elementValueNew1);

		List<ElementValueModel> elementValues = elementValueDAO.getAllElementValuesOfElement(element1.getId());
		System.out.println(elementValues);

		// page-dependency
		PageDependencyDAOIf pageDependencyDAO = DAOFactory.getInstance().getPageDependencyDAO();

		// PageDependencyModel pageDependencyNew1 = new PageDependencyModel(uiPage1.getId(), uiPage2.getId());
		// pageDependencyDAO.savePageDependency(pageDependencyNew1);
		// System.out.println(pageDependencyNew1);

		List<PageDependencyModel> pageDependencies = pageDependencyDAO.getPossibleNextPages(uiPage1.getId());
		System.out.println(pageDependencies);

		// page-condition
		PageConditionDAOIf pageConditionDAO = DAOFactory.getInstance().getPageConditionDAO();

		// PageConditionModel pageConditionNew1 = new PageConditionModel(pageDependencies.get(0).getId(), uiPage1.getId(), element1.getId(), elementValues.get(0).getId());
		// pageConditionDAO.savePageCondition(pageConditionNew1);
		// System.out.println(pageConditionNew1);

		List<PageConditionModel> pageConditions = pageConditionDAO.getPageConditionsByPageDependencyId(pageDependencies.get(0).getId());
		System.out.println(pageConditions);

		// element-restrictions
		ElementRestrictionDAOIf elementRestrictionDAO = (ElementRestrictionDAOIf) DAOFactory.getInstance().getElementRestrictionDAO();

		// ElementRestrictionModel elementRestrictionNew1 = new ElementRestrictionModel(uiPage1.getId(), element1.getId());
		// elementRestrictionDAO.saveElementRestriction(elementRestrictionNew1);
		// System.out.println(elementRestrictionNew1);

		List<ElementRestrictionModel> elementRestrictions = elementRestrictionDAO.getElementRestrictionsForElement(element1.getId());
		System.out.println(elementRestrictions);

		// element-condition
		ElementConditionDAOIf elementConditionDAO = (ElementConditionDAOIf) DAOFactory.getInstance().getElementConditionDAO();

		// ElementConditionModel elementConditionNew1 = new ElementConditionModel(elementRestrictions.get(0).getId(), project1.getId(), uiPage1.getId(), element1.getId());
		// elementConditionDAO.saveElementCondition(elementConditionNew1);
		// System.out.println(elementConditionNew1);

		List<ElementConditionModel> elementConditions = elementConditionDAO.getElementConditionsByElementRestrictionId(elementRestrictions.get(0).getId());
		System.out.println(elementConditions);

		// element-value-restrictions
		ElementValueRestrictionDAOIf elementValueRestrictionDAO = (ElementValueRestrictionDAOIf) DAOFactory.getInstance().getElementValueRestrictionDAO();

		// ElementValueRestrictionModel elementValueRestrictionNew1 = new ElementValueRestrictionModel(uiPage1.getId(), element1.getId(), elementValues.get(0).getId());
		// elementValueRestrictionDAO.saveElementValueRestriction(elementValueRestrictionNew1);
		// System.out.println(elementValueRestrictionNew1);

		List<ElementValueRestrictionModel> elementValueRestrictions = elementValueRestrictionDAO.getElementValueRestrictionsForElementValue(elementValues.get(0).getId());
		System.out.println(elementValueRestrictions);

		// element-value-condition
		ElementValueConditionDAOIf elementValueConditionDAO = (ElementValueConditionDAOIf) DAOFactory.getInstance().getElementValueConditionDAO();

		// ElementValueConditionModel elementValueConditionNew1 = new ElementValueConditionModel(elementValueRestrictions.get(0).getId(), uiPage1.getId(), element1.getId(),
		// elementValues.get(0).getId());
		// elementValueConditionDAO.saveElementValueCondition(elementValueConditionNew1);
		// System.out.println(elementValueConditionNew1);

		List<ElementValueConditionModel> elementValueConditions = elementValueConditionDAO.getElementValueConditionsByElementValueRestrictionId(elementValueRestrictions
				.get(0).getId());
		System.out.println(elementValueConditions);
	}
}
