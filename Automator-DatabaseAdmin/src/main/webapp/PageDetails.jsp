<%@page
	import="tool.automator.common.db.models.ElementValueModel"%>
<%@page import="tool.automator.common.db.models.ElementModel"%>
<%@page
	import="tool.automator.client.framework.models.holders.PageDetailsModel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Page Details</title>
<style type="text/css">
<!--
@import url("./static/css/style.css");
-->
</style>
</head>
<body>
	<%
		PageDetailsModel pageDetails = (PageDetailsModel) request.getAttribute("PAGE_DETAILS");
	%>
	<h3><%= pageDetails.getPage().getPageName() %></h3>
	<table id="newspaper-a">
		<thead>
			<tr>
				<th scope="col" >Order</th>
				<th scope="col" >Script Name</th>
				<th scope="col" ></th>
				<th scope="col" ></th>
				<th scope="col" >Element Type</th>
				<th scope="col" >Optional?</th>
				<th scope="col" >Hidden?</th>
				<th scope="col" >Element Value</th>
				<th scope="col" ></th>
				<th scope="col" ></th>
				<th scope="col" >Turns page?</th>
				<th scope="col" >Hidden?</th>
			</tr>
		</thead>
		<tbody>
			<tr>
			<td colspan="12" align="right">
				<a href="AddEditElement?ACTION=ADD&PAGE_ID=<%=pageDetails.getPage().getId()%>"><img src="./static/img/add.png" /></a>
			</td>
			</tr>
			<tr><td colspan="12" style="padding: 0; margin: 0"><hr /></td></tr>
			<%
			for (int i = 0; i < pageDetails.getElementDetailsList().size(); i++) {
				ElementModel element = pageDetails.getElementDetailsList().get(i).getElement();
			%>
				<tr>
				<td><%=element.getRelativeOrder()%></td>
				<td><%=element.getScriptName()%></td>
				<td>
					<a href="AddEditElement?ACTION=ADD&ADD_AFTER_ELEMENT=<%=element.getId()%>"><img src="./static/img/add.png" /></a>
					<a href="AddEditElement?ACTION=EDIT&ELEMENT_ID=<%=element.getId()%>"><img src="./static/img/pencil.png" /></a>
					<a href="#"><img src="./static/img/delete.png" /></a>
				</td>
				<td>
					<a href="GetRestrictionDetails?CONDITION_ON=ELEMENT&PAGE_ID=<%=element.getPageId()%>&ELEMENT_ID=<%=element.getId()%>">restrictions</a>
				</td>
				<td><%=element.getUIElementType()%></td>
				<td>
				<% if(element.isOptional())
					out.print("TRUE");
				%>
				</td>
				<td>
				<% if(element.isHidden())
					out.print("TRUE");
				%>
				</td>
				<%
				if(pageDetails.getElementDetailsList().get(i).getElementValueDetailsList().size() > 0) {
					for (int j = 0; j < pageDetails.getElementDetailsList().get(i).getElementValueDetailsList().size(); j++) {
						ElementValueModel elementValue = pageDetails.getElementDetailsList().get(i).getElementValueDetailsList().get(j).getElementValue();
				%>
					<% if(j != 0) { %>
						<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					<% } %>

					<td><%=elementValue.getScriptValue()%></td>
					<td>
						<a href="AddEditElementValue?ACTION=ADD&ADD_ELEMENT_VALUE=<%=elementValue.getId()%>"><img src="./static/img/add.png" /></a>
						<a href="AddEditElementValue?ACTION=EDIT&ELEMENT_VALUE_ID=<%=elementValue.getId()%>"><img src="./static/img/pencil.png" /></a>
						<a href="#"><img src="./static/img/delete.png" /></a>
					</td>
					<td><a href="GetRestrictionDetails?CONDITION_ON=ELEMENT_VALUE&PAGE_ID=<%=element.getPageId()%>&ELEMENT_ID=<%=element.getId()%>&ELEMENT_VALUE_ID=<%=elementValue.getId()%>">restrictions</a></td>
					<td>
					<% if(elementValue.getTurnsPage())
							out.print("TRUE");
					%>
					</td>
					<td>
					<% if(elementValue.isHidden())
							out.print("TRUE");
					%>
					</td>
					</tr>
					<% } %>
				<% } else { %>
					<td></td>
					<td>
						<a href="AddEditElementValue?ACTION=ADD&ELEMENT_ID=<%=element.getId()%>"><img src="./static/img/add.png" /></a>
					</td>
					<td></td>
					<td></td>
					<td></td>
					</tr>
				<% } %>
				<tr><td colspan="12" style="padding: 0; margin: 0"><hr /></td></tr>
			<% } %>
		</tbody>
	</table>
</body>
</html>