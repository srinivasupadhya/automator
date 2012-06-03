package tool.automator.server.servlet.getsuggestions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.automator.common.db.dao.factory.DAOFactory;
import tool.automator.common.db.daoif.*;
import tool.automator.common.db.models.*;

public class GetElementNameSuggestions extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetElementNameSuggestions() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer projectId = Integer.parseInt(request.getParameter("PROJECT_ID"));
		String pageName = request.getParameter("PAGE_NAME");
		String inputParam = request.getParameter("INPUT_PARAM");

		if (projectId != null && projectId > 0 && pageName != null && !pageName.trim().isEmpty()) {
			// get page by name
			UIPageDAOIf uiPageDAO = DAOFactory.getInstance().getUIPageDAO();
			UIPageModel uiPage = uiPageDAO.getPageByName(pageName, projectId);
			// get suggestions for element names
			ElementDAOIf elementDAO = DAOFactory.getInstance().getElementDAO();
			List<String> suggestions = elementDAO.getFilteredElementNames(uiPage.getId(), inputParam);

			// print response
			response.setContentType("text/plain");
			response.getWriter().println("{");
			response.getWriter().println("identifier:\"name\",");
			response.getWriter().println("items: [");

			if (suggestions != null && suggestions.size() > 0) {
				for (int i = 0; i < suggestions.size(); i++) {
					if (i > 0)
						response.getWriter().println(",");

					response.getWriter().println("{name:\"" + suggestions.get(i) + "\"}");
				}
			}

			response.getWriter().println("]");
			response.getWriter().println("}");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
