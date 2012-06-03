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

public class AddEditElement extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddEditElement() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("ACTION");
		Integer addElementAfter = 0, editElementId = 0;
		if (request.getParameter("ADD_AFTER_ELEMENT") != null)
			addElementAfter = Integer.parseInt(request.getParameter("ADD_AFTER_ELEMENT"));
		if (request.getParameter("ELEMENT_ID") != null)
			editElementId = Integer.parseInt(request.getParameter("ELEMENT_ID"));
		
		if ((action != null && !action.isEmpty())
				&& ((action.equals("ADD") && addElementAfter != null && addElementAfter > 0) || (action.equals("EDIT") && editElementId != null && editElementId > 0))) {
			ElementDAOIf elementDAO = DAOFactory.getInstance().getElementDAO();
			if (action.equals("ADD")) {
				ElementModel element = elementDAO.getElementById(addElementAfter);
				request.setAttribute("ACTION", "ADD");
				request.setAttribute("ELEMENT", element);
			}
			else if (action.equals("EDIT")) {
				ElementModel element = elementDAO.getElementById(editElementId);
				request.setAttribute("ACTION", "EDIT");
				request.setAttribute("ELEMENT", element);
			}
			// get page name - id map
			UIPageDAOIf uiPageDAO = DAOFactory.getInstance().getUIPageDAO();
			HashMap<Integer, String> pageIdNameMap = uiPageDAO.getPageIdNameMap();
			request.setAttribute("PAGE_ID_NAME_MAP", pageIdNameMap);
			// forward to JSP
			request.getRequestDispatcher("AddEditElement.jsp").forward(request, response);
		}
		else {
			request.setAttribute("ACTION", "ADD");
			Integer pageId = 0;
			if (request.getParameter("PAGE_ID") != null)
				pageId = Integer.parseInt(request.getParameter("PAGE_ID"));
			// get page
			UIPageDAOIf uiPageDAO = DAOFactory.getInstance().getUIPageDAO();
			UIPageModel page = uiPageDAO.getPageById(pageId);
			request.setAttribute("PAGE", page);
			// get page name - id map
			HashMap<Integer, String> pageIdNameMap = uiPageDAO.getPageIdNameMap();
			request.setAttribute("PAGE_ID_NAME_MAP", pageIdNameMap);
			// forward to JSP
			request.getRequestDispatcher("AddEditElement.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
