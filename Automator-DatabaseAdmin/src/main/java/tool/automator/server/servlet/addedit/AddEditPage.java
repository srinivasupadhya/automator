package tool.automator.server.servlet.addedit;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.automator.database.factory.DAOFactory;
import tool.automator.database.table.project.ProjectDTO;
import tool.automator.database.table.project.ProjectService;
import tool.automator.database.table.uipage.UIPageDTO;
import tool.automator.database.table.uipage.UIPageService;

public class AddEditPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddEditPage() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("ACTION");
		Long addPageAfter = 0L, editPageId = 0L;
		if (request.getParameter("ADD_AFTER_PAGE") != null)
			addPageAfter = Long.parseLong(request.getParameter("ADD_AFTER_PAGE"));
		if (request.getParameter("PAGE_ID") != null)
			editPageId = Long.parseLong(request.getParameter("PAGE_ID"));

		if ((action != null && !action.isEmpty())
				&& ((action.equals("ADD") && addPageAfter != null && addPageAfter > 0) || (action.equals("EDIT") && editPageId != null && editPageId > 0))) {
			UIPageService uiPageDAO = DAOFactory.getInstance().getUIPageService();
			if (action.equals("ADD")) {
				UIPageDTO page = uiPageDAO.getPageById(addPageAfter);
				request.setAttribute("ACTION", "ADD");
				request.setAttribute("PAGE", page);
			}
			else if (action.equals("EDIT")) {
				UIPageDTO page = uiPageDAO.getPageById(editPageId);
				request.setAttribute("ACTION", "EDIT");
				request.setAttribute("PAGE", page);
			}
			// get project name - id map
			ProjectService projectDAO = DAOFactory.getInstance().getProjectService();
			Map<Long, String> projectIdNameMap = projectDAO.getProjectIdNameMap();
			request.setAttribute("PROJECT_ID_NAME_MAP", projectIdNameMap);
			// forward to JSP
			request.getRequestDispatcher("AddEditPage.jsp").forward(request, response);
		}
		else {
			request.setAttribute("ACTION", "ADD");
			Long projectId = 0L;
			if (request.getParameter("PROJECT_ID") != null)
				projectId = Long.parseLong(request.getParameter("PROJECT_ID"));
			// get project
			ProjectService projectDAO = DAOFactory.getInstance().getProjectService();
			ProjectDTO project = projectDAO.getProjectById(projectId);
			request.setAttribute("PROJECT", project);
			// get project name - id map
			Map<Long, String> projectIdNameMap = projectDAO.getProjectIdNameMap();
			request.setAttribute("PROJECT_ID_NAME_MAP", projectIdNameMap);
			// forward to JSP
			request.getRequestDispatcher("AddEditPage.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
