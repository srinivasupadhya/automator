package tool.automator.database.constants;

public interface PathConstants {
	public static String BASE_PROJECT_PATH = "/Users/sriniup/Documents/workspace/Automator-Parent/";

	public static String BASE_XML_PATH = BASE_PROJECT_PATH + "output/DatabaseSettings/";
	public static String PROJECT_XML_FILE_NAME = BASE_XML_PATH + "Project.xml";
	public static String PAGE_XML_FILE_NAME = BASE_XML_PATH + "Page.xml";
	public static String ELEMENT_XML_FILE_NAME = BASE_XML_PATH + "Element.xml";
	public static String ELEMENT_VALUE_XML_FILE_NAME = BASE_XML_PATH + "ElementValue.xml";

	public static String BASE_TESTSCRIPT_PATH = BASE_PROJECT_PATH + "output/GeneratedTestscripts/";
	
	public static final String TESTSCRIPT_EXTENSION = ".ts";
}
