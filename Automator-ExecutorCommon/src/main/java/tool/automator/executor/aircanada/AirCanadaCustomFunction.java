package tool.automator.executor.aircanada;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import tool.automator.database.xml.models.ElementModelXMLBind;
import tool.automator.executor.constants.ApplicationConst;
import tool.automator.executor.util.ElementNValuePair;
import tool.automator.executor.util.FlightInformation;
import tool.automator.executor.webdriver.WebDriverHandle;

public class AirCanadaCustomFunction {
	public static ApplicationConst SELECT_FLIGHT(WebDriverHandle webDriver, List<ElementNValuePair> scriptElementValuePairs) {
		ApplicationConst status = ApplicationConst.SUCCESS;
		int noOfSegments = 1, fareFamilyIndex = 0;
		String functionCall = scriptElementValuePairs.get(scriptElementValuePairs.size() - 1).getElement().getScriptName();
		// String flightCodes = scriptElementValuePairs.get(scriptElementValuePairs.size() - 2).getElementValue().getScriptValue();
		String noOfSegmentsStr = scriptElementValuePairs.get(scriptElementValuePairs.size() - 3).getElementValue().getScriptValue();
		noOfSegments = Integer.parseInt(noOfSegmentsStr);
		String fareFamily = scriptElementValuePairs.get(scriptElementValuePairs.size() - 4).getElementValue().getScriptValue();

		if (fareFamily.equals("Tango"))
			fareFamilyIndex = 0;
		else if (fareFamily.equals("Tango-Plus"))
			fareFamilyIndex = 1;
		else if (fareFamily.equals("Latitude"))
			fareFamilyIndex = 2;
		else if (fareFamily.equals("Executive-Lowest"))
			fareFamilyIndex = 3;
		else if (fareFamily.equals("Executive-Flexible"))
			fareFamilyIndex = 4;

		String availabilityPanelId = null;
		if (functionCall.contains("[1]"))
			availabilityPanelId = "id('flightList1')";
		else if (functionCall.contains("[2]"))
			availabilityPanelId = "id('flightList2')";

		List<List<String>> rowsList = getTableContents(webDriver, availabilityPanelId);

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
			if (currentFlightSolution.getNoOfSegments() == noOfSegments && currentFlightSolution.getAmountAt(fareFamilyIndex) != null
					&& !currentFlightSolution.getAmountAt(fareFamilyIndex).trim().isEmpty() && !currentFlightSolution.getAmountAt(fareFamilyIndex).trim().equals("-")) {
				radioButtonId = "qaid_rdo_out_flight" + i + "_" + fareFamilyIndex;
				break;
			}
		}

		System.out.println(radioButtonId);

		if (radioButtonId != null)
			webDriver.doAction(new ElementModelXMLBind(-1L, "", -1L, radioButtonId, "ID", "RADIOBUTTON", 1, -1, 1, false), "CLICK");
		else {
			System.out.println("No Flight Solution");
			status = ApplicationConst.FAILED;
		}

		return status;
	}

	public static void SELECT_SEAT(WebDriverHandle webDriver, List<ElementNValuePair> scriptElementValuePairs) {
		int noOfPrefferedSeatsToSelect = 0, noOfRegularSeatsToSelect = 0, noOfComfortPlusSeatsToSelect = 0;

		List<WebElement> selectSeatButtons = webDriver.getDriver().findElements(By.linkText("SELECT SEAT"));

		String functionName = scriptElementValuePairs.get(scriptElementValuePairs.size() - 1).getElement().getScriptName();
		int index = functionName.indexOf('[');
		String segmentNumberStr = functionName.substring(index + 1, functionName.length() - 1);
		int segmentNumber = Integer.parseInt(segmentNumberStr);

		selectSeatButtons.get(segmentNumber - 1).click();

		int noOfSeatsToSelect = 0;
		for (int i = 0; i < scriptElementValuePairs.size(); i++)
			if (scriptElementValuePairs.get(i).getElement().getScriptName().equals("NUMBER_OF_PASSENGERS")) {
				noOfSeatsToSelect = Integer.parseInt(scriptElementValuePairs.get(i).getElementValue().getScriptValue());
				break;
			}

		String seatPreference = scriptElementValuePairs.get(scriptElementValuePairs.size() - 2).getElementValue().getScriptValue();
		if (seatPreference.equals("Regular"))
			noOfRegularSeatsToSelect = noOfSeatsToSelect;
		else if (seatPreference.equals("Preffered"))
			noOfPrefferedSeatsToSelect = noOfSeatsToSelect;
		else if (seatPreference.equals("Comfort-Plus"))
			noOfComfortPlusSeatsToSelect = noOfSeatsToSelect;

		String originalWindowHandle = webDriver.getDriver().getWindowHandle();
		Set<String> allWindowHandle = webDriver.getDriver().getWindowHandles();
		for (String currentWindowHandle : allWindowHandle) {
			if (!currentWindowHandle.equals(originalWindowHandle)) {
				webDriver.getDriver().switchTo().window(currentWindowHandle);
				break;
			}
		}

		try {
			Thread.sleep(10000);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}

		String textValue;
		List<WebElement> availableSeats = new ArrayList<WebElement>();
		WebElement table = webDriver.getDriver().findElement(By.xpath("id('WDSSeatMapTable')"));
		List<WebElement> allRows = table.findElements(By.tagName("tr"));

		for (WebElement row : allRows) {
			List<WebElement> cells = row.findElements(By.tagName("td"));
			for (WebElement cell : cells) {
				textValue = cell.getText().replaceAll("[\\n\\r\\t]+", " ");
				if (textValue != null && !textValue.trim().isEmpty())
					;
				else {
					List<WebElement> divs = cell.findElements(By.tagName("div"));
					if (divs != null && divs.size() > 0) {
						if (noOfPrefferedSeatsToSelect > 0 && divs.get(0).getAttribute("class").equals("WDSSeatMapAvailablePrefreedSeat")) {
							cell.click();
							noOfPrefferedSeatsToSelect--;
						}
						else if (noOfRegularSeatsToSelect > 0 && divs.get(0).getAttribute("class").equals("WDSSeatMapAvailableSeat")) {
							cell.click();
							noOfRegularSeatsToSelect--;
						}
						else if (noOfComfortPlusSeatsToSelect > 0 && divs.get(0).getAttribute("class").equals("WDSSeatMapAvailableComfortPlusSeat")) {
							cell.click();
							noOfComfortPlusSeatsToSelect--;
						}
						else if (divs.get(0).getAttribute("class").equals("WDSSeatMapAvailablePrefreedSeat")
								|| divs.get(0).getAttribute("class").equals("WDSSeatMapAvailableSeat")
								|| divs.get(0).getAttribute("class").equals("WDSSeatMapAvailableComfortPlusSeat")) {
							availableSeats.add(cell);
						}
					}
				}
			}
		}

		int noOfRemainingSeatsToSelect = noOfPrefferedSeatsToSelect + noOfRegularSeatsToSelect + noOfComfortPlusSeatsToSelect;

		for (int i = 0; noOfRemainingSeatsToSelect > 0 && i < availableSeats.size(); i++) {
			availableSeats.get(i).click();
			noOfRemainingSeatsToSelect--;
		}

		webDriver.doAction(new ElementModelXMLBind(-1L, "", -1L, "CONTINUE", "LINKTEXT", "BUTTON", -1, -1, -1, true), "CLICK");

		webDriver.getDriver().switchTo().window(originalWindowHandle);
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
