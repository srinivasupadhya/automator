package tool.automator.client.framework.models.holders;

import java.util.ArrayList;
import java.util.List;

import tool.automator.common.db.dao.factory.DAOFactory;
import tool.automator.common.db.daoif.*;
import tool.automator.common.db.models.*;

public class PageDetailsModel {
	private UIPageModel page;
	private List<PageDependencyModel> pageDependencies;
	private List<PageConditionModel> pageConditions;
	private List<ElementDetailsModel> elementDetailsList;

	public PageDetailsModel(UIPageModel page) {
		this.page = page;
		PageDependencyDAOIf pageDependencyDAO = DAOFactory.getInstance().getPageDependencyDAO();
		pageDependencies = pageDependencyDAO.getPossibleNextPages(page.getId());
		pageConditions = new ArrayList<PageConditionModel>();
		if (pageDependencies != null) {
			PageConditionDAOIf pageConditionDAO = DAOFactory.getInstance().getPageConditionDAO();
			for (PageDependencyModel currentPageDependency : pageDependencies)
				pageConditions.addAll(pageConditionDAO.getPageConditionsByPageDependencyId(currentPageDependency.getId()));
		}
		else
			pageDependencies = new ArrayList<PageDependencyModel>();
		ElementDAOIf elementDAO = DAOFactory.getInstance().getElementDAO();
		List<ElementModel> elementList = elementDAO.getElementsOfPage(page.getId());
		elementDetailsList = new ArrayList<ElementDetailsModel>();
		for (ElementModel currentElement : elementList)
			elementDetailsList.add(new ElementDetailsModel(currentElement));
	}

	public UIPageModel getPage() {
		return page;
	}

	public void setPage(UIPageModel page) {
		this.page = page;
	}

	public void setPageDependencies(List<PageDependencyModel> pageDependencies) {
		this.pageDependencies = pageDependencies;
	}

	public List<PageDependencyModel> getPageDependencies() {
		return pageDependencies;
	}

	public List<PageConditionModel> getPageConditions() {
		return pageConditions;
	}

	public void setPageConditions(List<PageConditionModel> pageConditions) {
		this.pageConditions = pageConditions;
	}

	public List<ElementDetailsModel> getElementDetailsList() {
		return elementDetailsList;
	}

	public void setElementDetailsList(List<ElementDetailsModel> elementDetailsList) {
		this.elementDetailsList = elementDetailsList;
	}

	public String toString() {
		return page.getPageName() + "\n" + elementDetailsList;
	}
}
