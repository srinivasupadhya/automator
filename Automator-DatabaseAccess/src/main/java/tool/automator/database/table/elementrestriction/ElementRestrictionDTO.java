package tool.automator.database.table.elementrestriction;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "element_restrictions")
public class ElementRestrictionDTO extends AbstractPersistable<Long> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "page_id", nullable = false)
	private Long pageId;

	@Column(name = "element_id", nullable = false)
	private Long elementId;

	@Column(name = "created", nullable = false)
	private Date created;

	@Column(name = "modified", nullable = false)
	private Date modified;

	// constructor
	public ElementRestrictionDTO() {
		super();
	}

	public ElementRestrictionDTO(Long pageId, Long elementId) {
		super();
		this.pageId = pageId;
		this.elementId = elementId;
		this.created = new Date();
		this.modified = new Date();
	}

	public Long getPageId() {
		return pageId;
	}

	public void setPageId(Long pageId) {
		this.pageId = pageId;
	}

	public Long getElementId() {
		return elementId;
	}

	public void setElementId(Long elementId) {
		this.elementId = elementId;
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
		return getId() + " " + pageId + " " + elementId;
	}
}
