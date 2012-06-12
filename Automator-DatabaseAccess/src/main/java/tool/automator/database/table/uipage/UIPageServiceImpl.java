package tool.automator.database.table.uipage;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class UIPageServiceImpl implements UIPageService {
	@Autowired
	private UIPageRepository uiPageRepository;

	public UIPageDTO getPageById(Long id) {
		return uiPageRepository.findOne(id);
	}

	@Transactional
	public void savePage(UIPageDTO page) {
		uiPageRepository.save(page);
	}

	public List<UIPageDTO> getAllPages() {
		return (List<UIPageDTO>) uiPageRepository.findAll();
	}

	public Map<Long, String> getPageIdNameMap() {
		return uiPageRepository.getPageIdNameMap();
	}

	public List<String> getFilteredPageNames(Long projectId, String inputParam) {
		return uiPageRepository.getFilteredPageNames(projectId, inputParam);
	}

	public List<UIPageDTO> getAllPagesOfProject(Long projectId) {
		return uiPageRepository.getAllPagesOfProject(projectId);
	}

	public UIPageDTO getPageByName(String pageName, Long projectId) {
		return uiPageRepository.findByPageNameAndProjectId(pageName, projectId);
	}

	public List<UIPageDTO> getStartPagesOfProject(Long projectId) {
		return uiPageRepository.findByStartPageAndProjectId(true, projectId);
	}

	@Transactional
	public void removePage(Long id) {
		uiPageRepository.delete(id);
	}
}
