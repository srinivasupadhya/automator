package tool.automator.database.table.element;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

import tool.automator.database.xml.models.ElementModelXMLBind;

@Entity
@Table(name = "elements")
public class ElementDTO extends AbstractPersistable<Long> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "scriptName", nullable = false)
	private String scriptName;

	@Column(name = "page_id", nullable = false)
	private Long pageId;

	@Column(name = "uIIdentifier", nullable = true)
	private String uIIdentifier;

	@Column(name = "uIIdentifierGetType", nullable = true)
	private String uIIdentifierGetType;

	@Column(name = "uIElementType", nullable = false)
	private String uIElementType;

	@Column(name = "relativeOrder", nullable = false)
	private int relativeOrder;

	@Column(name = "releaseNumber", nullable = false)
	private int release;

	@Column(name = "optional", nullable = false)
	private boolean optional;

	@Column(name = "hidden", nullable = false)
	private boolean hidden;

	@Column(name = "created", nullable = false)
	private Date created;

	@Column(name = "modified", nullable = false)
	private Date modified;

	// constructor
	public ElementDTO() {
		super();
	}

	public ElementDTO(String scriptName, Long pageId) {
		super();
		setId(0L);
		this.scriptName = scriptName;
		this.pageId = pageId;
		this.uIIdentifier = null;
		this.uIIdentifierGetType = null;
		this.uIElementType = null;
		this.relativeOrder = -1;
		this.release = 1;
		this.optional = false;
		this.hidden = false;
		this.created = new Date();
		this.modified = new Date();
	}

	public ElementDTO(String scriptName, Long pageId, String uIIdentifier, String uIIdentifierGetType, String uIElementType, int relativeOrder, int release,
			boolean optional, boolean hidden) {
		super();
		update(scriptName, pageId, uIIdentifier, uIIdentifierGetType, uIElementType, relativeOrder, release, optional, hidden);
		Date timestamp = new Date();
		this.created = timestamp;
		this.modified = timestamp;
	}

	public void update(String scriptName, Long pageId, String uIIdentifier, String uIIdentifierGetType, String uIElementType, int relativeOrder, int release,
			boolean optional, boolean hidden) {
		this.scriptName = scriptName;
		this.pageId = pageId;
		this.uIIdentifier = uIIdentifier;
		this.uIIdentifierGetType = uIIdentifierGetType;
		this.uIElementType = uIElementType;
		this.relativeOrder = relativeOrder;
		this.release = release;
		this.optional = optional;
		this.hidden = hidden;

		this.modified = new Date();
	}

	public ElementModelXMLBind getElementModelXMLBind() {
		return new ElementModelXMLBind(getId(), scriptName, pageId, uIIdentifier, uIIdentifierGetType, uIElementType, relativeOrder, -1L, release, optional);
	}

	// getters & setters
	public String getScriptName() {
		return scriptName;
	}

	public void setScriptName(String scriptName) {
		this.scriptName = scriptName;
	}

	public Long getPageId() {
		return pageId;
	}

	public void setPageId(Long pageId) {
		this.pageId = pageId;
	}

	public String getUIIdentifier() {
		return uIIdentifier;
	}

	public void setUIIdentifier(String uIIdentifier) {
		this.uIIdentifier = uIIdentifier;
	}

	public String getUIIdentifierGetType() {
		return uIIdentifierGetType;
	}

	public void setUIIdentifierGetType(String uIIdentifierGetType) {
		this.uIIdentifierGetType = uIIdentifierGetType;
	}

	public String getUIElementType() {
		return uIElementType;
	}

	public void setUIElementType(String uIElementType) {
		this.uIElementType = uIElementType;
	}

	public int getRelativeOrder() {
		return relativeOrder;
	}

	public void setRelativeOrder(int relativeOrder) {
		this.relativeOrder = relativeOrder;
	}

	public int getRelease() {
		return release;
	}

	public void setRelease(int release) {
		this.release = release;
	}

	public boolean isOptional() {
		return optional;
	}

	public void setOptional(boolean optional) {
		this.optional = optional;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	public boolean isHidden() {
		return hidden;
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
		if (getId() == -1)
			return getId() + " " + scriptName;
		else
			return getId() + " " + scriptName + " " + pageId + " " + uIIdentifier + " " + uIIdentifierGetType + " " + uIElementType + " " + relativeOrder + " "
					+ release + " " + optional;
	}
}
