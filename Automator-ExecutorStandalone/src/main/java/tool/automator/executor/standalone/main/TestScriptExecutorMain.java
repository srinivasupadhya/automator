package tool.automator.executor.standalone.main;

import java.io.File;
import java.util.List;

import tool.automator.common.constants.PathConstants;
import tool.automator.common.models.ElementModelIf;
import tool.automator.common.models.ElementValueModelIf;
import tool.automator.common.models.ProjectModelIf;
import tool.automator.common.models.UIPageModelIf;
import tool.automator.executor.datamanager.ElementDataManager;
import tool.automator.executor.datamanager.ElementValueDataManager;
import tool.automator.executor.datamanager.PageDataManager;
import tool.automator.executor.datamanager.ProjectDataManager;
import tool.automator.executor.standalone.thread.PooledExecutorService;
import tool.automator.executor.xml.read.XMLDataLoader;

public class TestScriptExecutorMain {
	// static DecimalFormat DF = new DecimalFormat("0.000", new
	// DecimalFormatSymbols(Locale.ENGLISH));

	private static String testScriptsPath;

	public static void main(String[] args) throws Exception {
		validate(args, false);

		if (testScriptsPath == null)
			testScriptsPath = PathConstants.BASE_PROJECT_PATH + "output/GeneratedTestscripts";

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
			if (testScripts[i].getName().endsWith(PathConstants.TESTSCRIPT_EXTENSION))
				executorService.start(projectDAO, pageDAO, elementDAO, elementValueDAO, testScripts[i].getAbsolutePath());
		}

		executorService.waitForEnd();
	}

	private static void validate(String[] args, boolean strict) {
		if (args != null && args.length > 0 && args[0] != null && !args[0].trim().isEmpty()) {
			testScriptsPath = args[0];
		} else {
			System.out.println("Usage: java -jar Automator-ExecutorStandalone <Path to Test-Script>");
			if (strict)
				System.exit(0);
		}
	}
}
