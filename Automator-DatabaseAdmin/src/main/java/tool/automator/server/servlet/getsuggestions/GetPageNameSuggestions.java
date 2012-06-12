package tool.automator.server.servlet.getsuggestions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.automator.database.factory.DAOFactory;
import tool.automator.database.table.uipage.UIPageService;

public class GetPageNameSuggestions extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetPageNameSuggestions() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long projectId = Long.parseLong(request.getParameter("PROJECT_ID"));
		String inputParam = request.getParameter("INPUT_PARAM");

		if (projectId != null && projectId > 0) {
			// get suggestions for page names
			UIPageService uiPageDAO = DAOFactory.getInstance().getUIPageService();
			List<String> suggestions = uiPageDAO.getFilteredPageNames(projectId, inputParam);

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
