package tool.automator.server.servlet.save;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.automator.common.db.dao.factory.DAOFactory;
import tool.automator.common.db.daoif.*;
import tool.automator.common.db.models.*;

public class SaveElementValue extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SaveElementValue() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int elementId = Integer.parseInt(request.getParameter("ELEMENT_ID"));
		int elementValueId = 0;
		if (request.getParameter("ELEMENT_VALUE_ID") != null && !request.getParameter("ELEMENT_VALUE_ID").trim().isEmpty())
			elementValueId = Integer.parseInt(request.getParameter("ELEMENT_VALUE_ID"));
		String scriptValue = request.getParameter("SCRIPT_VALUE");
		String actualValue = request.getParameter("ACTUAL_VALUE");
		boolean turnsPage = Boolean.parseBoolean(request.getParameter("TURNS_PAGE"));
		boolean hidden = Boolean.parseBoolean(request.getParameter("HIDDEN"));

		// save element-value
		ElementValueDAOIf elementValueDAO = DAOFactory.getInstance().getElementValueDAO();
		ElementValueModel elementValue = new ElementValueModel(elementId, scriptValue, actualValue, turnsPage, hidden);
		elementValue.setId(elementValueId);
		elementValueDAO.saveElementValue(elementValue);
		// get element to find page-id
		ElementDAOIf elementDAO = DAOFactory.getInstance().getElementDAO();
		ElementModel element = elementDAO.getElementById(elementId);
		// forward to JSP
		response.sendRedirect("GetPageDetails?PAGE_ID=" + element.getPageId());
	}
}
