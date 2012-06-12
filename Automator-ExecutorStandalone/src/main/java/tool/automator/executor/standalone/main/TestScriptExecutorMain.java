package tool.automator.executor.standalone.main;

import java.io.File;
import java.util.List;

import tool.automator.database.table.element.ElementModelIf;
import tool.automator.database.table.elementvalue.ElementValueModelIf;
import tool.automator.database.table.project.ProjectModelIf;
import tool.automator.database.table.uipage.UIPageModelIf;
import tool.automator.executor.datamanager.ElementDataManager;
import tool.automator.executor.datamanager.ElementValueDataManager;
import tool.automator.executor.datamanager.PageDataManager;
import tool.automator.executor.datamanager.ProjectDataManager;
import tool.automator.executor.standalone.thread.PooledExecutorService;
import tool.automator.executor.xml.read.XMLDataLoader;

public class TestScriptExecutorMain {
	// static DecimalFormat DF = new DecimalFormat("0.000", new DecimalFormatSymbols(Locale.ENGLISH));

	public static void main(String[] args) throws Exception {
		String testScriptsPath;
		if (args == null || args[0] == null || args[0].trim().isEmpty()) {
			System.out.println("Please provide test-script path.");
			return;
		}

		testScriptsPath = args[0];

		// load data
		XMLDataLoader dataLoader = new XMLDataLoader();
		List<? extends ProjectModelIf> projectList = dataLoader.loadProjectData();
		ProjectDataManager projectDAO = new ProjectDataManager(projectList);
		List<? extends UIPageModelIf> pageList = dataLoader.loadPageData();
		PageDataManager pageDAO = new PageDataManager(pageList);
		List<? extends ElementModelIf> elementList = dataLoader.loadElementData();
		ElementDataManager elementDAO = new ElementDataManager(elementList);
		List<? extends ElementValueModelIf> elementValueList = dataLoader.loadElementValueData();
		ElementValueDataManager elementValueDAO = new ElementValueDataManager(elementValueList);

		// threaded execution
		PooledExecutorService executorService = new PooledExecutorService(5);

		File testScriptFolder = new File(testScriptsPath);
		File[] testScripts = testScriptFolder.listFiles();

		for (int i = 0; i < testScripts.length; i++) {
			if (testScripts[i].getName().endsWith(".ts"))
				executorService.start(projectDAO, pageDAO, elementDAO, elementValueDAO, testScripts[i].getAbsolutePath());
		}

		executorService.waitForEnd();
	}
}