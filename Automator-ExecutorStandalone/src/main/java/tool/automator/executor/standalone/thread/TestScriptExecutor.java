package tool.automator.executor.standalone.thread;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import tool.automator.common.constants.UIElementTypesConst;
import tool.automator.common.models.ElementModelIf;
import tool.automator.common.models.ElementValueModelIf;
import tool.automator.common.models.ProjectModelIf;
import tool.automator.common.models.UIPageModelIf;
import tool.automator.database.xml.models.ElementValueModelXMLBind;
import tool.automator.executor.constants.*;
import tool.automator.executor.aircanada.AirCanadaCustomFunction;
import tool.automator.executor.datamanager.ElementDataManager;
import tool.automator.executor.datamanager.ElementValueDataManager;
import tool.automator.executor.datamanager.PageDataManager;
import tool.automator.executor.datamanager.ProjectDataManager;
import tool.automator.executor.parser.TestScriptParser;
import tool.automator.executor.standalone.fileio.FileConsoleAccess;
import tool.automator.executor.util.ElementNValuePair;
import tool.automator.executor.webdriver.WebDriverHandle;

public class TestScriptExecutor implements Runnable {
	private ProjectDataManager projectDAO;
	private PageDataManager pageDAO;
	private ElementDataManager elementDAO;
	private ElementValueDataManager elementValueDAO;
	private String testScriptFileName;

	private FileConsoleAccess fileIO;
	private String[] testScriptLines;

	private String elementScriptName, elementScriptValue;
	private String pageName, genericElementName, functionName;
	private ElementModelIf elementObj, genericElementObj;
	private ElementValueModelIf elementValueObj;
	private ProjectModelIf currentProject = null;
	private UIPageModelIf currentPage = null;
	private List<ElementNValuePair> scriptElementValuePairs = null;

	private void initialize() {
		elementScriptName = null;
		elementScriptValue = null;
		pageName = null;
		// elementName = null;
		genericElementName = null;
		functionName = null;
		elementObj = null;
		genericElementObj = null;
		elementValueObj = null;
	}

	public TestScriptExecutor(ProjectDataManager projectDAO, PageDataManager pageDAO, ElementDataManager elementDAO, ElementValueDataManager elementValueDAO,
			String testScriptFileName) {
		this.projectDAO = projectDAO;
		this.pageDAO = pageDAO;
		this.elementDAO = elementDAO;
		this.elementValueDAO = elementValueDAO;
		this.testScriptFileName = testScriptFileName;
	}

	@Override
	public void run() {
		// get parser
		TestScriptParser parser = new TestScriptParser();
		// get webdriver handle
		WebDriverHandle webDriver = new WebDriverHandle();

		// get test-script
		try {
			fileIO = new FileConsoleAccess(testScriptFileName);
			testScriptLines = fileIO.getTestScript();
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		initialize();

		scriptElementValuePairs = new ArrayList<ElementNValuePair>();

		// interpret each line
		int index;
		for (int i = 0; i < testScriptLines.length && webDriver.getErrorCode() == WebDriverErrorConst.NO_ERRORS; i++) {
			initialize();

			// parse line
			parser.parseLine(testScriptLines[i]);

			if (parser.getCurrentLineType() == LineTypeConst.ELEMENT_VALIDATION || parser.getCurrentLineType() == LineTypeConst.EXECUTABLE_COMMAND) {
				elementScriptName = parser.getElementScriptName();
				elementScriptValue = parser.getElementValue();

				System.out.println(testScriptFileName.substring(testScriptFileName.lastIndexOf('/') + 1) + "=] " + elementScriptName + " : " + elementScriptValue);

				// get project
				if (elementScriptName.equals(ScriptElementConst.PROJECT)) {
					currentProject = projectDAO.getProjectByName(elementScriptValue);
					currentPage = pageDAO.getPageByName("GLOBAL", currentProject.getId());
				}
				// get page
				else if (elementScriptName.equals(ScriptElementConst.PAGE)) {
					pageName = elementScriptValue;
					index = elementScriptValue.indexOf("[");
					if (index >= 0)
						pageName = elementScriptValue.substring(0, index);
					currentPage = pageDAO.getPageByName(pageName, currentProject.getId());

					if (currentPage.getPageGetURL() != null && !currentPage.getPageGetURL().isEmpty()) {
						webDriver.openPage(currentPage.getPageGetURL());

						try {
							Thread.sleep(5000);
						}
						catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
				// start browser
				else if (elementScriptName.equals(ScriptElementConst.BROWSER)) {
					webDriver.start(elementScriptValue, null);
				}
				// back & forward functionality
				else if (parser.getCurrentLineType() == LineTypeConst.EXECUTABLE_COMMAND && elementScriptName.equals(ScriptElementConst.HISTORY)) {
					if (elementScriptValue.equals("Back"))
						webDriver.goBack();
					else if (elementScriptValue.equals("Forward"))
						webDriver.goForward();
				}

				// execute command
				if (parser.getCurrentLineType() == LineTypeConst.EXECUTABLE_COMMAND && !elementScriptName.equals(ScriptElementConst.PAGE)
						&& !elementScriptName.equals(ScriptElementConst.HISTORY)) {
					// elementName = elementScriptName;

					// get element
					elementObj = elementDAO.getElementByName(elementScriptName, currentPage.getId());
					index = elementScriptName.indexOf("[");
					if (index >= 0) {
						genericElementName = elementScriptName.substring(0, index);
						genericElementObj = elementDAO.getElementByName(genericElementName, currentPage.getId());
					}
					else {
						genericElementName = null;
						genericElementObj = null;
					}

					// get element-value
					if (genericElementObj != null)
						elementValueObj = elementValueDAO.getElementValueObj(elementScriptValue, genericElementObj.getId());
					else
						elementValueObj = elementValueDAO.getElementValueObj(elementScriptValue, elementObj.getId());
					if (elementValueObj == null)
						elementValueObj = new ElementValueModelXMLBind(-1L, -1L, elementScriptValue, "", false, false);

					scriptElementValuePairs.add(new ElementNValuePair(elementObj, elementValueObj));

					// do action
					if (!elementObj.getUIElementType().equals(UIElementTypesConst.VARIABLE)) {
						if (elementValueObj.getActualValue() != null && !elementValueObj.getActualValue().isEmpty())
							webDriver.doAction(elementObj, elementValueObj.getActualValue());
						else
							webDriver.doAction(elementObj, elementValueObj.getScriptValue());
					}
				}
				// validate element
				else if (parser.getCurrentLineType() == LineTypeConst.ELEMENT_VALIDATION) {
					if (elementScriptName.equals(ScriptElementConst.PAGE)) {
						try {
							Thread.sleep(10000);
						}
						catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
			// call custom-function
			else if (parser.getCurrentLineType() == LineTypeConst.FUNCTION_CALL) {
				elementScriptName = parser.getFunctionName();
				elementScriptValue = parser.getElementValue();

				functionName = elementScriptName;
				// get element
				elementObj = elementDAO.getElementByName(elementScriptName, currentPage.getId());
				index = elementScriptName.indexOf("[");
				if (index >= 0) {
					genericElementName = elementScriptName.substring(0, index);
					genericElementObj = elementDAO.getElementByName(genericElementName, currentPage.getId());
				}
				else {
					genericElementName = null;
					genericElementObj = null;
				}

				// get element-value
				if (genericElementObj != null)
					elementValueObj = elementValueDAO.getElementValueObj(elementScriptValue, genericElementObj.getId());
				else
					elementValueObj = elementValueDAO.getElementValueObj(elementScriptValue, elementObj.getId());
				if (elementValueObj == null)
					elementValueObj = new ElementValueModelXMLBind(-1L, -1L, elementScriptValue, "", false, false);

				System.out.println(testScriptFileName.substring(testScriptFileName.lastIndexOf('/') + 1) + "=] " + functionName + " : " + elementScriptValue);

				scriptElementValuePairs.add(new ElementNValuePair(elementObj, elementValueObj));

				// call appropriate function
				if (functionName.contains(ScriptElementConst.SELECT_FLIGHT)) {
					AirCanadaCustomFunction.SELECT_FLIGHT(webDriver, scriptElementValuePairs);
				}
				else if (functionName.contains(ScriptElementConst.SELECT_SEAT)) {
					AirCanadaCustomFunction.SELECT_SEAT(webDriver, scriptElementValuePairs);
				}
			}
			else if (parser.getCurrentLineType() == LineTypeConst.END_CASE || parser.getCurrentLineType() == LineTypeConst.END_GLOBAL) {

			}
		}

		try {
			Thread.sleep(5000);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}

		// close driver & file
		webDriver.closeDriver();
		try {
			fileIO.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}