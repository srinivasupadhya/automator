package tool.automator.server.servlet.getdetails;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

public class GetConditionDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetConditionDetails() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String conditionOn = "";
		Long conditionNumber = 0L;
		if (request.getParameter("CONDITION_ON") != null)
			conditionOn = request.getParameter("CONDITION_ON");
		if (request.getParameter("CONDITION_NUMBER") != null)
			conditionNumber = Long.parseLong(request.getParameter("CONDITION_NUMBER"));

		List<? extends ConditionIf> conditions = new ArrayList<ConditionIf>();

		if ((conditionOn != null && !conditionOn.isEmpty()) && conditionNumber > 0
				&& (conditionOn.equals("PAGE") || conditionOn.equals("ELEMENT") || conditionOn.equals("ELEMENT_VALUE"))) {
			if (conditionOn.equals("PAGE")) {
				PageConditionService pageConditionDAO = DAOFactory.getInstance().getPageConditionService();
				conditions = pageConditionDAO.getPageConditionsByPageDependencyId(conditionNumber);
			}
			else if (conditionOn.equals("ELEMENT")) {
				ElementConditionService elementConditionDAO = DAOFactory.getInstance().getElementConditionService();
				conditions = elementConditionDAO.getElementConditionsByElementRestrictionId(conditionNumber);
			}
			else if (conditionOn.equals("ELEMENT_VALUE")) {
				ElementValueConditionService elementValueConditonDAO = DAOFactory.getInstance().getElementValueConditionService();
				conditions = elementValueConditonDAO.getElementValueConditionsByElementValueRestrictionId(conditionNumber);
			}

			request.setAttribute("CONDITION_ON", conditionOn);
			request.setAttribute("CONDITION_NUMBER", conditionNumber);
			request.setAttribute("CONDITIONS", conditions);
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
			request.getRequestDispatcher("ConditionDetails.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
