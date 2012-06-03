package tool.automator.executor.xml.models;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import tool.automator.common.db.models.UIPageModel;
import tool.automator.common.models.interfaces.UIPageModelIf;

@Root
public class PageModelXMLBind implements UIPageModelIf {
	@Element
	private int id;

	@Element
	private String pageName;

	@Element
	private int projectId;

	@Element
	private int waitTime;

	@Element
	private boolean startPage;

	@Element(required=false)
	private String pageGetURL;

	@Element(required=false)
	private String pageIdentifier;

	@Element(required=false)
	private String pageIdentifierGetType;

	@Element(required=false)
	private String pageIdentifierValue;

	// constructor
	public PageModelXMLBind() {
		
	}
	
	public PageModelXMLBind(int id, String pageName, int projectId, boolean startPage, int waitTime, String pageGetURL,
			String pageIdentifier, String pageIdentifierGetType, String pageIdentifierValue) {
		super();
		this.id = id;
		this.pageName = pageName;
		this.projectId = projectId;
		this.startPage = startPage;
		this.waitTime = waitTime;
		this.pageGetURL = pageGetURL;
		this.pageIdentifier = pageIdentifier;
		this.pageIdentifierGetType = pageIdentifierGetType;
		this.pageIdentifierValue = pageIdentifierValue;
	}

	public PageModelXMLBind(UIPageModel page) {
		this.id = page.getId();
		this.pageName = page.getPageName();
		this.projectId = page.getProjectId();
		this.startPage = page.isStartPage();
		this.waitTime = page.getWaitTime();
		this.pageGetURL = page.getPageGetURL();
		this.pageIdentifier = page.getPageIdentifier();
		this.pageIdentifierGetType = page.getPageIdentifierGetType();
		this.pageIdentifierValue = page.getPageIdentifierValue();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public int getWaitTime() {
		return waitTime;
	}

	public void setWaitTime(int waitTime) {
		this.waitTime = waitTime;
	}

	public boolean isStartPage() {
		return startPage;
	}

	public void setStartPage(boolean startPage) {
		this.startPage = startPage;
	}

	public String getPageGetURL() {
		return pageGetURL;
	}

	public void setPageGetURL(String pageGetURL) {
		this.pageGetURL = pageGetURL;
	}

	public String getPageIdentifier() {
		return pageIdentifier;
	}

	public void setPageIdentifier(String pageIdentifier) {
		this.pageIdentifier = pageIdentifier;
	}

	public String getPageIdentifierGetType() {
		return pageIdentifierGetType;
	}

	public void setPageIdentifierGetType(String pageIdentifierGetType) {
		this.pageIdentifierGetType = pageIdentifierGetType;
	}

	public String getPageIdentifierValue() {
		return pageIdentifierValue;
	}

	public void setPageIdentifierValue(String pageIdentifierValue) {
		this.pageIdentifierValue = pageIdentifierValue;
	}
}
