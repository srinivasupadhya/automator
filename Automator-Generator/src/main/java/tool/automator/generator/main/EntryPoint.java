package tool.automator.generator.main;

import java.io.File;

import tool.automator.common.constants.PathConstants;

public class EntryPoint {
	public static void main(String[] args) {
		File testScriptFolder = new File(PathConstants.BASE_TESTSCRIPT_PATH);
		if (!testScriptFolder.exists())
			testScriptFolder.mkdir();

		// String project = args[0];
		// String release = args[1];
		// String browser = args[2];

		String project = "Yahoo";
		String release = "1";
		String browser = "Firefox";

		// System.out.println(project + " " + release + " " + browser);

		new TestScriptsGenerator(project, release, browser).start();
	}
}
