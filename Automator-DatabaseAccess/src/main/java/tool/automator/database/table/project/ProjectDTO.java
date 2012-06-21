package tool.automator.database.table.project;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

import tool.automator.database.xml.models.ProjectModelXMLBind;

@Entity
@Table(name = "projects")
public class ProjectDTO extends AbstractPersistable<Long> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "projectName", nullable = false)
	private String projectName;

	@Column(name = "created", nullable = false)
	private Date created;

	@Column(name = "modified", nullable = false)
	private Date modified;

	// constructor
	public ProjectDTO() {
		super();
	}

	public ProjectDTO(String projectName) {
		super();
		this.projectName = projectName;
		this.created = new Date();
		this.modified = new Date();
	}

	public ProjectModelXMLBind getProjectModelXMLBind() {
		return new ProjectModelXMLBind(getId(), projectName);
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
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
		return getId() + " " + projectName;
	}
}
