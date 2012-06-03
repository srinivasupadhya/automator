package tool.automator.server.servlet.getdetails;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.automator.common.db.dao.factory.DAOFactory;
import tool.automator.common.db.daoif.*;
import tool.automator.common.db.models.*;

public class GetRestrictionDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetRestrictionDetails() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String conditionOn = "";
		Integer pageId = 0, elementId = 0, elementValueId = 0;
		if (request.getParameter("CONDITION_ON") != null)
			conditionOn = request.getParameter("CONDITION_ON");
		if (request.getParameter("PAGE_ID") != null)
			pageId = Integer.parseInt(request.getParameter("PAGE_ID"));
		if (request.getParameter("ELEMENT_ID") != null)
			elementId = Integer.parseInt(request.getParameter("ELEMENT_ID"));
		if (request.getParameter("ELEMENT_VALUE_ID") != null)
			elementValueId = Integer.parseInt(request.getParameter("ELEMENT_VALUE_ID"));

		if ((conditionOn != null && !conditionOn.trim().isEmpty()) && pageId > 0 && elementId > 0) {
			if (conditionOn.equals("ELEMENT")) {
				ElementRestrictionDAOIf elementRestrictionDAO = DAOFactory.getInstance().getElementRestrictionDAO();
				List<ElementRestrictionModel> elementRestrictions = elementRestrictionDAO.getElementRestrictionsForElement(elementId);
				request.setAttribute("ELEMENT_RESTRICTIONS", elementRestrictions);
			}
			if (conditionOn.equals("ELEMENT_VALUE") && elementValueId > 0) {
				ElementValueRestrictionDAOIf elementValueRestrictionDAO = DAOFactory.getInstance().getElementValueRestrictionDAO();
				List<ElementValueRestrictionModel> elementValueRestrictions = elementValueRestrictionDAO.getElementValueRestrictionsForElementValue(elementValueId);
				request.setAttribute("ELEMENT_VALUE_RESTRICTIONS", elementValueRestrictions);
			}

			request.setAttribute("CONDITION_ON", conditionOn);
			request.setAttribute("PAGE_ID", pageId);
			request.setAttribute("ELEMENT_ID", elementId);
			request.setAttribute("ELEMENT_VALUE_ID", elementValueId);
			// get uipage name - id map
			UIPageDAOIf uiPageDAO = DAOFactory.getInstance().getUIPageDAO();
			HashMap<Integer, String> pageIdNameMap = uiPageDAO.getPageIdNameMap();
			request.setAttribute("PAGE_ID_NAME_MAP", pageIdNameMap);
			// get element name - id map
			ElementDAOIf elementDAO = DAOFactory.getInstance().getElementDAO();
			HashMap<Integer, String> elementIdNameMap = elementDAO.getElementIdNameMap();
			request.setAttribute("ELEMENT_ID_NAME_MAP", elementIdNameMap);
			// get elementvalue value - id map
			ElementValueDAOIf elementValueDAO = DAOFactory.getInstance().getElementValueDAO();
			HashMap<Integer, String> elementValueIdNameMap = elementValueDAO.getElementValueIdNameMap();
			request.setAttribute("ELEMENT_VALUE_ID_NAME_MAP", elementValueIdNameMap);
			// forward to JSP
			request.getRequestDispatcher("RestrictionDetails.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
