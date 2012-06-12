<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="tool.automator.database.table.element.ElementDTO"%>
<%@page import="tool.automator.database.table.elementvalue.ElementValueDTO"%>
<%@page import="java.util.HashMap"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><%=request.getAttribute("ACTION")%> Element Value</title>
<style type="text/css">
<!--
@import url("./static/css/style.css");
-->
</style>
</head>
<body>
	<%
		String action = (String) request.getAttribute("ACTION");
		ElementDTO element = (ElementDTO) request.getAttribute("ELEMENT");
		ElementValueDTO elementValue = (ElementValueDTO) request.getAttribute("ELEMENT_VALUE");
		HashMap<Integer, String> elementIdNameMap = (HashMap<Integer, String>) request.getAttribute("ELEMENT_ID_NAME_MAP");
	%>
	<form action="SaveElementValue" method="POST">
		<input type="hidden" name="ACTION" value="<%=action%>" />
		<input type="hidden" name="ELEMENT_ID" value="<%if(elementValue != null) out.print(elementValue.getElementId()); else out.print(element.getId());%>" />
		<table id="newspaper-a">
			<thead>
			<tr>
				<th scope="col">Property</th>
				<th scope="col">Value</th>
			</tr>
			</thead>
			<tbody>
			<tr>
				<td>ELEMENT_VALUE_ID</td>
				<td>
				<% if(action.equals("ADD")) { %>
					<input type="text" name="ELEMENT_VALUE_ID" readonly="readonly" />
				<% } else if(action.equals("EDIT")) { %>
					<input type="text" name="ELEMENT_VALUE_ID" value="<%=elementValue.getId()%>" readonly="readonly" />
				<% } %>
				</td>
			</tr>
			<tr>
				<td>SCRIPT_VALUE</td>
				<td>
				<% if(action.equals("ADD")) { %>
					<input type="text" name="SCRIPT_VALUE" />
				<% } else if(action.equals("EDIT")) { %>
					<input type="text" name="SCRIPT_VALUE" value="<%=elementValue.getScriptValue()%>" />
				<% } %>
				</td>
			</tr>
			<tr>
				<td>ACTUAL_VALUE</td>
				<td>
				<% if(action.equals("ADD")) { %>
					<input type="text" name="ACTUAL_VALUE" />
				<% } else if(action.equals("EDIT")) { %>
					<input type="text" name="ACTUAL_VALUE" value="<%=elementValue.getActualValue()%>" />
				<% } %>
				</td>
			</tr>
			<tr>
				<td>ELEMENT_NAME</td>
				<td>
				<input type="text" name="ELEMENT_NAME" value="<%if(elementValue != null) out.print(elementIdNameMap.get(elementValue.getElementId())); else out.print(elementIdNameMap.get(element.getId()));%>" readonly="readonly" />
				</td>
			</tr>
			<tr>
				<td>OPTIONAL</td>
				<td>
				<% if(action.equals("ADD")) { %>
					<input type="radio" name="TURNS_PAGE" value="FALSE" checked="checked">FALSE</input>
					<input type="radio" name="TURNS_PAGE" value="TRUE">TRUE</input>
				<% } else if(action.equals("EDIT")) { %>
					<input type="radio" name="TURNS_PAGE" value="FALSE" <% if(!elementValue.getTurnsPage()) out.print("checked=\"checked\""); %>>FALSE</input>
					<input type="radio" name="TURNS_PAGE" value="TRUE" <% if(elementValue.getTurnsPage()) out.print("checked=\"checked\""); %>>TRUE</input>
				<% } %>
				</td>
			</tr>
			<tr>
				<td>HIDDEN</td>
				<td>
				<% if(action.equals("ADD")) { %>
					<input type="radio" name="HIDDEN" value="FALSE" checked="checked">FALSE</input>
					<input type="radio" name="HIDDEN" value="TRUE">TRUE</input>
				<% } else if(action.equals("EDIT")) { %>
					<input type="radio" name="HIDDEN" value="FALSE" <% if(!elementValue.isHidden()) out.print("checked=\"checked\""); %>>FALSE</input>
					<input type="radio" name="HIDDEN" value="TRUE" <% if(elementValue.isHidden()) out.print("checked=\"checked\""); %>>TRUE</input>
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