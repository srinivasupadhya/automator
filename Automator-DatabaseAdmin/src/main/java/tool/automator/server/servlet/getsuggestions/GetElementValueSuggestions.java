package tool.automator.server.servlet.getsuggestions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.automator.database.factory.DAOFactory;
import tool.automator.database.table.element.ElementDTO;
import tool.automator.database.table.element.ElementService;
import tool.automator.database.table.elementvalue.ElementValueService;
import tool.automator.database.table.uipage.UIPageDTO;
import tool.automator.database.table.uipage.UIPageService;

public class GetElementValueSuggestions extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetElementValueSuggestions() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long projectId = Long.parseLong(request.getParameter("PROJECT_ID"));
		String pageName = request.getParameter("PAGE_NAME");
		String elementName = request.getParameter("ELEMENT_NAME");
		String inputParam = request.getParameter("INPUT_PARAM");

		if (projectId != null && projectId > 0 && pageName != null && !pageName.trim().isEmpty() && elementName != null && !elementName.trim().isEmpty()) {
			// get page by name
			UIPageService uiPageDAO = DAOFactory.getInstance().getUIPageService();
			UIPageDTO uiPage = uiPageDAO.getPageByName(pageName, projectId);
			// get element by name
			ElementService elementDAO = DAOFactory.getInstance().getElementService();
			ElementDTO element = elementDAO.getElementByScriptName(elementName, uiPage.getId());
			// get suggestions for element-value values
			ElementValueService elementValueDAO = DAOFactory.getInstance().getElementValueService();
			List<String> suggestions = elementValueDAO.getFilteredElementValues(element.getId(), inputParam);

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
