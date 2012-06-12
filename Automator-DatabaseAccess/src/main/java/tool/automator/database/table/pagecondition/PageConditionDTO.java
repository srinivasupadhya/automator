package tool.automator.database.table.pagecondition;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

import tool.automator.database.table.ConditionIf;

@Entity
@Table(name = "page_conditions")
public class PageConditionDTO extends AbstractPersistable<Long> implements Serializable, ConditionIf {
	private static final long serialVersionUID = 1L;

	@Column(name = "page_dependency_id", nullable = false)
	private Long pageDependencyId;

	@Column(name = "page_id", nullable = false)
	private Long pageId;

	@Column(name = "element_id", nullable = false)
	private Long elementId;

	@Column(name = "element_value_id", nullable = false)
	private Long elementValueId;

	@Column(name = "created", nullable = false)
	private Date created;

	@Column(name = "modified", nullable = false)
	private Date modified;

	// constructor
	public PageConditionDTO() {
		super();
	}

	public PageConditionDTO(Long pageDependencyId, Long pageId, Long elementId, Long elementValueId) {
		super();
		update(pageDependencyId, pageId, elementId, elementValueId);
		Date timestamp = new Date();
		this.created = timestamp;
		this.modified = timestamp;
	}

	public void update(Long pageDependencyId, Long pageId, Long elementId, Long elementValueId) {
		this.pageDependencyId = pageDependencyId;
		this.pageId = pageId;
		this.elementId = elementId;
		this.elementValueId = elementValueId;
		this.modified = new Date();
	}

	public Long getConditionNumber() {
		return getPageDependencyId();
	}

	public Long getPageDependencyId() {
		return pageDependencyId;
	}

	public void setPageDependencyId(Long pageDependencyId) {
		this.pageDependencyId = pageDependencyId;
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

	public Long getElementValueId() {
		return elementValueId;
	}

	public void setElementValueId(Long elementValueId) {
		this.elementValueId = elementValueId;
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
		return getId() + " " + pageDependencyId + " " + pageId + " " + elementId + " " + elementValueId;
	}
}
