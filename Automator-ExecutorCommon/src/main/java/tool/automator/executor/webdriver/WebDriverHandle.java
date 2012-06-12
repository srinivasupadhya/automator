package tool.automator.executor.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;

import tool.automator.database.constants.UIElementTypesConst;
import tool.automator.database.table.element.ElementModelIf;
import tool.automator.executor.constants.*;

public class WebDriverHandle {
	private WebDriver driver;
	private WebElement webElement;
	private WebDriverErrorConst errorCode;

	/**
	 * constructors
	 */
	public WebDriverHandle() {
		errorCode = WebDriverErrorConst.NO_ERRORS;
	}

	public WebDriverHandle(String browserChoice, String startURL) {
		errorCode = WebDriverErrorConst.NO_ERRORS;

		start(browserChoice, startURL);
	}

	/**
	 * initialize
	 */
	public void start(String browserChoice, String startURL) {
		try {
			if (browserChoice.equals(BrowserChoiceConst.FIREFOX))
				driver = new FirefoxDriver();
			else if (browserChoice.equals(BrowserChoiceConst.EXPLORER))
				driver = new InternetExplorerDriver();
			else if (browserChoice.equals(BrowserChoiceConst.CHROME))
				driver = new ChromeDriver();
			else if (browserChoice.equals(BrowserChoiceConst.NOBROWSER))
				driver = new HtmlUnitDriver();
			else
				setErrorCode(WebDriverErrorConst.BROWSER_NOT_SUPPORTED);

			if (driver != null && startURL != null && !startURL.trim().isEmpty())
				driver.get(startURL);
			else
				driver.get("http://www.google.co.in");
		}
		catch (Exception e) {
			setErrorCode(WebDriverErrorConst.COULD_NOT_CREATE_DRIVER);
		}
	}

	/**
	 * open page with URL
	 */
	public void openPage(String startURL) {
		driver.get(startURL);
	}

	public void goBack() {
		driver.navigate().back();
	}

	public void goForward() {
		driver.navigate().forward();
	}

	public void refreshPage() {
		driver.navigate().refresh();
	}

	/**
	 * get UI element's value in page
	 */
	public String getValue(ElementModelIf element) {
		String elementValue = null;

		if (element != null) {
			try {
				webElement = getWebElementOnPage(element);

				if (webElement != null)
					elementValue = webElement.getText();
			}
			catch (Exception e) {
				setErrorCode(WebDriverErrorConst.COULD_NOT_GET_ELEMENT_VALUE);
			}
		}
		else
			setErrorCode(WebDriverErrorConst.ELEMENT_NULL);

		return elementValue;
	}

	/**
	 * perform required action on element
	 */
	public ApplicationConst doAction(ElementModelIf element, String value) {
		ApplicationConst actionResult = ApplicationConst.FAILED;

		if (element != null) {
			// get element
			webElement = getWebElementOnPage(element);

			if (webElement != null) {
				// find action type
				UIActionTypeConst actionType = getActionType(element);

				if (actionType != UIActionTypeConst.NOT_SUPPORTED) {
					// pass true-value if present else script-value
					if (actionType == UIActionTypeConst.CLICK)
						actionResult = doClick();
					if (actionType == UIActionTypeConst.INPUT) {
						if (element.getScriptName().contains("TRAVEL_DATE"))
							actionResult = doInput(value, true);
						else
							actionResult = doInput(value, true);
					}
					if (actionType == UIActionTypeConst.SELECT)
						actionResult = doSelect(true, value);
				}
				else
					actionResult = ApplicationConst.FAILED;
			}
			else
				actionResult = ApplicationConst.FAILED;
		}
		else {
			setErrorCode(WebDriverErrorConst.ELEMENT_NULL);
			actionResult = ApplicationConst.FAILED;
		}

		return actionResult;
	}

	/**
	 * get UI element
	 */
	public WebElement getWebElementOnPage(ElementModelIf element) {
		WebElement webElement = null;

		By byElement = findGetBy(element);

		if (byElement != null) {
			try {
				webElement = driver.findElement(byElement);
				return webElement;
			}
			catch (Exception e) {
				setErrorCode(WebDriverErrorConst.COULD_NOT_FIND_ELEMENT_ON_PAGE);
				webElement = null;
			}
		}
		else {
			setErrorCode(WebDriverErrorConst.WRONG_ELEMENT_GET_TYPE);
			webElement = null;
		}

		return webElement;
	}

	/**
	 * find how to get UI element
	 */
	public By findGetBy(ElementModelIf element) {
		By byElement = null;

		if (element != null) {
			String uiGetType = element.getUIIdentifierGetType();
			String uiIdentifier = element.getUIIdentifier();

			if (uiGetType.equals(UIElementGetTypesConst.ID))
				byElement = By.id(uiIdentifier);
			else if (uiGetType.equals(UIElementGetTypesConst.NAME))
				byElement = By.name(uiIdentifier);
			else if (uiGetType.equals(UIElementGetTypesConst.XPATH))
				byElement = By.xpath(uiIdentifier);
			else if (uiGetType.equals(UIElementGetTypesConst.LINKTEXT))
				byElement = By.linkText(uiIdentifier);
			else if (uiGetType.equals(UIElementGetTypesConst.TAGNAME))
				byElement = By.tagName(uiIdentifier);
		}

		return byElement;
	}

	/**
	 * find action to be performed on UI element
	 */
	private UIActionTypeConst getActionType(ElementModelIf element) {
		UIActionTypeConst actionType = UIActionTypeConst.NOT_SUPPORTED;

		String elementUIType = element.getUIElementType();

		if (elementUIType.equals(UIElementTypesConst.BUTTON) || elementUIType.equals(UIElementTypesConst.LINK) || elementUIType.equals(UIElementTypesConst.RADIOBUTTON)
				|| elementUIType.equals(UIElementTypesConst.CHECKBOX))
			actionType = UIActionTypeConst.CLICK;
		else if (elementUIType.equals(UIElementTypesConst.TEXTBOX))
			actionType = UIActionTypeConst.INPUT;
		else if (elementUIType.equals(UIElementTypesConst.SELECTLIST))
			actionType = UIActionTypeConst.SELECT;
		else
			setErrorCode(WebDriverErrorConst.ELEMENT_TYPE_NOT_SUPPORTED);

		return actionType;
	}

	/**
	 * do click on UI element
	 */
	public ApplicationConst doClick() {
		try {
			webElement.click();
			return ApplicationConst.SUCCESS;
		}
		catch (Exception e) {
			setErrorCode(WebDriverErrorConst.COULD_NOT_CLICK_ELEMENT);
			return ApplicationConst.FAILED;
		}
	}

	/**
	 * do input on UI element
	 */
	public ApplicationConst doInput(String value, boolean clearContentsRequired) {
		try {
			if (clearContentsRequired) {
				webElement.click();
				webElement.sendKeys(Keys.CONTROL + "A");
				webElement.sendKeys(Keys.BACK_SPACE);
			}

			webElement.sendKeys(value);

			return ApplicationConst.SUCCESS;
		}
		catch (Exception e) {
			setErrorCode(WebDriverErrorConst.COULD_NOT_INPUT_VALUE);
			return ApplicationConst.FAILED;
		}
	}

	/**
	 * do select on UI element
	 */
	public ApplicationConst doSelect(boolean isActualValue, String value) {
		try {
			Select selectBox = new Select(webElement);
			if (isActualValue)
				selectBox.selectByValue(value);
			else
				selectBox.selectByVisibleText(value);
			// selectBox.selectByVisibleText(visibleValue);
			return ApplicationConst.SUCCESS;
		}
		catch (Exception e) {
			setErrorCode(WebDriverErrorConst.COULD_NOT_SELECT_VALUE);
			return ApplicationConst.FAILED;
		}
	}

	/**
	 * close driver
	 */
	public ApplicationConst closeDriver() {
		try {
			driver.quit();
			return ApplicationConst.SUCCESS;
		}
		catch (Exception e) {
			setErrorCode(WebDriverErrorConst.COULD_NOT_CLOSE_DRIVER);
			return ApplicationConst.FAILED;
		}
	}

	/**
	 * setters & getters
	 */
	public WebDriver getDriver() {
		return driver;
	}

	private void setErrorCode(WebDriverErrorConst errorCode) {
		System.out.println(errorCode);
		this.errorCode = errorCode;
	}

	public WebDriverErrorConst getErrorCode() {
		return errorCode;
	}
}
