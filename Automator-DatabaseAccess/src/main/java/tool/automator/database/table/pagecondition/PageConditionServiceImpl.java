package tool.automator.database.table.pagecondition;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class PageConditionServiceImpl implements PageConditionService {
	@Autowired
	private PageConditionRepository pageConditionRepository;

	public PageConditionDTO getPageConditionById(Long id) {
		return pageConditionRepository.findOne(id);
	}

	@Transactional
	public void savePageCondition(PageConditionDTO condition) {
		pageConditionRepository.save(condition);
	}

	public List<PageConditionDTO> getAllPageConditions() {
		return (List<PageConditionDTO>) pageConditionRepository.findAll();
	}

	public List<PageConditionDTO> getPageConditionsByPageDependencyId(Long pageDependencyId) {
		return pageConditionRepository.findByPageDependencyId(pageDependencyId);
	}

	@Transactional
	public void removePageCondition(Long id) {
		pageConditionRepository.delete(id);
	}
}
