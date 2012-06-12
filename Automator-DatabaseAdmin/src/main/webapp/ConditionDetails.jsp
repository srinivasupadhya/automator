<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="tool.automator.database.table.ConditionIf"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Condition Details</title>
<style type="text/css">
<!--
@import url("./static/css/style.css");
-->
</style>
</head>
<body>
	<%
		String conditionOn = (String) request.getAttribute("CONDITION_ON");
		int conditionNumber = (Integer) request.getAttribute("CONDITION_NUMBER");
	
		List<ConditionIf> conditions = (List<ConditionIf>) request.getAttribute("CONDITIONS");
		HashMap<Integer, String> pageIdNameMap = (HashMap<Integer, String>) request.getAttribute("PAGE_ID_NAME_MAP");
		HashMap<Integer, String> elementIdNameMap = (HashMap<Integer, String>) request.getAttribute("ELEMENT_ID_NAME_MAP");
		HashMap<Integer, String> elementValueIdNameMap = (HashMap<Integer, String>) request.getAttribute("ELEMENT_VALUE_ID_NAME_MAP");
	%>
	<table id="newspaper-a">
		<thead>
			<tr>
				<th scope="col" >Condition Number</th>
				<th scope="col" >Page</th>
				<th scope="col" >Element</th>
				<th scope="col" >Element Value</th>
				<th scope="col" ></th>
			</tr>
		</thead>
		<tbody>
			<tr>
			<td colspan="5" align="right">
				<a href="AddEditCondition?ACTION=ADD&ADD_CONDITION=-1"><img src="./static/img/add.png" /></a>
			</td>
			</tr>
			<tr><td colspan="5" style="padding: 0; margin: 0"><hr /></td></tr>
			<%
			for (int i = 0; i < conditions.size(); i++) {
				ConditionIf condition = conditions.get(i);
				if(i > 0 && conditions.get(i).getConditionNumber() != conditions.get(i-1).getConditionNumber())
					out.print("<tr><td colspan=\"5\" style=\"padding: 0; margin: 0\"><hr /></td></tr>");
			%>
				<tr>
				<td><%=condition.getConditionNumber()%></td>
				<td><%=pageIdNameMap.get(condition.getPageId())%></td>
				<td><%=elementIdNameMap.get(condition.getElementId())%></td>
				<td><%=elementValueIdNameMap.get(condition.getElementValueId())%></td>
				<td>
					<a href="AddEditCondition?CONDITION_ON=<%=conditionOn%>&CONDITION_NUMBER=<%=conditionNumber%>&ACTION=ADD&ADD_CONDITION=<%=condition.getId()%>">
					<img src="./static/img/add.png" /></a>
					<a href="AddEditCondition?CONDITION_ON=<%=conditionOn%>&CONDITION_NUMBER=<%=conditionNumber%>&ACTION=EDIT&CONDITION_ID=<%=condition.getId()%>">
					<img src="./static/img/pencil.png" /></a>
					<a href="#"><img src="./static/img/delete.png" /></a>
				</td>
				</tr>
			<% } %>
		</tbody>
	</table>
</body>
</html>