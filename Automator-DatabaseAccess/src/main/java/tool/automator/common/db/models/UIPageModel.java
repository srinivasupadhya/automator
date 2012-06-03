package tool.automator.common.db.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ui_pages")
public class UIPageModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private int id;

	@Column(name = "pageName", nullable = false)
	private String pageName;

	@Column(name = "project_id", nullable = false)
	private int projectId;

	@Column(name = "waitTime", nullable = false)
	private int waitTime;

	@Column(name = "startPage", nullable = false)
	private boolean startPage;

	@Column(name = "pageGetURL", nullable = true)
	private String pageGetURL;

	@Column(name = "pageIdentifier", nullable = true)
	private String pageIdentifier;

	@Column(name = "pageIdentifierGetType", nullable = true)
	private String pageIdentifierGetType;

	@Column(name = "pageIdentifierValue", nullable = true)
	private String pageIdentifierValue;

	@Column(name = "created", nullable = false)
	private Date created;

	@Column(name = "modified", nullable = false)
	private Date modified;

	// constructor
	public UIPageModel() {
		super();
	}

	public UIPageModel(String pageName, int projectId, boolean startPage, int waitTime, String pageGetURL,
			String pageIdentifier, String pageIdentifierGetType, String pageIdentifierValue) {
		super();
		this.pageName = pageName;
		this.projectId = projectId;
		this.startPage = startPage;
		this.waitTime = waitTime;
		this.pageGetURL = pageGetURL;
		this.pageIdentifier = pageIdentifier;
		this.pageIdentifierGetType = pageIdentifierGetType;
		this.pageIdentifierValue = pageIdentifierValue;
		this.created = new Date();
		this.modified = new Date();
	}

	// getters & setters
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

	public void setStartPage(boolean startPage) {
		this.startPage = startPage;
	}

	public boolean isStartPage() {
		return startPage;
	}

	public String getPageGetURL() {
		return pageGetURL;
	}

	public void setWaitTime(int waitTime) {
		this.waitTime = waitTime;
	}

	public int getWaitTime() {
		return waitTime;
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

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public String toString() {
		return id + " " + pageName + " " + projectId + " " + startPage + " " + waitTime + " " + pageGetURL + " "
				+ pageIdentifier + " " + pageIdentifierGetType + " " + pageIdentifierValue;
	}
}
