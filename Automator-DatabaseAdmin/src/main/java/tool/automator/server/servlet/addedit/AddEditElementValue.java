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

public class AddEditElementValue extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddEditElementValue() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("ACTION");
		Integer addElementValue = 0, editElementValueId = 0;
		if (request.getParameter("ADD_ELEMENT_VALUE") != null)
			addElementValue = Integer.parseInt(request.getParameter("ADD_ELEMENT_VALUE"));
		if (request.getParameter("ELEMENT_VALUE_ID") != null)
			editElementValueId = Integer.parseInt(request.getParameter("ELEMENT_VALUE_ID"));
		
		if ((action != null && !action.isEmpty())
				&& ((action.equals("ADD") && addElementValue != null && addElementValue > 0) || (action.equals("EDIT") && editElementValueId != null && editElementValueId > 0))) {
			ElementValueDAOIf elementValueDAO = DAOFactory.getInstance().getElementValueDAO();
			if (action.equals("ADD")) {
				ElementValueModel elementValue = elementValueDAO.getElementValueById(addElementValue);
				request.setAttribute("ACTION", "ADD");
				request.setAttribute("ELEMENT_VALUE", elementValue);
			}
			else if (action.equals("EDIT")) {
				ElementValueModel elementValue = elementValueDAO.getElementValueById(editElementValueId);
				request.setAttribute("ACTION", "EDIT");
				request.setAttribute("ELEMENT_VALUE", elementValue);
			}
			// get element name - id map
			ElementDAOIf elementDAO = DAOFactory.getInstance().getElementDAO();
			HashMap<Integer, String> elementIdNameMap = elementDAO.getElementIdNameMap();
			request.setAttribute("ELEMENT_ID_NAME_MAP", elementIdNameMap);
			// forward to JSP
			request.getRequestDispatcher("AddEditElementValue.jsp").forward(request, response);
		}
		else {
			request.setAttribute("ACTION", "ADD");
			Integer elementId = 0;
			if (request.getParameter("ELEMENT_ID") != null)
				elementId = Integer.parseInt(request.getParameter("ELEMENT_ID"));
			// get element
			ElementDAOIf elementDAO = DAOFactory.getInstance().getElementDAO();
			ElementModel element = elementDAO.getElementById(elementId);
			request.setAttribute("ELEMENT", element);
			// get element name - id map
			HashMap<Integer, String> elementIdNameMap = elementDAO.getElementIdNameMap();
			request.setAttribute("ELEMENT_ID_NAME_MAP", elementIdNameMap);
			// forward to JSP
			request.getRequestDispatcher("AddEditElementValue.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
