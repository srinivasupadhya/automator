package tool.automator.executor.xml.models;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import tool.automator.common.db.models.ProjectModel;
import tool.automator.common.models.interfaces.ProjectModelIf;

@Root
public class ProjectModelXMLBind implements ProjectModelIf {
	@Element
	private int id;
	
	@Element
	private String projectName;

	// constructor
	public ProjectModelXMLBind() {
		
	}
	
	public ProjectModelXMLBind(int id, String projectName) {
		super();
		this.id = id;
		this.projectName = projectName;
	}
	
	public ProjectModelXMLBind(ProjectModel project) {
		this.id = project.getId();
		this.projectName = project.getProjectName();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
}
