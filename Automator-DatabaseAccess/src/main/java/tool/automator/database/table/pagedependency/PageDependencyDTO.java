package tool.automator.database.table.pagedependency;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "page_dependencies")
public class PageDependencyDTO extends AbstractPersistable<Long> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "source_page_id", nullable = false)
	private Long sourcePageId;

	@Column(name = "destination_page_id", nullable = false)
	private Long destinationPageId;

	@Column(name = "created", nullable = false)
	private Date created;

	@Column(name = "modified", nullable = false)
	private Date modified;

	// constructor
	public PageDependencyDTO() {
		super();
	}

	public PageDependencyDTO(Long sourcePageId, Long destinationPageId) {
		super();
		this.sourcePageId = sourcePageId;
		this.destinationPageId = destinationPageId;
		this.created = new Date();
		this.modified = new Date();
	}

	public Long getSourcePageId() {
		return sourcePageId;
	}

	public void setSourcePageId(Long sourcePageId) {
		this.sourcePageId = sourcePageId;
	}

	public Long getDestinationPageId() {
		return destinationPageId;
	}

	public void setDestinationPageId(Long destinationPageId) {
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
		return getId() + " " + sourcePageId + " " + destinationPageId;
	}
}
