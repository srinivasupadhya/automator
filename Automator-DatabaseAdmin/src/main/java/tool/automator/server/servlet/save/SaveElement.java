package tool.automator.server.servlet.save;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.automator.database.factory.DAOFactory;
import tool.automator.database.table.element.ElementDTO;
import tool.automator.database.table.element.ElementService;

public class SaveElement extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SaveElement() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long pageId = Long.parseLong(request.getParameter("PAGE_ID"));
		Long elementId = 0L;
		if (request.getParameter("ELEMENT_ID") != null && !request.getParameter("ELEMENT_ID").trim().isEmpty())
			elementId = Long.parseLong(request.getParameter("ELEMENT_ID"));
		String scriptName = request.getParameter("SCRIPT_NAME");
		String uiIdentifier = request.getParameter("UI_IDENTIFIER");
		String uiElementGetType = request.getParameter("UI_ELEMENT_GET_TYPE");
		String uiElementType = request.getParameter("UI_ELEMENT_TYPE");
		int relativeOrder = Integer.parseInt(request.getParameter("RELATIVE_ORDER"));
		int release = Integer.parseInt(request.getParameter("RELEASE"));
		boolean optional = Boolean.parseBoolean(request.getParameter("OPTIONAL"));
		boolean hidden = Boolean.parseBoolean(request.getParameter("HIDDEN"));

		// save element
		ElementService elementDAO = DAOFactory.getInstance().getElementService();
		ElementDTO element = elementDAO.getElementById(elementId);
		element.update(scriptName, pageId, uiIdentifier, uiElementGetType, uiElementType, relativeOrder, release, optional, hidden);
		elementDAO.saveElement(element);

		// forward to JSP
		response.sendRedirect("GetPageDetails?PAGE_ID=" + pageId);
	}
}
