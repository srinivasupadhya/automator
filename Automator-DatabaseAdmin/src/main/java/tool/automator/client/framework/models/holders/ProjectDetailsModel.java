package tool.automator.client.framework.models.holders;

import java.util.ArrayList;
import java.util.List;

import tool.automator.database.factory.DAOFactory;
import tool.automator.database.table.project.ProjectDTO;
import tool.automator.database.table.uipage.UIPageDTO;
import tool.automator.database.table.uipage.UIPageService;

public class ProjectDetailsModel {
	private ProjectDTO project;
	private List<PageDetailsModel> pageDetailsList;

	public ProjectDetailsModel(ProjectDTO project) {
		this.project = project;
		pageDetailsList = new ArrayList<PageDetailsModel>();
		UIPageService uiPageDAO = DAOFactory.getInstance().getUIPageService();
		List<UIPageDTO> pageList = uiPageDAO.getAllPagesOfProject(project.getId());
		for (int i = 0; i < pageList.size(); i++)
			pageDetailsList.add(new PageDetailsModel(pageList.get(i)));
	}

	public void setProject(ProjectDTO project) {
		this.project = project;
	}

	public ProjectDTO getProject() {
		return project;
	}

	public void setPageDetailsList(List<PageDetailsModel> pageDetailsList) {
		this.pageDetailsList = pageDetailsList;
	}

	public List<PageDetailsModel> getPageDetailsList() {
		return pageDetailsList;
	}
}
