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
import tool.automator.common.models.interfaces.ConditionIf;

public class AddEditCondition extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddEditCondition() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String conditionOn = "";
		Integer sourcePageId = 0, destinationPageId = 0, elementId = 0, elementValueId = 0;
		Integer addCondition = 0, editConditionId = 0;
		String action = request.getParameter("ACTION");
		if (request.getParameter("ADD_CONDITION") != null)
			addCondition = Integer.parseInt(request.getParameter("ADD_CONDITION"));
		if (request.getParameter("CONDITION_ID") != null)
			editConditionId = Integer.parseInt(request.getParameter("CONDITION_ID"));
		if (request.getParameter("CONDITION_ON") != null)
			conditionOn = request.getParameter("CONDITION_ON");
		if (request.getParameter("SOURCE_PAGE_ID") != null)
			sourcePageId = Integer.parseInt(request.getParameter("SOURCE_PAGE_ID"));
		if (request.getParameter("DESTINATION_PAGE_ID") != null)
			destinationPageId = Integer.parseInt(request.getParameter("DESTINATION_PAGE_ID"));
		if (request.getParameter("ELEMENT_ID") != null)
			elementId = Integer.parseInt(request.getParameter("ELEMENT_ID"));
		if (request.getParameter("ELEMENT_VALUE_ID") != null)
			elementValueId = Integer.parseInt(request.getParameter("ELEMENT_VALUE_ID"));

		if ((action != null && !action.isEmpty()) && ((action.equals("ADD") && addCondition > 0) || (action.equals("EDIT") && editConditionId > 0))) {
			ConditionIf condition = null;
			int conditionId = 0;
			if (action.equals("ADD"))
				conditionId = addCondition;
			else if (action.equals("EDIT"))
				conditionId = editConditionId;

			if (conditionOn.equals("PAGE")) {
				PageConditionDAOIf pageConditionDAO = DAOFactory.getInstance().getPageConditionDAO();
				condition = pageConditionDAO.getPageConditionById(conditionId);
			}
			else if (conditionOn.equals("ELEMENT")) {
				ElementConditionDAOIf elementConditonDAO = DAOFactory.getInstance().getElementConditionDAO();
				condition = elementConditonDAO.getElementConditionById(conditionId);
			}
			else if (conditionOn.equals("ELEMENT_VALUE")) {
				ElementValueConditionDAOIf elementValueConditionDAO = DAOFactory.getInstance().getElementValueConditionDAO();
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
			UIPageDAOIf uiPageDAO = DAOFactory.getInstance().getUIPageDAO();
			HashMap<Integer, String> pageIdNameMap = uiPageDAO.getPageIdNameMap();
			request.setAttribute("PAGE_ID_NAME_MAP", pageIdNameMap);
			// get element name - id map
			ElementDAOIf elementDAO = DAOFactory.getInstance().getElementDAO();
			HashMap<Integer, String> elementIdNameMap = elementDAO.getElementIdNameMap();
			request.setAttribute("ELEMENT_ID_NAME_MAP", elementIdNameMap);
			// get element-value value - id map
			ElementValueDAOIf elementValueDAO = DAOFactory.getInstance().getElementValueDAO();
			HashMap<Integer, String> elementValueIdNameMap = elementValueDAO.getElementValueIdNameMap();
			request.setAttribute("ELEMENT_VALUE_ID_NAME_MAP", elementValueIdNameMap);
			// forward to JSP
			request.getRequestDispatcher("AddEditCondition.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
