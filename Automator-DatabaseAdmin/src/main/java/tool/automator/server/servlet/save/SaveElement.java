package tool.automator.server.servlet.save;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.automator.common.db.dao.factory.DAOFactory;
import tool.automator.common.db.daoif.*;
import tool.automator.common.db.models.*;

public class SaveElement extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SaveElement() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pageId = Integer.parseInt(request.getParameter("PAGE_ID"));
		int elementId = 0;
		if (request.getParameter("ELEMENT_ID") != null && !request.getParameter("ELEMENT_ID").trim().isEmpty())
			elementId = Integer.parseInt(request.getParameter("ELEMENT_ID"));
		String scriptName = request.getParameter("SCRIPT_NAME");
		String uiIdentifier = request.getParameter("UI_IDENTIFIER");
		String uiElementGetType = request.getParameter("UI_ELEMENT_GET_TYPE");
		String uiElementType = request.getParameter("UI_ELEMENT_TYPE");
		int relativeOrder = Integer.parseInt(request.getParameter("RELATIVE_ORDER"));
		int release = Integer.parseInt(request.getParameter("RELEASE"));
		boolean optional = Boolean.parseBoolean(request.getParameter("OPTIONAL"));
		boolean hidden = Boolean.parseBoolean(request.getParameter("HIDDEN"));

		// save element
		ElementDAOIf elementDAO = DAOFactory.getInstance().getElementDAO();
		ElementModel element = new ElementModel(scriptName, pageId, uiIdentifier, uiElementGetType, uiElementType, relativeOrder, release, optional, hidden);
		element.setId(elementId);
		elementDAO.saveElement(element);
		// forward to JSP
		response.sendRedirect("GetPageDetails?PAGE_ID=" + pageId);
	}
}
