package tool.automator.server.servlet.addedit;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.automator.database.factory.DAOFactory;
import tool.automator.database.table.ConditionIf;
import tool.automator.database.table.element.ElementService;
import tool.automator.database.table.elementcondition.ElementConditionService;
import tool.automator.database.table.elementvalue.ElementValueService;
import tool.automator.database.table.elementvaluecondition.ElementValueConditionService;
import tool.automator.database.table.pagecondition.PageConditionService;
import tool.automator.database.table.uipage.UIPageService;

public class AddEditCondition extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddEditCondition() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String conditionOn = "";
		Long sourcePageId = 0L, destinationPageId = 0L, elementId = 0L, elementValueId = 0L;
		Long addCondition = 0L, editConditionId = 0L;
		String action = request.getParameter("ACTION");
		if (request.getParameter("ADD_CONDITION") != null)
			addCondition = Long.parseLong(request.getParameter("ADD_CONDITION"));
		if (request.getParameter("CONDITION_ID") != null)
			editConditionId = Long.parseLong(request.getParameter("CONDITION_ID"));
		if (request.getParameter("CONDITION_ON") != null)
			conditionOn = request.getParameter("CONDITION_ON");
		if (request.getParameter("SOURCE_PAGE_ID") != null)
			sourcePageId = Long.parseLong(request.getParameter("SOURCE_PAGE_ID"));
		if (request.getParameter("DESTINATION_PAGE_ID") != null)
			destinationPageId = Long.parseLong(request.getParameter("DESTINATION_PAGE_ID"));
		if (request.getParameter("ELEMENT_ID") != null)
			elementId = Long.parseLong(request.getParameter("ELEMENT_ID"));
		if (request.getParameter("ELEMENT_VALUE_ID") != null)
			elementValueId = Long.parseLong(request.getParameter("ELEMENT_VALUE_ID"));

		if ((action != null && !action.isEmpty()) && ((action.equals("ADD") && addCondition > 0) || (action.equals("EDIT") && editConditionId > 0))) {
			ConditionIf condition = null;
			Long conditionId = 0L;
			if (action.equals("ADD"))
				conditionId = addCondition;
			else if (action.equals("EDIT"))
				conditionId = editConditionId;

			if (conditionOn.equals("PAGE")) {
				PageConditionService pageConditionDAO = DAOFactory.getInstance().getPageConditionService();
				condition = pageConditionDAO.getPageConditionById(conditionId);
			}
			else if (conditionOn.equals("ELEMENT")) {
				ElementConditionService elementConditonDAO = DAOFactory.getInstance().getElementConditionService();
				condition = elementConditonDAO.getElementConditionById(conditionId);
			}
			else if (conditionOn.equals("ELEMENT_VALUE")) {
				ElementValueConditionService elementValueConditionDAO = DAOFactory.getInstance().getElementValueConditionService();
				condition = elementValueConditionDAO.getElementValueConditionById(conditionId);
			}

			if (action.equals("ADD"))
				request.setAttribute("ACTION", "ADD");
			else if (action.equals("EDIT"))
				request.setAttribute("ACTION", "EDIT");
			request.setAttribute("CONDITION", condition);

			request.setAttribute("CONDITION_ON", conditionOn);
			request.setAttribute("SOURCE_PAGE_ID", sourcePageId);
			request.setAttribute("DESTINATION_PAGE_ID", destinationPageId);
			request.setAttribute("ELEMENT_ID", elementId);
			request.setAttribute("ELEMENT_VALUE_ID", elementValueId);

			// get page name - id map
			UIPageService uiPageDAO = DAOFactory.getInstance().getUIPageService();
			Map<Long, String> pageIdNameMap = uiPageDAO.getPageIdNameMap();
			request.setAttribute("PAGE_ID_NAME_MAP", pageIdNameMap);
			// get element name - id map
			ElementService elementDAO = DAOFactory.getInstance().getElementService();
			Map<Long, String> elementIdNameMap = elementDAO.getElementIdNameMap();
			request.setAttribute("ELEMENT_ID_NAME_MAP", elementIdNameMap);
			// get element-value value - id map
			ElementValueService elementValueDAO = DAOFactory.getInstance().getElementValueService();
			Map<Long, String> elementValueIdNameMap = elementValueDAO.getElementValueIdNameMap();
			request.setAttribute("ELEMENT_VALUE_ID_NAME_MAP", elementValueIdNameMap);
			// forward to JSP
			request.getRequestDispatcher("AddEditCondition.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
