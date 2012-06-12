<%@page import="tool.automator.database.table.pagedependency.PageDependencyDTO"%>
<%@page import="tool.automator.database.table.uipage.UIPageDTO"%>
<%@page import="tool.automator.client.framework.models.holders.ProjectDetailsModel"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Project Details</title>
<style type="text/css">
<!--
@import url("./static/css/style.css");
-->
</style>
</head>
<body>
<%
	ProjectDetailsModel projectDetails = (ProjectDetailsModel) request.getAttribute("PROJECT_DETAILS");
	HashMap<Integer, String> pageIdNameMap = (HashMap<Integer, String>) request.getAttribute("PAGE_ID_NAME_MAP");
%>
<h3><%=projectDetails.getProject().getProjectName()%></h3>
<table id="newspaper-a">
		<thead>
			<tr>
				<th scope="col" >Page Name</th>
				<th scope="col" ></th>
				<th scope="col" >Wait Time</th>
				<th scope="col" >Start Page?</th>
				<th scope="col" >Next Page</th>
				<th scope="col" ></th>
				<th scope="col" ></th>
			</tr>
		</thead>
		<tbody>
			<tr>
			<td colspan="7" align="right">
				<a href="AddEditPage?ACTION=ADD&PROJECT_ID=<%=projectDetails.getProject().getId()%>"><img src="./static/img/add.png" /></a>
			</td>
			</tr>
			<tr><td colspan="7" style="padding: 0; margin: 0"><hr /></td></tr>
			<%
				for (int i = 0; i < projectDetails.getPageDetailsList().size(); i++) {
						UIPageDTO uiPage = projectDetails.getPageDetailsList().get(i).getPage();
			%>
				<tr>
				<td><a href="GetPageDetails?PAGE_ID=<%=uiPage.getId()%>"><%=uiPage.getPageName()%></a></td>
				<td>
					<a href="AddEditPage?ACTION=ADD&ADD_AFTER_PAGE=<%=uiPage.getId()%>"><img src="./static/img/add.png" /></a>
					<a href="AddEditPage?ACTION=EDIT&PAGE_ID=<%=uiPage.getId()%>"><img src="./static/img/pencil.png" /></a>
					<a href="#"><img src="./static/img/delete.png" /></a>
				</td>
				<td><%=uiPage.getWaitTime()%></td>
				<td>
				<%
					if(uiPage.isStartPage())
							out.print("TRUE");
				%>
				</td>
		
				<%
							if(projectDetails.getPageDetailsList().get(i).getPageDependencies().size() > 0) {
									for (int j = 0; j < projectDetails.getPageDetailsList().get(i).getPageDependencies().size(); j++) {
										PageDependencyDTO pageDependency = projectDetails.getPageDetailsList().get(i).getPageDependencies().get(j);
						%>
					<% if(j != 0) { %>
						<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					<% } %>

					<td><%=pageIdNameMap.get(pageDependency.getDestinationPageId())%></td>
					<td>
						<a href="#"><img src="./static/img/add.png" /></a>
						<a href="#"><img src="./static/img/pencil.png" /></a>
						<a href="#"><img src="./static/img/delete.png" /></a>
					</td>
					<td><a href="GetConditionDetails?CONDITION_ON=PAGE&CONDITION_NUMBER=<%=pageDependency.getId()%>">conditions</a></td>
					</tr>
					<% } %>
				<% } else { %>
					<td></td>
					<td>
						<a href="#"><img src="./static/img/add.png" /></a>
					</td>
					<td></td>
					</tr>
				<% } %>
				<tr><td colspan="7" style="padding: 0; margin: 0"><hr /></td></tr>
			<% } %>
		</tbody>
	</table>
</body>
</html>