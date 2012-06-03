package tool.automator.executor.standalone.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import tool.automator.executor.webdriver.WebDriverHandle;
import tool.automator.executor.xml.models.ElementModelXMLBind;

public class SelectSeat {
	public static void test(String[] args) throws InterruptedException {
		int noOfPrefferedSeatsToSelect = 2, noOfRegularSeatsToSelect = 2, noOfComfortPlusSeatsToSelect = 0;
		String url = "https://book.aircanada.com/pl/AConline/en/GWOForwardServlet;jsessionid=r8n2TgDMmwTcc2XJnlnPDWMLzyc2gNsnlQzpl69zkg0lQQhKyLJS!-1107968555?PAGE_NAME=SEAS_Version_C.jsp&SITE=SAADSAAD&LANGUAGE=US&PAGE_TICKET=7";

		WebDriverHandle webDriver = new WebDriverHandle("Firefox", url);

		Thread.sleep(10000);

		webDriver.doAction(new ElementModelXMLBind(-1, "", -1, "SELECT SEAT", "LINKTEXT", "BUTTON", -1, -1, -1, true), "CLICK");

		String originalWindowHandle = webDriver.getDriver().getWindowHandle();
		Set<String> allWindowHandle = webDriver.getDriver().getWindowHandles();
		for (String currentWindowHandle : allWindowHandle) {
			if (!currentWindowHandle.equals(originalWindowHandle)) {
				webDriver.getDriver().switchTo().window(currentWindowHandle);
				break;
			}
		}

		Thread.sleep(10000);

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
						if (noOfPrefferedSeatsToSelect > 0
								&& divs.get(0).getAttribute("class").equals("WDSSeatMapAvailablePrefreedSeat")) {
							cell.click();
							noOfPrefferedSeatsToSelect--;
						}
						else if (noOfRegularSeatsToSelect > 0
								&& divs.get(0).getAttribute("class").equals("WDSSeatMapAvailableSeat")) {
							cell.click();
							noOfRegularSeatsToSelect--;
						}
						else if (noOfComfortPlusSeatsToSelect > 0
								&& divs.get(0).getAttribute("class").equals("WDSSeatMapAvailableComfortPlusSeat")) {
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

		int noOfRemainingSeatsToSelect = noOfPrefferedSeatsToSelect + noOfRegularSeatsToSelect
				+ noOfComfortPlusSeatsToSelect;

		for (int i = 0; noOfRemainingSeatsToSelect > 0 && i < availableSeats.size(); i++) {
			availableSeats.get(i).click();
			noOfRemainingSeatsToSelect--;
		}

		webDriver.doAction(new ElementModelXMLBind(-1, "", -1, "CONTINUE", "LINKTEXT", "BUTTON", -1, -1, -1, true), "CLICK");

		webDriver.getDriver().switchTo().window(originalWindowHandle);
	}
}