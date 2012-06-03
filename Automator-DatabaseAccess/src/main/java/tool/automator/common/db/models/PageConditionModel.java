package tool.automator.common.db.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import tool.automator.common.models.interfaces.ConditionIf;

@Entity
@Table(name = "page_conditions")
public class PageConditionModel implements Serializable, ConditionIf {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private int id;

	@Column(name = "page_dependency_id", nullable = false)
	private int pageDependencyId;

	@Column(name = "page_id", nullable = false)
	private int pageId;

	@Column(name = "element_id", nullable = false)
	private int elementId;

	@Column(name = "element_value_id", nullable = false)
	private int elementValueId;

	@Column(name = "created", nullable = false)
	private Date created;

	@Column(name = "modified", nullable = false)
	private Date modified;

	// constructor
	public PageConditionModel() {
		super();
	}

	public PageConditionModel(int pageDependencyId, int pageId, int elementId, int elementValueId) {
		super();
		this.pageDependencyId = pageDependencyId;
		this.pageId = pageId;
		this.elementId = elementId;
		this.elementValueId = elementValueId;
		this.created = new Date();
		this.modified = new Date();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getConditionNumber() {
		return getPageDependencyId();
	}
	
	public int getPageDependencyId() {
		return pageDependencyId;
	}

	public void setPageDependencyId(int pageDependencyId) {
		this.pageDependencyId = pageDependencyId;
	}

	public int getPageId() {
		return pageId;
	}

	public void setPageId(int pageId) {
		this.pageId = pageId;
	}

	public int getElementId() {
		return elementId;
	}

	public void setElementId(int elementId) {
		this.elementId = elementId;
	}

	public int getElementValueId() {
		return elementValueId;
	}

	public void setElementValueId(int elementValueId) {
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
		return id + " " + pageDependencyId + " " + pageId + " " + elementId + " " + elementValueId;
	}
}
