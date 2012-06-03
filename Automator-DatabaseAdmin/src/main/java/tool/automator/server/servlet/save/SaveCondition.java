package tool.automator.server.servlet.save;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.automator.common.db.dao.factory.DAOFactory;
import tool.automator.common.db.daoif.*;
import tool.automator.common.db.models.*;

public class SaveCondition extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SaveCondition() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int conditionNumber = Integer.parseInt(request.getParameter("CONDITION_NUMBER"));
		int conditionId = 0;
		if (request.getParameter("CONDITION_ID") != null && !request.getParameter("CONDITION_ID").trim().isEmpty())
			conditionId = Integer.parseInt(request.getParameter("CONDITION_ID"));
		String pageName = request.getParameter("pageSearchBox");
		String elementName = request.getParameter("elementSearchBox");
		String elementValueParam = request.getParameter("elementValueSearchBox");
		String conditionOn = request.getParameter("CONDITION_ON");
		Integer sourcePageId = 0;
		if (request.getParameter("SOURCE_PAGE_ID") != null && !request.getParameter("SOURCE_PAGE_ID").trim().isEmpty())
			sourcePageId = Integer.parseInt(request.getParameter("SOURCE_PAGE_ID"));
		Integer destinationPageId = 0;
		if (request.getParameter("DESTINATION_PAGE_ID") != null && !request.getParameter("DESTINATION_PAGE_ID").trim().isEmpty())
			destinationPageId = Integer.parseInt(request.getParameter("DESTINATION_PAGE_ID"));
		Integer elementId = 0;
		if (request.getParameter("ELEMENT_ID") != null && !request.getParameter("ELEMENT_ID").trim().isEmpty())
			elementId = Integer.parseInt(request.getParameter("ELEMENT_ID"));
		Integer elementValueId = 0;
		if (request.getParameter("ELEMENT_VALUE_ID") != null && !request.getParameter("ELEMENT_VALUE_ID").trim().isEmpty())
			elementValueId = Integer.parseInt(request.getParameter("ELEMENT_VALUE_ID"));

		// get page by page-name
		UIPageDAOIf uiPageDAO = DAOFactory.getInstance().getUIPageDAO();
		UIPageModel page = uiPageDAO.getPageByName(pageName, 1);
		// get element by element-name
		ElementDAOIf elementDAO = DAOFactory.getInstance().getElementDAO();
		ElementModel element = elementDAO.getElementByScriptName(elementName, page.getId());
		// get element-value by value
		ElementValueDAOIf elementValueDAO = DAOFactory.getInstance().getElementValueDAO();
		ElementValueModel elementValue = elementValueDAO.getElementValueOfElement(elementValueParam, element.getId());

		if (conditionOn.equals("PAGE")) {
			// save condition for page-dependency
			PageConditionDAOIf pageConditionDAO = DAOFactory.getInstance().getPageConditionDAO();
			PageConditionModel pageCondition = new PageConditionModel(conditionNumber, page.getId(), element.getId(), elementValue.getId());
			pageCondition.setId(conditionId);
			pageConditionDAO.savePageCondition(pageCondition);
		}
		else if (conditionOn.equals("ELEMENT")) {
			// save condition for element-restriction
			ElementConditionDAOIf elementConditionDAO = DAOFactory.getInstance().getElementConditionDAO();
			ElementConditionModel elementCondition = new ElementConditionModel(conditionNumber, page.getId(), element.getId(), elementValue.getId());
			elementCondition.setId(conditionId);
			elementConditionDAO.saveElementCondition(elementCondition);
		}
		else if (conditionOn.equals("ELEMENT_VALUE")) {
			// save condition for element-value-restriction
			ElementValueConditionDAOIf elementValueConditionDAO = DAOFactory.getInstance().getElementValueConditionDAO();
			ElementValueConditionModel elementValueCondition = new ElementValueConditionModel(conditionNumber, page.getId(), element.getId(), elementValue.getId());
			elementValueCondition.setId(conditionId);
			elementValueConditionDAO.saveElementValueCondition(elementValueCondition);
		}

		response.sendRedirect("GetConditionDetails?CONDITION_ON=" + conditionOn + "&SOURCE_PAGE_ID=" + sourcePageId + "&DESTINATION_PAGE_ID=" + destinationPageId
				+ "&ELEMENT_ID=" + elementId + "&ELEMENT_VALUE_ID=" + elementValueId);
	}
}
