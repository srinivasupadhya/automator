package tool.automator.server.servlet.save;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.automator.database.factory.DAOFactory;
import tool.automator.database.table.uipage.UIPageDTO;
import tool.automator.database.table.uipage.UIPageService;

public class SavePage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SavePage() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long projectId = Long.parseLong(request.getParameter("PROJECT_ID"));
		Long pageId = 0L;
		if (request.getParameter("PAGE_ID") != null && !request.getParameter("PAGE_ID").trim().isEmpty())
			pageId = Long.parseLong(request.getParameter("PAGE_ID"));
		String pageName = request.getParameter("PAGE_NAME");
		int waitTime = 0;
		if (request.getParameter("WAIT_TIME") != null && !request.getParameter("WAIT_TIME").trim().isEmpty())
			waitTime = Integer.parseInt(request.getParameter("WAIT_TIME"));
		String pageGetURL = request.getParameter("PAGE_GET_URL");
		String pageIdentifier = request.getParameter("PAGE_IDENTIFIER");
		String pageIdentifierGetType = request.getParameter("PAGE_IDENTIFIER_GET_TYPE");
		String pageIdentifierValue = request.getParameter("PAGE_IDENTIFIER_VALUE");
		boolean startPage = Boolean.parseBoolean(request.getParameter("START_PAGE"));

		// save page
		UIPageService uiPageDAO = DAOFactory.getInstance().getUIPageService();
		
		UIPageDTO uiPage = uiPageDAO.getPageById(pageId);
		uiPage.update(pageName, projectId, startPage, waitTime, pageGetURL, pageIdentifier, pageIdentifierGetType, pageIdentifierValue);
		uiPageDAO.savePage(uiPage);
		
		// forward to JSP
		response.sendRedirect("GetProjectDetails?PROJECT_ID=" + projectId);
	}
}
