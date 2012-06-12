package tool.automator.database.table.pagedependency;

import java.util.List;

public interface PageDependencyService {
	public PageDependencyDTO getPageDependencyById(Long id);

	public void savePageDependency(PageDependencyDTO pageDependency);

	public List<PageDependencyDTO> getAllPageDependecies();

	public List<PageDependencyDTO> getPageDependencies(Long sourcePageId, Long destinationPageId);

	public List<PageDependencyDTO> getPossibleNextPages(Long pageId);

	public void removePageDependency(Long id);
}
