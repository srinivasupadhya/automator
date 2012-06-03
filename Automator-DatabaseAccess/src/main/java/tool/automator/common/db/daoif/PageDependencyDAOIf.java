package tool.automator.common.db.daoif;

import java.util.List;

import tool.automator.common.db.models.PageDependencyModel;

public interface PageDependencyDAOIf {

	public PageDependencyModel getPageDependencyById(int id);

	public void savePageDependency(PageDependencyModel pageDependency);

	public List<PageDependencyModel> getAllPageDependecies();

	public List<PageDependencyModel> getPageDependencies(int sourcePageId, int destinationPageId);
	
	public List<PageDependencyModel> getPossibleNextPages(int pageId);

	public void removePageDependency(int id);
}
