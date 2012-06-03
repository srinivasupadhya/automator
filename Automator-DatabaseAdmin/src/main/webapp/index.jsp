<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.List"%>
<%@page import="tool.automator.common.db.dao.factory.DAOFactory"%>
<%@page import="tool.automator.common.db.daoif.ProjectDAOIf"%>
<%@page import="tool.automator.common.db.models.ProjectModel"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Select Project</title>
<style type="text/css">
<!--
@import url("./static/css/style.css");
-->
</style>
</head>
<body>
	<%
		ProjectDAOIf projectDAO = DAOFactory.getInstance().getProjectDAO();
		List<ProjectModel> projectList = projectDAO.getAllProjects();
	%>
	<h3>Select Project</h3>
	<table id="newspaper-a">
		<thead>
			<tr>
				<th scope="col">Project</th>
			</tr>
		</thead>
		<tbody>
			<% for(int i=0; i < projectList.size(); i++) { %>
				<tr>
					<td><a href="GetProjectDetails?PROJECT_ID=<%=projectList.get(i).getId()%>"><%=projectList.get(i).getProjectName()%></a></td>
				</tr>
			<% } %>
		</tbody>
	</table>
</body>
</html>