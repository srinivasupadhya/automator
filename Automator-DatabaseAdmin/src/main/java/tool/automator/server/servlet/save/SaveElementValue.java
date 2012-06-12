package tool.automator.server.servlet.save;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.automator.database.factory.DAOFactory;
import tool.automator.database.table.element.ElementDTO;
import tool.automator.database.table.element.ElementService;
import tool.automator.database.table.elementvalue.ElementValueDTO;
import tool.automator.database.table.elementvalue.ElementValueService;

public class SaveElementValue extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SaveElementValue() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long elementId = Long.parseLong(request.getParameter("ELEMENT_ID"));
		Long elementValueId = 0L;
		if (request.getParameter("ELEMENT_VALUE_ID") != null && !request.getParameter("ELEMENT_VALUE_ID").trim().isEmpty())
			elementValueId = Long.parseLong(request.getParameter("ELEMENT_VALUE_ID"));
		String scriptValue = request.getParameter("SCRIPT_VALUE");
		String actualValue = request.getParameter("ACTUAL_VALUE");
		boolean turnsPage = Boolean.parseBoolean(request.getParameter("TURNS_PAGE"));
		boolean hidden = Boolean.parseBoolean(request.getParameter("HIDDEN"));

		// save element-value
		ElementValueService elementValueDAO = DAOFactory.getInstance().getElementValueService();
		ElementValueDTO elementValue = elementValueDAO.getElementValueById(elementValueId);
		elementValue.update(elementId, scriptValue, actualValue, turnsPage, hidden);
		elementValueDAO.saveElementValue(elementValue);

		// get element to find page-id
		ElementService elementDAO = DAOFactory.getInstance().getElementService();
		ElementDTO element = elementDAO.getElementById(elementId);
		// forward to JSP
		response.sendRedirect("GetPageDetails?PAGE_ID=" + element.getPageId());
	}
}
