package tool.automator.server.servlet.save;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.automator.database.factory.DAOFactory;
import tool.automator.database.table.element.ElementDTO;
import tool.automator.database.table.element.ElementService;
import tool.automator.database.table.elementcondition.ElementConditionDTO;
import tool.automator.database.table.elementcondition.ElementConditionService;
import tool.automator.database.table.elementvalue.ElementValueDTO;
import tool.automator.database.table.elementvalue.ElementValueService;
import tool.automator.database.table.elementvaluecondition.ElementValueConditionDTO;
import tool.automator.database.table.elementvaluecondition.ElementValueConditionService;
import tool.automator.database.table.pagecondition.PageConditionDTO;
import tool.automator.database.table.pagecondition.PageConditionService;
import tool.automator.database.table.uipage.UIPageDTO;
import tool.automator.database.table.uipage.UIPageService;

public class SaveCondition extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SaveCondition() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long conditionNumber = Long.parseLong(request.getParameter("CONDITION_NUMBER"));
		Long conditionId = 0L;
		if (request.getParameter("CONDITION_ID") != null && !request.getParameter("CONDITION_ID").trim().isEmpty())
			conditionId = Long.parseLong(request.getParameter("CONDITION_ID"));
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
		UIPageService uiPageDAO = DAOFactory.getInstance().getUIPageService();
		UIPageDTO page = uiPageDAO.getPageByName(pageName, 1L);
		// get element by element-name
		ElementService elementDAO = DAOFactory.getInstance().getElementService();
		ElementDTO element = elementDAO.getElementByScriptName(elementName, page.getId());
		// get element-value by value
		ElementValueService elementValueDAO = DAOFactory.getInstance().getElementValueService();
		ElementValueDTO elementValue = elementValueDAO.getElementValueOfElement(elementValueParam, element.getId());

		if (conditionOn.equals("PAGE")) {
			// save condition for page-dependency
			PageConditionService pageConditionDAO = DAOFactory.getInstance().getPageConditionService();
			PageConditionDTO pageCondition = pageConditionDAO.getPageConditionById(conditionId);
			pageCondition.update(conditionNumber, page.getId(), element.getId(), elementValue.getId());
			pageConditionDAO.savePageCondition(pageCondition);
		}
		else if (conditionOn.equals("ELEMENT")) {
			// save condition for element-restriction
			ElementConditionService elementConditionDAO = DAOFactory.getInstance().getElementConditionService();
			ElementConditionDTO elementCondition = elementConditionDAO.getElementConditionById(conditionId);
			elementCondition.update(conditionNumber, page.getId(), element.getId(), elementValue.getId());
			elementConditionDAO.saveElementCondition(elementCondition);
		}
		else if (conditionOn.equals("ELEMENT_VALUE")) {
			// save condition for element-value-restriction
			ElementValueConditionService elementValueConditionDAO = DAOFactory.getInstance().getElementValueConditionService();
			ElementValueConditionDTO elementValueCondition = elementValueConditionDAO.getElementValueConditionById(conditionId);
			elementValueCondition.update(conditionNumber, page.getId(), element.getId(), elementValue.getId());
			elementValueConditionDAO.saveElementValueCondition(elementValueCondition);
		}

		response.sendRedirect("GetConditionDetails?CONDITION_ON=" + conditionOn + "&SOURCE_PAGE_ID=" + sourcePageId + "&DESTINATION_PAGE_ID=" + destinationPageId
				+ "&ELEMENT_ID=" + elementId + "&ELEMENT_VALUE_ID=" + elementValueId);
	}
}
