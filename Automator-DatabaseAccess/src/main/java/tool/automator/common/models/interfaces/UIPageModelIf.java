package tool.automator.common.models.interfaces;

public interface UIPageModelIf {
	public int getId();

	public String getPageName();

	public int getProjectId();

	public boolean isStartPage();

	public String getPageGetURL();

	public int getWaitTime();

	public String getPageIdentifier();

	public String getPageIdentifierGetType();

	public String getPageIdentifierValue();
}
