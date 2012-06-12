package tool.automator.database.table.elementcondition;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

import tool.automator.database.table.ConditionIf;

@Entity
@Table(name = "element_conditions")
public class ElementConditionDTO extends AbstractPersistable<Long> implements Serializable, ConditionIf {
	private static final long serialVersionUID = 1L;

	@Column(name = "element_restriction_id", nullable = false)
	private Long elementRestrictionId;

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
	public ElementConditionDTO() {
		super();
	}

	public ElementConditionDTO(Long elementRestrictionId, Long pageId, Long elementId, Long elementValueId) {
		super();
		update(elementRestrictionId, pageId, elementId, elementValueId);
		Date timestamp = new Date();
		this.created = timestamp;
		this.modified = timestamp;
	}

	public void update(Long elementRestrictionId, Long pageId, Long elementId, Long elementValueId) {
		this.elementRestrictionId = elementRestrictionId;
		this.pageId = pageId;
		this.elementId = elementId;
		this.elementValueId = elementValueId;
		this.modified = new Date();
	}

	public Long getConditionNumber() {
		return getElementRestrictionId();
	}

	public Long getElementRestrictionId() {
		return elementRestrictionId;
	}

	public void setElementRestrictionId(Long elementRestrictionId) {
		this.elementRestrictionId = elementRestrictionId;
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
		return getId() + " " + elementRestrictionId + " " + pageId + " " + elementId + " " + elementValueId;
	}
}
