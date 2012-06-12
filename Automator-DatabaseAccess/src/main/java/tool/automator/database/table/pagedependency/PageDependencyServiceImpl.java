package tool.automator.database.table.pagedependency;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class PageDependencyServiceImpl implements PageDependencyService {
	@Autowired
	private PageDependencyRepository pageDependencyRepository;

	public PageDependencyDTO getPageDependencyById(Long id) {
		return pageDependencyRepository.findOne(id);
	}

	@Transactional
	public void savePageDependency(PageDependencyDTO pageDependency) {
		pageDependencyRepository.save(pageDependency);
	}

	public List<PageDependencyDTO> getAllPageDependecies() {
		return (List<PageDependencyDTO>) pageDependencyRepository.findAll();
	}

	public List<PageDependencyDTO> getPageDependencies(Long sourcePageId, Long destinationPageId) {
		return pageDependencyRepository.findBySourcePageIdAndDestinationPageId(sourcePageId, destinationPageId);
	}

	public List<PageDependencyDTO> getPossibleNextPages(Long pageId) {
		return pageDependencyRepository.findBySourcePageId(pageId);
	}

	@Transactional
	public void removePageDependency(Long id) {
		pageDependencyRepository.delete(id);
	}
}
