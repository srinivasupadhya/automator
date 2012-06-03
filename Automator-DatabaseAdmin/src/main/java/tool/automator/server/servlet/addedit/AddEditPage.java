package tool.automator.server.servlet.addedit;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.automator.common.db.dao.factory.DAOFactory;
import tool.automator.common.db.daoif.*;
import tool.automator.common.db.models.*;

public class AddEditPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddEditPage() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("ACTION");
		Integer addPageAfter = 0, editPageId = 0;
		if (request.getParameter("ADD_AFTER_PAGE") != null)
			addPageAfter = Integer.parseInt(request.getParameter("ADD_AFTER_PAGE"));
		if (request.getParameter("PAGE_ID") != null)
			editPageId = Integer.parseInt(request.getParameter("PAGE_ID"));
		
		if ((action != null && !action.isEmpty())
				&& ((action.equals("ADD") && addPageAfter != null && addPageAfter > 0) || (action.equals("EDIT") && editPageId != null && editPageId > 0))) {
			UIPageDAOIf uiPageDAO = DAOFactory.getInstance().getUIPageDAO();
			if (action.equals("ADD")) {
				UIPageModel page = uiPageDAO.getPageById(addPageAfter);
				request.setAttribute("ACTION", "ADD");
				request.setAttribute("PAGE", page);
			}
			else if (action.equals("EDIT")) {
				UIPageModel page = uiPageDAO.getPageById(editPageId);
				request.setAttribute("ACTION", "EDIT");
				request.setAttribute("PAGE", page);
			}
			// get project name - id map
			ProjectDAOIf projectDAO = DAOFactory.getInstance().getProjectDAO();
			HashMap<Integer, String> projectIdNameMap = projectDAO.getProjectIdNameMap();
			request.setAttribute("PROJECT_ID_NAME_MAP", projectIdNameMap);
			// forward to JSP
			request.getRequestDispatcher("AddEditPage.jsp").forward(request, response);
		}
		else {
			request.setAttribute("ACTION", "ADD");
			Integer projectId = 0;
			if (request.getParameter("PROJECT_ID") != null)
				projectId = Integer.parseInt(request.getParameter("PROJECT_ID"));
			// get project
			ProjectDAOIf projectDAO = DAOFactory.getInstance().getProjectDAO();
			ProjectModel project = projectDAO.getProjectById(projectId);
			request.setAttribute("PROJECT", project);
			// get project name - id map
			HashMap<Integer, String> projectIdNameMap = projectDAO.getProjectIdNameMap();
			request.setAttribute("PROJECT_ID_NAME_MAP", projectIdNameMap);
			// forward to JSP
			request.getRequestDispatcher("AddEditPage.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
