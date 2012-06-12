<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="tool.automator.database.table.elementrestriction.ElementRestrictionDTO"%>
<%@page import="tool.automator.database.table.elementvaluerestriction.ElementValueRestrictionDTO"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Restriction Details</title>
<style type="text/css">
<!--
@import url("./static/css/style.css");
-->
</style>
</head>
<body>
	<%
		String conditionOn = (String) request.getAttribute("CONDITION_ON");
		int pageId = (Integer) request.getAttribute("PAGE_ID");
		int elementId = (Integer) request.getAttribute("ELEMENT_ID");
		int elementValueId = (Integer) request.getAttribute("ELEMENT_VALUE_ID");
			
		List<ElementRestrictionDTO> elementRestrictions = (List<ElementRestrictionDTO>) request.getAttribute("ELEMENT_RESTRICTIONS");
		List<ElementValueRestrictionDTO> elementValueRestrictions = (List<ElementValueRestrictionDTO>) request.getAttribute("ELEMENT_VALUE_RESTRICTIONS");
		
		HashMap<Integer, String> pageIdNameMap = (HashMap<Integer, String>) request.getAttribute("PAGE_ID_NAME_MAP");
		HashMap<Integer, String> elementIdNameMap = (HashMap<Integer, String>) request.getAttribute("ELEMENT_ID_NAME_MAP");
		HashMap<Integer, String> elementValueIdNameMap = (HashMap<Integer, String>) request.getAttribute("ELEMENT_VALUE_ID_NAME_MAP");
	%>
	<table id="newspaper-a">
		<thead>
			<tr>
				<th scope="col" ><%=conditionOn%> Restriction Id</th>
				<th scope="col" >Page Id</th>
				<th scope="col" >Element Id</th>
				<%
					if(conditionOn.equals("ELEMENT_VALUE")) out.println("<th scope=\"col\" >ElementValue Id</th>");
				%>
				<th scope="col" ></th>
				<th scope="col" ></th>
			</tr>
		</thead>
		<%
			if(conditionOn.equals("ELEMENT")) {
		%>
		<tbody>
			<tr>
			<td colspan="5" align="right">
				<a href="AddEditRestriction?ACTION=ADD&CONDITION_ON=<%=conditionOn%>&RESTRICTION_NUMBER=-1"><img src="./static/img/add.png" /></a>
			</td>
			</tr>
			<tr><td colspan="5" style="padding: 0; margin: 0"><hr /></td></tr>
			<%
				for (int i = 0; i < elementRestrictions.size(); i++) {
						ElementRestrictionDTO elementRestriction = elementRestrictions.get(i);
			%>
				<tr>
				<td><%=elementRestriction.getId()%></td>
				<td><%=pageIdNameMap.get(elementRestriction.getPageId())%></td>
				<td><%=elementIdNameMap.get(elementRestriction.getElementId())%></td>
				<td>
					<a href="AddEditRestriction?&ACTION=ADD&CONDITION_ON=<%=conditionOn%>&RESTRICTION_NUMBER=<%=elementRestriction.getId()%>">
					<img src="./static/img/add.png" /></a>
					<a href="AddEditRestriction?&ACTION=EDIT&CONDITION_ON=<%=conditionOn%>&RESTRICTION_NUMBER=<%=elementRestriction.getId()%>">
					<img src="./static/img/pencil.png" /></a>
					<a href="#"><img src="./static/img/delete.png" /></a>
				</td>
				<td><a href="GetConditionDetails?CONDITION_ON=ELEMENT&CONDITION_NUMBER=<%=elementRestriction.getId()%>">conditions</a></td>
				</tr>
			<%
				}
			%>
		</tbody>
		<%
			} else if(conditionOn.equals("ELEMENT_VALUE")) {
		%>
		<tbody>
			<tr>
			<td colspan="6" align="right">
				<a href="AddEditRestriction?ACTION=ADD&CONDITION_ON=<%=conditionOn%>&RESTRICTION_NUMBER=-1"><img src="./static/img/add.png" /></a>
			</td>
			</tr>
			<tr><td colspan="6" style="padding: 0; margin: 0"><hr /></td></tr>
			<%
				for (int i = 0; i < elementValueRestrictions.size(); i++) {
					ElementValueRestrictionDTO elementValueRestriction = elementValueRestrictions.get(i);
			%>
				<tr>
				<td><%=elementValueRestriction.getId()%></td>
				<td><%=pageIdNameMap.get(elementValueRestriction.getPageId())%></td>
				<td><%=elementIdNameMap.get(elementValueRestriction.getElementId())%></td>
				<td><%=elementValueIdNameMap.get(elementValueRestriction.getElementValueId())%></td>
				<td>
					<a href="AddEditRestriction?&ACTION=ADD&CONDITION_ON=<%=conditionOn%>&RESTRICTION_NUMBER=<%=elementValueRestriction.getId()%>">
					<img src="./static/img/add.png" /></a>
					<a href="AddEditRestriction?&ACTION=EDIT&CONDITION_ON=<%=conditionOn%>&RESTRICTION_NUMBER=<%=elementValueRestriction.getId()%>">
					<img src="./static/img/pencil.png" /></a>
					<a href="#"><img src="./static/img/delete.png" /></a>
				</td>
				<td><a href="GetConditionDetails?CONDITION_ON=ELEMENT_VALUE&CONDITION_NUMBER=<%=elementValueRestriction.getId()%>">conditions</a></td>
				</tr>
			<% } %>
		</tbody>
		<% } %>
	</table>
</body>
</html>