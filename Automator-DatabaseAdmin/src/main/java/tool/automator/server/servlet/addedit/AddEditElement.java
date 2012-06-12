package tool.automator.server.servlet.addedit;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.automator.database.factory.DAOFactory;
import tool.automator.database.table.element.ElementDTO;
import tool.automator.database.table.element.ElementService;
import tool.automator.database.table.uipage.UIPageDTO;
import tool.automator.database.table.uipage.UIPageService;

public class AddEditElement extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddEditElement() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("ACTION");
		Long addElementAfter = 0L, editElementId = 0L;
		if (request.getParameter("ADD_AFTER_ELEMENT") != null)
			addElementAfter = Long.parseLong(request.getParameter("ADD_AFTER_ELEMENT"));
		if (request.getParameter("ELEMENT_ID") != null)
			editElementId = Long.parseLong(request.getParameter("ELEMENT_ID"));

		if ((action != null && !action.isEmpty())
				&& ((action.equals("ADD") && addElementAfter != null && addElementAfter > 0) || (action.equals("EDIT") && editElementId != null && editElementId > 0))) {
			ElementService elementDAO = DAOFactory.getInstance().getElementService();
			if (action.equals("ADD")) {
				ElementDTO element = elementDAO.getElementById(addElementAfter);
				request.setAttribute("ACTION", "ADD");
				request.setAttribute("ELEMENT", element);
			}
			else if (action.equals("EDIT")) {
				ElementDTO element = elementDAO.getElementById(editElementId);
				request.setAttribute("ACTION", "EDIT");
				request.setAttribute("ELEMENT", element);
			}
			// get page name - id map
			UIPageService uiPageDAO = DAOFactory.getInstance().getUIPageService();
			Map<Long, String> pageIdNameMap = uiPageDAO.getPageIdNameMap();
			request.setAttribute("PAGE_ID_NAME_MAP", pageIdNameMap);
			// forward to JSP
			request.getRequestDispatcher("AddEditElement.jsp").forward(request, response);
		}
		else {
			request.setAttribute("ACTION", "ADD");
			Long pageId = 0L;
			if (request.getParameter("PAGE_ID") != null)
				pageId = Long.parseLong(request.getParameter("PAGE_ID"));
			// get page
			UIPageService uiPageDAO = DAOFactory.getInstance().getUIPageService();
			UIPageDTO page = uiPageDAO.getPageById(pageId);
			request.setAttribute("PAGE", page);
			// get page name - id map
			Map<Long, String> pageIdNameMap = uiPageDAO.getPageIdNameMap();
			request.setAttribute("PAGE_ID_NAME_MAP", pageIdNameMap);
			// forward to JSP
			request.getRequestDispatcher("AddEditElement.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
