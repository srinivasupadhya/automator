package tool.automator.executor.standalone.test;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import tool.automator.database.xml.models.ElementModelXMLBind;
import tool.automator.executor.util.FlightInformation;
import tool.automator.executor.webdriver.WebDriverHandle;

public class SelectFlight {
	public static void test(String[] args) {
		int noOfSegments = 1, fareFamilyIndex = 0;
		String fareFamily = "Tango";// flightCodes = "ANY";
		
		if(fareFamily.equals("Tango"))
			fareFamilyIndex = 0;
		else if(fareFamily.equals("Tango-Plus"))
			fareFamilyIndex = 1;
		else if(fareFamily.equals("Latitude"))
			fareFamilyIndex = 2;
		else if(fareFamily.equals("Executive-Lowest"))
			fareFamilyIndex = 3;
		else if(fareFamily.equals("Executive-Flexible"))
			fareFamilyIndex = 4;
		
		String url = 
		// "http://localhost/pl/AConline/en/OverrideServlet?SITE=SAADSAAD&BOOKING_FLOW=DOMTRANSB&PRIVATE_LABEL=ACO_AC_AIR&LANGUAGE=US&COUNTRY=CA&SO_SITE_EDITION=CA&USERID=GUEST&EXTERNAL_ID=GUEST&countryOfResidence=1&SELECT_SUN=AG&SELECT_OTHERS=AT&MARKET=CA&SO_SITE_POINT_OF_SALE=YOW&SO_SITE_POINT_OF_TICKETING=YOW&SO_SITE_COUNTRY_OF_RESIDENCE=CA&TRAVELLER_TYPE_1=ADT&EMBEDDED_TRANSACTION=FlexPricerAvailabilityServlet&TRIP_TYPE=O&B_LOCATION_1=YUL&E_LOCATION_2=YUL&B_DATE_1=201201100100&CALENDAR_DATE_1=201201100100&E_LOCATION_1=YYC&B_LOCATION_2=YYC&PRICING_TYPE=C&DISPLAY_TYPE=2&COMMERCIAL_FARE_FAMILY_1=NORAMECO&TYPE_OF_CORPORATE_FARE=2&COMMERCIAL_FARE_FAMILY_2=EXCLUSIVE&CORPORATE_NUMBER_1=URIGET&CORPORATE_NUMBER_2=BHVQLN&DATE_RANGE_QUALIFIER_1=C&DATE_RANGE_VALUE_1=5&DATE_RANGE_QUALIFIER_2=C&DATE_RANGE_VALUE_2=5&SO_SITE_FP_WITHHOLD_SURCHARG=TRUE&SO_SITE_APIV2_SERVER=194.76.166.179&SO_SITE_APIV2_SERVER_USER_ID=OCG&SO_SITE_APIV2_SERVER_PWD=API2000&SO_SITE_SI_SERVER_IP=194.76.166.67&SO_SITE_SI_SAP=1ASIXJCP&SO_SITE_ONEAXML_ENABLED=TRUE&SO_SITE_CORPORATE_ID=OCG-MUCWW28AA&SO_SITE_HOST_REC_REP_MODE=NORMAL&SO_SITE_FQ_INTERFACE_ACTIVE=false&SO_SITE_SI_CMPR_ENABLED=FALSE&SO_SITE_FP_TRACES_ON=false&SO_SITE_SI_CMPR_ALGORITHM=1&SO_SITE_NUMBER_OF_REQUESTS=2&SO_SITE_SEND_MAIL=FALSE&SO_SITE_TIMEOUT_ALERT=FALSE";
		"http://localhost/pl/AConline/en/OverrideServlet?SITE=SAADSAAD&BOOKING_FLOW=INTERNATIONAL&PRIVATE_LABEL=ACO_AC_AIR&LANGUAGE=US&COUNTRY=CA&SO_SITE_EDITION=CA&USERID=GUEST&EXTERNAL_ID=GUEST&countryOfResidence=1&SELECT_SUN=AG&SELECT_OTHERS=AT&MARKET=CA&SO_SITE_POINT_OF_SALE=YOW&SO_SITE_POINT_OF_TICKETING=YOW&SO_SITE_COUNTRY_OF_RESIDENCE=CA&TRAVELLER_TYPE_1=ADT&EMBEDDED_TRANSACTION=FlexPricerAvailabilityServlet&B_LOCATION_1=YUL&B_DATE_1=201201180100&CALENDAR_DATE_1=201201180100&E_LOCATION_1=LHR&B_DATE_2=201201240100&CALENDAR_DATE_2=201201240100&TRIP_TYPE=O&PRICING_TYPE=I&DISPLAY_TYPE=2&COMMERCIAL_FARE_FAMILY_1=NBMOW&CORPORATE_NUMBER_1=URIGET&CORPORATE_NUMBER_2=BHVQLN&DATE_RANGE_QUALIFIER_1=C&DATE_RANGE_VALUE_1=5&SO_SITE_FP_WITHHOLD_SURCHARG=TRUE&SO_SITE_APIV2_SERVER=194.76.166.179&SO_SITE_APIV2_SERVER_USER_ID=OCG&SO_SITE_APIV2_SERVER_PWD=API2000&SO_SITE_SI_SERVER_IP=194.76.166.67&SO_SITE_SI_SAP=1ASIXJCP&SO_SITE_ONEAXML_ENABLED=TRUE&SO_SITE_CORPORATE_ID=OCG-MUCWW28AA&SO_SITE_HOST_REC_REP_MODE=NORMAL&SO_SITE_FQ_INTERFACE_ACTIVE=false&SO_SITE_SI_CMPR_ENABLED=FALSE&SO_SITE_FP_TRACES_ON=false&SO_SITE_SI_CMPR_ALGORITHM=1&SO_SITE_NUMBER_OF_REQUESTS=2&SO_SITE_SEND_MAIL=FALSE&SO_SITE_TIMEOUT_ALERT=FALSE";
		// "http://localhost/pl/AConline/en/OverrideServlet?SITE=SAADSAAD&BOOKING_FLOW=INTERNATIONAL&PRIVATE_LABEL=ACO_AC_AIR&LANGUAGE=US&COUNTRY=CA&SO_SITE_EDITION=CA&USERID=GUEST&EXTERNAL_ID=GUEST&countryOfResidence=1&SELECT_SUN=AG&SELECT_OTHERS=AT&MARKET=CA&SO_SITE_POINT_OF_SALE=YOW&SO_SITE_POINT_OF_TICKETING=YOW&SO_SITE_COUNTRY_OF_RESIDENCE=CA&TRAVELLER_TYPE_1=ADT&EMBEDDED_TRANSACTION=FlexPricerAvailabilityServlet&TRIP_TYPE=O&B_LOCATION_1=YUL&E_LOCATION_2=YUL&B_DATE_1=201201180100&CALENDAR_DATE_1=201201180100&E_LOCATION_1=BDA&B_LOCATION_2=BDA&PRICING_TYPE=C&DISPLAY_TYPE=2&COMMERCIAL_FARE_FAMILY_1=NBMSUN&TYPE_OF_CORPORATE_FARE=2&CORPORATE_NUMBER_1=URIGET&CORPORATE_NUMBER_2=BHVQLN&DATE_RANGE_QUALIFIER_1=C&DATE_RANGE_VALUE_1=5&DATE_RANGE_QUALIFIER_2=C&DATE_RANGE_VALUE_2=5&SO_SITE_FP_WITHHOLD_SURCHARG=TRUE&SO_SITE_APIV2_SERVER=194.76.166.179&SO_SITE_APIV2_SERVER_USER_ID=OCG&SO_SITE_APIV2_SERVER_PWD=API2000&SO_SITE_SI_SERVER_IP=194.76.166.67&SO_SITE_SI_SAP=1ASIXJCP&SO_SITE_ONEAXML_ENABLED=TRUE&SO_SITE_CORPORATE_ID=OCG-MUCWW28AA&SO_SITE_HOST_REC_REP_MODE=NORMAL&SO_SITE_FQ_INTERFACE_ACTIVE=false&SO_SITE_SI_CMPR_ENABLED=FALSE&SO_SITE_FP_TRACES_ON=false&SO_SITE_SI_CMPR_ALGORITHM=1&SO_SITE_NUMBER_OF_REQUESTS=2&SO_SITE_SEND_MAIL=FALSE&SO_SITE_TIMEOUT_ALERT=FALSE";

		WebDriverHandle webDriver = new WebDriverHandle("Firefox", url);

		List<List<String>> rowsList = getTableContents(webDriver, "id('flightList1')");
		// for (int i = 0; i < rowsList.size(); i++) {
		// System.out.println(rowsList.get(i));
		// }

		try {
			Thread.sleep(5000);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}

		List<String> firstRow = rowsList.get(0);
		int noOfFareFamilies = 0;
		for (int i = firstRow.size() - 1; i > 0 && firstRow.get(i).trim().isEmpty(); i--)
			noOfFareFamilies++;

		boolean flightSolutionStart = false;
		List<String> currentRow;
		List<FlightInformation> flightSolutions = new ArrayList<FlightInformation>();
		FlightInformation currentFlightSolution = null;
		for (int i = 1; i < rowsList.size(); i++) {
			currentRow = rowsList.get(i);
			if (!currentRow.get(1).trim().isEmpty()) {
				if (!flightSolutionStart) {
					flightSolutionStart = true;
					String[] amounts = new String[5];
					for (int j = 0; j < noOfFareFamilies; j++) {
						amounts[4 - j] = currentRow.get((currentRow.size() - 1) - j);
					}
					currentFlightSolution = new FlightInformation(currentRow.get(1), currentRow.get(4), amounts);
				}
				else {
					currentFlightSolution.addFlight(currentRow.get(1), currentRow.get(4));
				}
			}
			else {
				if (flightSolutionStart) {
					flightSolutionStart = false;
					if (currentFlightSolution != null)
						flightSolutions.add(currentFlightSolution);
				}
			}
		}

		// for (int i = 0; i < flightSolutions.size(); i++)
		// System.out.println(flightSolutions.get(i));

		String radioButtonId = null;
		for (int i = 0; i < flightSolutions.size(); i++) {
			currentFlightSolution = flightSolutions.get(i);
			if (currentFlightSolution.getNoOfSegments() == noOfSegments
					&& currentFlightSolution.getAmountAt(fareFamilyIndex) != null
					&& !currentFlightSolution.getAmountAt(fareFamilyIndex).trim().isEmpty()
					&& !currentFlightSolution.getAmountAt(fareFamilyIndex).trim().equals("-")) {
				radioButtonId = "qaid_rdo_out_flight" + i + "_" + fareFamilyIndex;
				break;
			}
		}

		System.out.println(radioButtonId);
		
		if(radioButtonId != null)
			webDriver.doAction(new ElementModelXMLBind(-1L, "", -1L, radioButtonId, "ID", "RADIOBUTTON", 1, -1L, 1, false), "CLICK");
		else
			System.out.println("No Flight Solution");
		
		try {
			Thread.sleep(5000);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}

		webDriver.closeDriver();
	}

	public static List<List<String>> getTableContents(WebDriverHandle webDriver, String xpath) {
		WebElement table = webDriver.getDriver().findElement(By.xpath(xpath));
		List<WebElement> allRows = table.findElements(By.tagName("tr"));
		List<List<String>> rowContents = new ArrayList<List<String>>();
		for (WebElement row : allRows) {
			List<String> cellContents = new ArrayList<String>();
			List<WebElement> cells = row.findElements(By.tagName("td"));
			for (WebElement cell : cells) {
				cellContents.add(cell.getText().replaceAll("[\\n\\r\\t]+", " "));
			}
			rowContents.add(cellContents);
		}
		return rowContents;
	}
}
