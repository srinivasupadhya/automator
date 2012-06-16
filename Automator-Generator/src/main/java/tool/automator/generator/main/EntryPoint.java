package tool.automator.generator.main;

public class EntryPoint {
	public static void main(String[] args) {
		String project = args[0];
		String release = args[1];
		String browser = args[2];

		project = "Google";
		release = "1";
		browser = "Firefox";

		// System.out.println(project + " " + release + " " + browser);

		new TestScriptsGenerator(project, release, browser).start();
	}
}
