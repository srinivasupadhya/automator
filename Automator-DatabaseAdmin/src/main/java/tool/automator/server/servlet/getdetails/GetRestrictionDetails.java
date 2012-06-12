package tool.automator.server.servlet.getdetails;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.automator.database.factory.DAOFactory;
import tool.automator.database.table.element.ElementService;
import tool.automator.database.table.elementrestriction.ElementRestrictionDTO;
import tool.automator.database.table.elementrestriction.ElementRestrictionService;
import tool.automator.database.table.elementvalue.ElementValueService;
import tool.automator.database.table.elementvaluerestriction.ElementValueRestrictionDTO;
import tool.automator.database.table.elementvaluerestriction.ElementValueRestrictionService;
import tool.automator.database.table.uipage.UIPageService;

public class GetRestrictionDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetRestrictionDetails() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String conditionOn = "";
		Long pageId = 0L, elementId = 0L, elementValueId = 0L;
		if (request.getParameter("CONDITION_ON") != null)
			conditionOn = request.getParameter("CONDITION_ON");
		if (request.getParameter("PAGE_ID") != null)
			pageId = Long.parseLong(request.getParameter("PAGE_ID"));
		if (request.getParameter("ELEMENT_ID") != null)
			elementId = Long.parseLong(request.getParameter("ELEMENT_ID"));
		if (request.getParameter("ELEMENT_VALUE_ID") != null)
			elementValueId = Long.parseLong(request.getParameter("ELEMENT_VALUE_ID"));

		if ((conditionOn != null && !conditionOn.trim().isEmpty()) && pageId > 0 && elementId > 0) {
			if (conditionOn.equals("ELEMENT")) {
				ElementRestrictionService elementRestrictionDAO = DAOFactory.getInstance().getElementRestrictionService();
				List<ElementRestrictionDTO> elementRestrictions = elementRestrictionDAO.getElementRestrictionsForElement(elementId);
				request.setAttribute("ELEMENT_RESTRICTIONS", elementRestrictions);
			}
			if (conditionOn.equals("ELEMENT_VALUE") && elementValueId > 0) {
				ElementValueRestrictionService elementValueRestrictionDAO = DAOFactory.getInstance().getElementValueRestrictionService();
				List<ElementValueRestrictionDTO> elementValueRestrictions = elementValueRestrictionDAO.getElementValueRestrictionsForElementValue(elementValueId);
				request.setAttribute("ELEMENT_VALUE_RESTRICTIONS", elementValueRestrictions);
			}

			request.setAttribute("CONDITION_ON", conditionOn);
			request.setAttribute("PAGE_ID", pageId);
			request.setAttribute("ELEMENT_ID", elementId);
			request.setAttribute("ELEMENT_VALUE_ID", elementValueId);
			// get uipage name - id map
			UIPageService uiPageDAO = DAOFactory.getInstance().getUIPageService();
			Map<Long, String> pageIdNameMap = uiPageDAO.getPageIdNameMap();
			request.setAttribute("PAGE_ID_NAME_MAP", pageIdNameMap);
			// get element name - id map
			ElementService elementDAO = DAOFactory.getInstance().getElementService();
			Map<Long, String> elementIdNameMap = elementDAO.getElementIdNameMap();
			request.setAttribute("ELEMENT_ID_NAME_MAP", elementIdNameMap);
			// get elementvalue value - id map
			ElementValueService elementValueDAO = DAOFactory.getInstance().getElementValueService();
			Map<Long, String> elementValueIdNameMap = elementValueDAO.getElementValueIdNameMap();
			request.setAttribute("ELEMENT_VALUE_ID_NAME_MAP", elementValueIdNameMap);
			// forward to JSP
			request.getRequestDispatcher("RestrictionDetails.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
