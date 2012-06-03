package tool.automator.client.framework.models.holders;

import java.util.ArrayList;
import java.util.List;

import tool.automator.common.db.dao.factory.DAOFactory;
import tool.automator.common.db.daoif.*;
import tool.automator.common.db.models.*;

public class ProjectDetailsModel {
	private ProjectModel project;
	private List<PageDetailsModel> pageDetailsList;

	public ProjectDetailsModel(ProjectModel project) {
		this.project = project;
		pageDetailsList = new ArrayList<PageDetailsModel>();
		UIPageDAOIf uiPageDAO = DAOFactory.getInstance().getUIPageDAO();
		List<UIPageModel> pageList = uiPageDAO.getAllPagesOfProject(project.getId());
		for (int i = 0; i < pageList.size(); i++)
			pageDetailsList.add(new PageDetailsModel(pageList.get(i)));
	}

	public void setProject(ProjectModel project) {
		this.project = project;
	}

	public ProjectModel getProject() {
		return project;
	}

	public void setPageDetailsList(List<PageDetailsModel> pageDetailsList) {
		this.pageDetailsList = pageDetailsList;
	}

	public List<PageDetailsModel> getPageDetailsList() {
		return pageDetailsList;
	}
}
