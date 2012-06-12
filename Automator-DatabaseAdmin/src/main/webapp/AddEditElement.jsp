<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="tool.automator.database.table.uipage.UIPageDTO"%>
<%@page import="tool.automator.database.table.element.ElementDTO"%>
<%@page import="java.util.HashMap"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><%=request.getAttribute("ACTION")%> Element</title>
<style type="text/css">
<!--
@import url("./static/css/style.css");
-->
</style>
</head>
<body>
	<%
		String action = (String) request.getAttribute("ACTION");
		UIPageDTO uipage = (UIPageDTO) request.getAttribute("PAGE");
		ElementDTO element = (ElementDTO) request.getAttribute("ELEMENT");
		Map<Long, String> pageIdNameMap = (Map<Long, String>) request.getAttribute("PAGE_ID_NAME_MAP");
	%>
	<form action="SaveElement" method="POST">
		<input type="hidden" name="ACTION" value="<%=action%>" />
		<input type="hidden" name="PAGE_ID" value="<%if(element != null) out.print(element.getPageId()); else out.print(uipage.getId());%>" />
		<table id="newspaper-a">
			<thead>
			<tr>
				<th scope="col">Property</th>
				<th scope="col">Value</th>
			</tr>
			</thead>
			<tbody>
			<tr>
				<td>ELEMENT_ID</td>
				<td>
				<% if(action.equals("ADD")) { %>
					<input type="text" name="ELEMENT_ID" readonly="readonly" />
				<% } else if(action.equals("EDIT")) { %>
					<input type="text" name="ELEMENT_ID" value="<%=element.getId()%>" readonly="readonly" />
				<% } %>
				</td>
			</tr>
			<tr>
				<td>SCRIPT_NAME</td>
				<td>
				<% if(action.equals("ADD")) { %>
					<input type="text" name="SCRIPT_NAME" />
				<% } else if(action.equals("EDIT")) { %>
					<input type="text" name="SCRIPT_NAME" value="<%=element.getScriptName()%>" />
				<% } %>
				</td>
			</tr>
			<tr>
				<td>PAGE</td>
				<td>
				<input type="text" name="PAGE_NAME" value="<%if(element != null) out.print(pageIdNameMap.get(element.getPageId())); else out.print(pageIdNameMap.get(uipage.getId()));%>" readonly="readonly" />
				</td>
			</tr>
			<tr>
				<td>UI_IDENTIFIER</td>
				<td>
				<% if(action.equals("ADD")) { %>
					<input type="text" name="UI_IDENTIFIER" />
				<% } else if(action.equals("EDIT")) { %>
					<input type="text" name="UI_IDENTIFIER" value="<%=element.getUIIdentifier()%>" />
				<% } %>
				</td>
			</tr>
			<tr>
				<td>UI_ELEMENT_GET_TYPE</td>
				<td>
				<% if(action.equals("ADD")) { %>
					<input type="text" name="UI_ELEMENT_GET_TYPE" />
				<% } else if(action.equals("EDIT")) { %>
					<input type="text" name="UI_ELEMENT_GET_TYPE" value="<%=element.getUIIdentifierGetType()%>" />
				<% } %>
				</td>
			</tr>
			<tr>
				<td>UI_ELEMENT_TYPE</td>
				<td>
				<% if(action.equals("ADD")) { %>
					<input type="text" name="UI_ELEMENT_TYPE" />
				<% } else if(action.equals("EDIT")) { %>
					<input type="text" name="UI_ELEMENT_TYPE" value="<%=element.getUIElementType()%>" />
				<% } %>
				</td>
			</tr>
			<tr>
				<td>RELATIVE_ORDER</td>
				<td>
				<% if(action.equals("ADD")) { %>
					<input type="text" name="RELATIVE_ORDER" />
				<% } else if(action.equals("EDIT")) { %>
					<input type="text" name="RELATIVE_ORDER" value="<%=element.getRelativeOrder()%>" />
				<% } %>
				</td>
			</tr>
			<tr>
				<td>RELEASE</td>
				<td>
				<% if(action.equals("ADD")) { %>
					<input type="text" name="RELEASE" />
				<% } else if(action.equals("EDIT")) { %>
					<input type="text" name="RELEASE" value="<%=element.getRelease()%>" />
				<% } %>
				</td>
			</tr>
			<tr>
				<td>OPTIONAL</td>
				<td>
				<% if(action.equals("ADD")) { %>
					<input type="radio" name="OPTIONAL" value="FALSE" checked="checked">FALSE </input>
					<input type="radio" name="OPTIONAL" value="TRUE">TRUE</input>
				<% } else if(action.equals("EDIT")) { %>
					<input type="radio" name="OPTIONAL" value="FALSE" <% if(!element.isOptional()) out.print("checked=\"checked\""); %>>FALSE </input>
					<input type="radio" name="OPTIONAL" value="TRUE" <% if(element.isOptional()) out.print("checked=\"checked\""); %>>TRUE</input>
				<% } %>
				</td>
			</tr>
			<tr>
				<td>HIDDEN</td>
				<td>
				<% if(action.equals("ADD")) { %>
					<input type="radio" name="HIDDEN" value="FALSE" checked="checked">FALSE </input>
					<input type="radio" name="HIDDEN" value="TRUE">TRUE</input>
				<% } else if(action.equals("EDIT")) { %>
					<input type="radio" name="HIDDEN" value="FALSE" <% if(!element.isHidden()) out.print("checked=\"checked\""); %>>FALSE </input>
					<input type="radio" name="HIDDEN" value="TRUE" <% if(element.isHidden()) out.print("checked=\"checked\""); %>>TRUE</input>
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