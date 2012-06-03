package tool.automator.server.servlet.getdetails;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.automator.common.db.dao.factory.DAOFactory;
import tool.automator.common.db.daoif.*;
import tool.automator.common.db.models.*;
import tool.automator.client.framework.models.holders.PageDetailsModel;

public class GetPageDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetPageDetails() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer pageID = 0;
		pageID = Integer.parseInt(request.getParameter("PAGE_ID"));
		if (pageID != null && pageID > 0) {
			// get page details
			UIPageDAOIf uiPageDAO = DAOFactory.getInstance().getUIPageDAO();
			UIPageModel page = uiPageDAO.getPageById(pageID);
			PageDetailsModel pageDetails = new PageDetailsModel(page);
			request.setAttribute("PAGE_DETAILS", pageDetails);
			// forward to JSP
			request.getRequestDispatcher("PageDetails.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
