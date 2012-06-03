package tool.automator.server.servlet.getdetails;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.automator.common.db.dao.factory.DAOFactory;
import tool.automator.common.db.daoif.*;
import tool.automator.common.db.models.*;
import tool.automator.client.framework.models.holders.ProjectDetailsModel;

public class GetProjectDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetProjectDetails() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer projectID = 0;
		projectID = Integer.parseInt(request.getParameter("PROJECT_ID"));
		if (projectID != null && projectID > 0) {
			// get project details
			ProjectDAOIf projectDAO = DAOFactory.getInstance().getProjectDAO();
			ProjectModel project = projectDAO.getProjectById(projectID);
			ProjectDetailsModel projectDetails = new ProjectDetailsModel(project);
			request.setAttribute("PROJECT_DETAILS", projectDetails);
			// get uipage name - id map
			UIPageDAOIf uiPageDAO = DAOFactory.getInstance().getUIPageDAO();
			HashMap<Integer, String> pageIdNameMap = uiPageDAO.getPageIdNameMap();
			request.setAttribute("PAGE_ID_NAME_MAP", pageIdNameMap);
			// forward to JSP
			request.getRequestDispatcher("ProjectDetails.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
