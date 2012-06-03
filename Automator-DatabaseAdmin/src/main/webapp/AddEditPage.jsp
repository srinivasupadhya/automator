<%@page import="tool.automator.common.db.models.ProjectModel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="tool.automator.common.db.models.UIPageModel"%>
<%@page import="java.util.HashMap"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><%=request.getAttribute("ACTION")%> Page</title>
<style type="text/css">
<!--
@import url("./static/css/style.css");
-->
</style>
</head>
<body>
	<%
		String action = (String) request.getAttribute("ACTION");
		ProjectModel project = (ProjectModel) request.getAttribute("PROJECT");
		UIPageModel uiPage = (UIPageModel) request.getAttribute("PAGE");
		HashMap<Integer, String> projectIdNameMap = (HashMap<Integer, String>) request.getAttribute("PROJECT_ID_NAME_MAP");
	%>
	<form action="SavePage" method="POST">
		<input type="hidden" name="ACTION" value="<%=action%>" />
		<input type="hidden" name="PROJECT_ID" value="<%if(uiPage != null) out.print(uiPage.getProjectId()); else out.print(project.getId());%>" />
		<table id="newspaper-a">
			<thead>
			<tr>
				<th scope="col">Property</th>
				<th scope="col">Value</th>
			</tr>
			</thead>
			<tbody>
			<tr>
				<td>PAGE_ID</td>
				<td>
				<% if(action.equals("ADD")) { %>
					<input type="text" name="PAGE_ID" readonly="readonly" />
				<% } else if(action.equals("EDIT")) { %>
					<input type="text" name="PAGE_ID" value="<%=uiPage.getId()%>" readonly="readonly" />
				<% } %>
				</td>
			</tr>
			<tr>
				<td>PAGE_NAME</td>
				<td>
				<% if(action.equals("ADD")) { %>
					<input type="text" name="PAGE_NAME" />
				<% } else if(action.equals("EDIT")) { %>
					<input type="text" name="PAGE_NAME" value="<%=uiPage.getPageName()%>" />
				<% } %>
				</td>
			</tr>
			<tr>
				<td>PROJECT_NAME</td>
				<td>
				<input type="text" name="PROJECT_NAME" value="<%if(uiPage != null) out.print(projectIdNameMap.get(uiPage.getProjectId())); else out.print(projectIdNameMap.get(project.getId()));%>" readonly="readonly" />
				</td>
			</tr>
			<tr>
				<td>WAIT_TIME</td>
				<td>
				<% if(action.equals("ADD")) { %>
					<input type="text" name="WAIT_TIME" />
				<% } else if(action.equals("EDIT")) { %>
					<input type="text" name="WAIT_TIME" value="<%=uiPage.getWaitTime()%>" />
				<% } %>
				</td>
			</tr>
			<tr>
				<td>PAGE_GET_URL</td>
				<td>
				<% if(action.equals("ADD")) { %>
					<input type="text" name="PAGE_GET_URL" />
				<% } else if(action.equals("EDIT")) { %>
					<input type="text" name="PAGE_GET_URL" value="<%=uiPage.getPageGetURL()%>" />
				<% } %>
				</td>
			</tr>
			<tr>
				<td>PAGE_IDENTIFIER</td>
				<td>
				<% if(action.equals("ADD")) { %>
					<input type="text" name="PAGE_IDENTIFIER" />
				<% } else if(action.equals("EDIT")) { %>
					<input type="text" name="PAGE_IDENTIFIER" value="<%=uiPage.getPageIdentifier()%>" />
				<% } %>
				</td>
			</tr>
			<tr>
				<td>PAGE_IDENTIFIER_GET_TYPE</td>
				<td>
				<% if(action.equals("ADD")) { %>
					<input type="text" name="PAGE_IDENTIFIER_GET_TYPE" />
				<% } else if(action.equals("EDIT")) { %>
					<input type="text" name="PAGE_IDENTIFIER_GET_TYPE" value="<%=uiPage.getPageIdentifierGetType()%>" />
				<% } %>
				</td>
			</tr>
			<tr>
				<td>PAGE_IDENTIFIER_VALUE</td>
				<td>
				<% if(action.equals("ADD")) { %>
					<input type="text" name="PAGE_IDENTIFIER_VALUE" />
				<% } else if(action.equals("EDIT")) { %>
					<input type="text" name="PAGE_IDENTIFIER_VALUE" value="<%=uiPage.getPageIdentifierValue()%>" />
				<% } %>
				</td>
			</tr>
			<tr>
				<td>START_PAGE</td>
				<td>
				<% if(action.equals("ADD")) { %>
					<input type="radio" name="START_PAGE" value="FALSE" checked="checked">FALSE </input>
					<input type="radio" name="START_PAGE" value="TRUE">TRUE</input>
				<% } else if(action.equals("EDIT")) { %>
					<input type="radio" name="START_PAGE" value="FALSE" <% if(!uiPage.isStartPage()) out.print("checked=\"checked\""); %>>FALSE </input>
					<input type="radio" name="START_PAGE" value="TRUE" <% if(uiPage.isStartPage()) out.print("checked=\"checked\""); %>>TRUE</input>
				<% } %>
				</td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="SAVE" /></td>
			</tr>
			</tbody>
		</table>
	</form>
</body>
</html>