package tool.automator.database.xml.models;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import tool.automator.common.models.ProjectModelIf;

@Root
public class ProjectModelXMLBind implements ProjectModelIf {
	@Element
	private Long id;

	@Element
	private String projectName;

	// constructor
	public ProjectModelXMLBind() {

	}

	public ProjectModelXMLBind(Long id, String projectName) {
		super();
		this.id = id;
		this.projectName = projectName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
}
