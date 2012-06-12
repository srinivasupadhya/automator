package tool.automator.server.servlet.getdetails;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.automator.client.framework.models.holders.PageDetailsModel;
import tool.automator.database.factory.DAOFactory;
import tool.automator.database.table.uipage.UIPageDTO;
import tool.automator.database.table.uipage.UIPageService;

public class GetPageDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetPageDetails() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long pageID = 0L;
		pageID = Long.parseLong(request.getParameter("PAGE_ID"));
		if (pageID != null && pageID > 0) {
			// get page details
			UIPageService uiPageDAO = DAOFactory.getInstance().getUIPageService();
			UIPageDTO page = uiPageDAO.getPageById(pageID);
			PageDetailsModel pageDetails = new PageDetailsModel(page);
			request.setAttribute("PAGE_DETAILS", pageDetails);
			// forward to JSP
			request.getRequestDispatcher("PageDetails.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
