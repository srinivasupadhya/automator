package tool.automator.client.framework.models.holders;

import java.util.ArrayList;
import java.util.List;

import tool.automator.database.factory.DAOFactory;
import tool.automator.database.table.element.ElementDTO;
import tool.automator.database.table.element.ElementService;
import tool.automator.database.table.pagecondition.PageConditionDTO;
import tool.automator.database.table.pagecondition.PageConditionService;
import tool.automator.database.table.pagedependency.PageDependencyDTO;
import tool.automator.database.table.pagedependency.PageDependencyService;
import tool.automator.database.table.uipage.UIPageDTO;

public class PageDetailsModel {
	private UIPageDTO page;
	private List<PageDependencyDTO> pageDependencies;
	private List<PageConditionDTO> pageConditions;
	private List<ElementDetailsModel> elementDetailsList;

	public PageDetailsModel(UIPageDTO page) {
		this.page = page;
		PageDependencyService pageDependencyDAO = DAOFactory.getInstance().getPageDependencyService();
		pageDependencies = pageDependencyDAO.getPossibleNextPages(page.getId());
		pageConditions = new ArrayList<PageConditionDTO>();
		if (pageDependencies != null) {
			PageConditionService pageConditionDAO = DAOFactory.getInstance().getPageConditionService();
			for (PageDependencyDTO currentPageDependency : pageDependencies)
				pageConditions.addAll(pageConditionDAO.getPageConditionsByPageDependencyId(currentPageDependency.getId()));
		}
		else
			pageDependencies = new ArrayList<PageDependencyDTO>();
		ElementService elementDAO = DAOFactory.getInstance().getElementService();
		List<ElementDTO> elementList = elementDAO.getElementsOfPage(page.getId());
		elementDetailsList = new ArrayList<ElementDetailsModel>();
		for (ElementDTO currentElement : elementList)
			elementDetailsList.add(new ElementDetailsModel(currentElement));
	}

	public UIPageDTO getPage() {
		return page;
	}

	public void setPage(UIPageDTO page) {
		this.page = page;
	}

	public void setPageDependencies(List<PageDependencyDTO> pageDependencies) {
		this.pageDependencies = pageDependencies;
	}

	public List<PageDependencyDTO> getPageDependencies() {
		return pageDependencies;
	}

	public List<PageConditionDTO> getPageConditions() {
		return pageConditions;
	}

	public void setPageConditions(List<PageConditionDTO> pageConditions) {
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
