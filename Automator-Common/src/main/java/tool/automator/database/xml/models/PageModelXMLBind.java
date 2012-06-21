package tool.automator.database.xml.models;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import tool.automator.common.models.UIPageModelIf;

@Root
public class PageModelXMLBind implements UIPageModelIf {
	@Element
	private Long id;

	@Element
	private String pageName;

	@Element
	private Long projectId;

	@Element
	private int waitTime;

	@Element
	private boolean startPage;

	@Element(required = false)
	private String pageGetURL;

	@Element(required = false)
	private String pageIdentifier;

	@Element(required = false)
	private String pageIdentifierGetType;

	@Element(required = false)
	private String pageIdentifierValue;

	// constructor
	public PageModelXMLBind() {

	}

	public PageModelXMLBind(Long id, String pageName, Long projectId, boolean startPage, int waitTime, String pageGetURL, String pageIdentifier,
			String pageIdentifierGetType, String pageIdentifierValue) {
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
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
