package tool.automator.executor.xml.models;

import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root
public class ProjectFileXMLBind {
	@ElementList
	private List<ProjectModelXMLBind> projectList;

	public ProjectFileXMLBind() {
		
	}
	
	public ProjectFileXMLBind(List<ProjectModelXMLBind> projectList) {
		this.projectList = projectList;
	}
	
	public List<ProjectModelXMLBind> getProjectList() {
		return projectList;
	}

	public void setProjectList(List<ProjectModelXMLBind> projectList) {
		this.projectList = projectList;
	}
}
