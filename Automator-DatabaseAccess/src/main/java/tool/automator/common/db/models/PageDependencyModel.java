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
@Table(name = "page_dependencies")
public class PageDependencyModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private int id;

	@Column(name = "source_page_id", nullable = false)
	private int sourcePageId;

	@Column(name = "destination_page_id", nullable = false)
	private int destinationPageId;

	@Column(name = "created", nullable = false)
	private Date created;

	@Column(name = "modified", nullable = false)
	private Date modified;

	// constructor
	public PageDependencyModel() {
		super();
	}

	public PageDependencyModel(int sourcePageId, int destinationPageId) {
		super();
		this.sourcePageId = sourcePageId;
		this.destinationPageId = destinationPageId;
		this.created = new Date();
		this.modified = new Date();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSourcePageId() {
		return sourcePageId;
	}

	public void setSourcePageId(int sourcePageId) {
		this.sourcePageId = sourcePageId;
	}

	public int getDestinationPageId() {
		return destinationPageId;
	}

	public void setDestinationPageId(int destinationPageId) {
		this.destinationPageId = destinationPageId;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getCreated() {
		return created;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public Date getModified() {
		return modified;
	}

	public String toString() {
		return id + " " + sourcePageId + " " + destinationPageId;
	}
}
