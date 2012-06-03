package tool.automator.server.servlet.getdetails;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.automator.common.db.dao.factory.DAOFactory;
import tool.automator.common.db.daoif.*;
import tool.automator.common.db.models.*;
import tool.automator.common.models.interfaces.ConditionIf;

public class GetConditionDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetConditionDetails() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String conditionOn = "";
		Integer conditionNumber = 0;
		if (request.getParameter("CONDITION_ON") != null)
			conditionOn = request.getParameter("CONDITION_ON");
		if (request.getParameter("CONDITION_NUMBER") != null)
			conditionNumber = Integer.parseInt(request.getParameter("CONDITION_NUMBER"));

		List<? extends ConditionIf> conditions = new ArrayList<ConditionIf>();

		if ((conditionOn != null && !conditionOn.isEmpty()) && conditionNumber > 0
				&& (conditionOn.equals("PAGE") || conditionOn.equals("ELEMENT") || conditionOn.equals("ELEMENT_VALUE"))) {
			if (conditionOn.equals("PAGE")) {
				PageConditionDAOIf pageConditionDAO = DAOFactory.getInstance().getPageConditionDAO();
				conditions = pageConditionDAO.getPageConditionsByPageDependencyId(conditionNumber);
			}
			else if (conditionOn.equals("ELEMENT")) {
				ElementConditionDAOIf elementConditionDAO = DAOFactory.getInstance().getElementConditionDAO();
				conditions = elementConditionDAO.getElementConditionsByElementRestrictionId(conditionNumber);
			}
			else if (conditionOn.equals("ELEMENT_VALUE")) {
				ElementValueConditionDAOIf elementValueConditonDAO = DAOFactory.getInstance().getElementValueConditionDAO();
				conditions = elementValueConditonDAO.getElementValueConditionsByElementValueRestrictionId(conditionNumber);
			}

			request.setAttribute("CONDITION_ON", conditionOn);
			request.setAttribute("CONDITION_NUMBER", conditionNumber);
			request.setAttribute("CONDITIONS", conditions);
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
			request.getRequestDispatcher("ConditionDetails.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
