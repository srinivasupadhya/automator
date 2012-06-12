package tool.automator.server.servlet.getdetails;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.automator.client.framework.models.holders.ProjectDetailsModel;
import tool.automator.database.factory.DAOFactory;
import tool.automator.database.table.project.ProjectDTO;
import tool.automator.database.table.project.ProjectService;
import tool.automator.database.table.uipage.UIPageService;

public class GetProjectDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetProjectDetails() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long projectID = 0L;
		projectID = Long.parseLong(request.getParameter("PROJECT_ID"));
		if (projectID != null && projectID > 0) {
			// get project details
			ProjectService projectDAO = DAOFactory.getInstance().getProjectService();
			ProjectDTO project = projectDAO.getProjectById(projectID);
			ProjectDetailsModel projectDetails = new ProjectDetailsModel(project);
			request.setAttribute("PROJECT_DETAILS", projectDetails);
			// get uipage name - id map
			UIPageService uiPageDAO = DAOFactory.getInstance().getUIPageService();
			Map<Long, String> pageIdNameMap = uiPageDAO.getPageIdNameMap();
			request.setAttribute("PAGE_ID_NAME_MAP", pageIdNameMap);
			// forward to JSP
			request.getRequestDispatcher("ProjectDetails.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
