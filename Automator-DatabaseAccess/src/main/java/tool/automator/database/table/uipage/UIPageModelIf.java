package tool.automator.database.table.uipage;

public interface UIPageModelIf {
	public Long getId();

	public String getPageName();

	public Long getProjectId();

	public boolean isStartPage();

	public String getPageGetURL();

	public int getWaitTime();

	public String getPageIdentifier();

	public String getPageIdentifierGetType();

	public String getPageIdentifierValue();
}
