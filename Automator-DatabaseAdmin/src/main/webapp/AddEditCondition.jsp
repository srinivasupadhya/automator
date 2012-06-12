<%@page import="java.util.Map"%>
<%@page import="tool.automator.database.table.elementvaluecondition.ElementValueConditionDTO"%>
<%@page import="tool.automator.database.table.elementcondition.ElementConditionDTO"%>
<%@page import="tool.automator.database.table.pagecondition.PageConditionDTO"%>
<%@page import="tool.automator.database.table.ConditionIf"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.util.HashMap"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><%=request.getAttribute("ACTION")%> Condition</title>
<style type="text/css">
<!--
@import url("./static/css/style.css");
-->
</style>

<!-- Dojo -->
<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/dojo/1.6.0/dojo/dojo.xd.js" djConfig="parseOnLoad: true"></script>

<script type="text/javascript">
dojo.require("dijit.form.FilteringSelect");
dojo.require("dojo.data.ItemFileReadStore");
dojo.require("dijit.form.Button");
</script>
<script type="text/javascript">
function getPageSuggestions() {
	//alert(document.getElementById('PROJECT_ID').value + " " + dijit.byId('pageSearchBox').get('displayedValue'));
	var projectIDParam = document.getElementById('PROJECT_ID').value;
	var inputParam = dijit.byId('pageSearchBox').get('displayedValue');
	
	pageNameStore.url = "http://localhost:8080/AutomatorServer/GetPageNameSuggestions?PROJECT_ID=" + projectIDParam + "&INPUT_PARAM=" + inputParam;
	pageNameStore.close();
}

function getElementSuggestions() {
	// alert(dijit.byId('stateSelect').get('displayedValue'));
	var projectIDParam = document.getElementById('PROJECT_ID').value;
	var pageName = dijit.byId('pageSearchBox').get('displayedValue');
	var inputParam = dijit.byId('elementSearchBox').get('displayedValue');
	
	elementNameStore.url = "http://localhost:8080/AutomatorServer/GetElementNameSuggestions?PROJECT_ID=" + projectIDParam + "&PAGE_NAME=" + pageName + "&INPUT_PARAM=" + inputParam;
	elementNameStore.close();
}

function getElementValueSuggestions() {
	// alert(dijit.byId('stateSelect').get('displayedValue'));
	var projectIDParam = document.getElementById('PROJECT_ID').value;
	var pageName = dijit.byId('pageSearchBox').get('displayedValue');
	var elementName = dijit.byId('elementSearchBox').get('displayedValue');
	var inputParam = dijit.byId('elementValueSearchBox').get('displayedValue');
	
	elementValueStore.url = "http://localhost:8080/AutomatorServer/GetElementValueSuggestions?PROJECT_ID=" + projectIDParam + "&PAGE_NAME=" + pageName + "&ELEMENT_NAME=" + elementName + "&INPUT_PARAM=" + inputParam;
	elementValueStore.close();
}

</script>
<link rel="stylesheet" type="text/css" href="http://ajax.googleapis.com/ajax/libs/dojo/1.6/dijit/themes/claro/claro.css" />
</head>
<body class="claro">
	<%
		String conditionOn = (String) request.getAttribute("CONDITION_ON");
		Long sourcePageId = (Long) request.getAttribute("SOURCE_PAGE_ID");
		Long destinationPageId = (Long) request.getAttribute("DESTINATION_PAGE_ID");
		Long elementId = (Long) request.getAttribute("ELEMENT_ID");
		Long elementValueId = (Long) request.getAttribute("ELEMENT_VALUE_ID");
	
		String action = (String) request.getAttribute("ACTION");
		ConditionIf condition = (ConditionIf) request.getAttribute("CONDITION");
		Map<Long, String> pageIdNameMap = (Map<Long, String>) request.getAttribute("PAGE_ID_NAME_MAP");
		Map<Long, String> elementIdNameMap = (Map<Long, String>) request.getAttribute("ELEMENT_ID_NAME_MAP");
		Map<Long, String> elementValueIdNameMap = (Map<Long, String>) request.getAttribute("ELEMENT_VALUE_ID_NAME_MAP");
	%>
	<input type="hidden" name="PROJECT_ID" id="PROJECT_ID" value="1">
	<form action="SaveCondition" method="POST">
		<input type="hidden" name="ACTION" value="<%=action%>" />
		<input type="hidden" name="CONDITION_ON" value="<%=conditionOn%>" />
		<input type="hidden" name="SOURCE_PAGE_ID" value="<%=sourcePageId%>" />
		<input type="hidden" name="DESTINATION_PAGE_ID" value="<%=destinationPageId%>" />
		<input type="hidden" name="ELEMENT_ID" value="<%=elementId%>" />
		<input type="hidden" name="ELEMENT_VALUE_ID" value="<%=elementValueId%>" />
		
		<table id="newspaper-a">
			<thead>
			<tr>
				<th scope="col">Property</th>
				<th scope="col">Value</th>
			</tr>
			</thead>
			<tbody>
			<tr>
				<td>CONDITION_ID</td>
				<td>
				<% if(action.equals("ADD")) { %>
					<input type="text" name="CONDITION_ID" readonly="readonly" />
				<% } else if(action.equals("EDIT")) { %>
					<input type="text" name="CONDITION_ID" value="<%=condition.getId()%>" readonly="readonly" />
				<% } %>
				</td>
			</tr>
			<tr>
				<td>CONDITION_NUMBER</td>
				<td>
				<% if(condition.getConditionNumber() > 0) { %>
					<input type="text" name="CONDITION_NUMBER" value="<%=condition.getConditionNumber()%>" readonly="readonly" />
				<% } else { %>
					<input type="text" name="CONDITION_NUMBER" />
				<% } %>
				</td>
			</tr>
			<tr>
				<td>PAGE</td>
				<td>
					<div dojoType="dojo.data.ItemFileReadStore" jsId="pageNameStore" url="http://localhost:8080/AutomatorServer/GetPageNameSuggestions?PROJECT_ID=1&INPUT_PARAM=" urlPreventCache="true" clearOnClose="true"></div>

					<select dojoType="dijit.form.FilteringSelect" value="<%if(condition != null && action.equals("EDIT")) out.print(pageIdNameMap.get(condition.getPageId()));%>" store="pageNameStore" 
						searchAttr="name" name="pageSearchBox" id="pageSearchBox" onkeyup="getPageSuggestions()" onchange="getPageSuggestions()"></select>
				</td>
			</tr>
			<tr>
				<td>ELEMENT</td>
				<td>
					<div dojoType="dojo.data.ItemFileReadStore" jsId="elementNameStore" url="http://localhost:8080/AutomatorServer/GetElementNameSuggestions?PROJECT_ID=1" urlPreventCache="true" clearOnClose="true"></div>

					<select dojoType="dijit.form.FilteringSelect" value="<%if(condition != null && action.equals("EDIT")) out.print(elementIdNameMap.get(condition.getElementId()));%>" store="elementNameStore" 
						searchAttr="name" name="elementSearchBox" id="elementSearchBox" onkeyup="getElementSuggestions()" onchange="getElementSuggestions()"></select>
				</td>
			</tr>
			<tr>
				<td>ELEMENT_VALUE</td>
				<td>
					<div dojoType="dojo.data.ItemFileReadStore" jsId="elementValueStore" url="http://localhost:8080/AutomatorServer/GetElementValueSuggestions?PROJECT_ID=1" urlPreventCache="true" clearOnClose="true"></div>

					<select dojoType="dijit.form.FilteringSelect" value="<%if(condition != null && action.equals("EDIT")) out.print(elementValueIdNameMap.get(condition.getElementValueId()));%>" store="elementValueStore" 
						searchAttr="name" name="elementValueSearchBox" id="elementValueSearchBox" onkeyup="getElementValueSuggestions()" onchange="getElementValueSuggestions()"></select>
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