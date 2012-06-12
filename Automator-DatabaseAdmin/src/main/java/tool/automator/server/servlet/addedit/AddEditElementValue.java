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
import tool.automator.database.table.elementvalue.ElementValueDTO;
import tool.automator.database.table.elementvalue.ElementValueService;

public class AddEditElementValue extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddEditElementValue() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("ACTION");
		Long addElementValue = 0L, editElementValueId = 0L;
		if (request.getParameter("ADD_ELEMENT_VALUE") != null)
			addElementValue = Long.parseLong(request.getParameter("ADD_ELEMENT_VALUE"));
		if (request.getParameter("ELEMENT_VALUE_ID") != null)
			editElementValueId = Long.parseLong(request.getParameter("ELEMENT_VALUE_ID"));

		if ((action != null && !action.isEmpty())
				&& ((action.equals("ADD") && addElementValue != null && addElementValue > 0) || (action.equals("EDIT") && editElementValueId != null && editElementValueId > 0))) {
			ElementValueService elementValueDAO = DAOFactory.getInstance().getElementValueService();
			if (action.equals("ADD")) {
				ElementValueDTO elementValue = elementValueDAO.getElementValueById(addElementValue);
				request.setAttribute("ACTION", "ADD");
				request.setAttribute("ELEMENT_VALUE", elementValue);
			}
			else if (action.equals("EDIT")) {
				ElementValueDTO elementValue = elementValueDAO.getElementValueById(editElementValueId);
				request.setAttribute("ACTION", "EDIT");
				request.setAttribute("ELEMENT_VALUE", elementValue);
			}
			// get element name - id map
			ElementService elementDAO = DAOFactory.getInstance().getElementService();
			Map<Long, String> elementIdNameMap = elementDAO.getElementIdNameMap();
			request.setAttribute("ELEMENT_ID_NAME_MAP", elementIdNameMap);
			// forward to JSP
			request.getRequestDispatcher("AddEditElementValue.jsp").forward(request, response);
		}
		else {
			request.setAttribute("ACTION", "ADD");
			Long elementId = 0L;
			if (request.getParameter("ELEMENT_ID") != null)
				elementId = Long.parseLong(request.getParameter("ELEMENT_ID"));
			// get element
			ElementService elementDAO = DAOFactory.getInstance().getElementService();
			ElementDTO element = elementDAO.getElementById(elementId);
			request.setAttribute("ELEMENT", element);
			// get element name - id map
			Map<Long, String> elementIdNameMap = elementDAO.getElementIdNameMap();
			request.setAttribute("ELEMENT_ID_NAME_MAP", elementIdNameMap);
			// forward to JSP
			request.getRequestDispatcher("AddEditElementValue.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
