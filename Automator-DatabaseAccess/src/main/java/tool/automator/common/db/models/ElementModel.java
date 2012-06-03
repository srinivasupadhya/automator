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
@Table(name = "elements")
public class ElementModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private int id;

	@Column(name = "scriptName", nullable = false)
	private String scriptName;

	@Column(name = "page_id", nullable = false)
	private int pageId;

	@Column(name = "uIIdentifier", nullable = false)
	private String uIIdentifier;

	@Column(name = "uIIdentifierGetType", nullable = false)
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
	public ElementModel() {
		super();
	}

	public ElementModel(String scriptName, int pageId) {
		super();
		this.id = 0;
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

	public ElementModel(String scriptName, int pageId, String uIIdentifier, String uIIdentifierGetType,
			String uIElementType, int relativeOrder, int release, boolean optional, boolean hidden) {
		super();
		this.scriptName = scriptName;
		this.pageId = pageId;
		this.uIIdentifier = uIIdentifier;
		this.uIIdentifierGetType = uIIdentifierGetType;
		this.uIElementType = uIElementType;
		this.relativeOrder = relativeOrder;
		this.release = release;
		this.optional = optional;
		this.hidden = hidden;
		this.created = new Date();
		this.modified = new Date();
	}

	// getters & setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getScriptName() {
		return scriptName;
	}

	public void setScriptName(String scriptName) {
		this.scriptName = scriptName;
	}

	public int getPageId() {
		return pageId;
	}

	public void setPageId(int pageId) {
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
		if (id == -1)
			return id + " " + scriptName;
		else
			return id + " " + scriptName + " " + pageId + " " + uIIdentifier + " " + uIIdentifierGetType + " "
					+ uIElementType + " " + relativeOrder + " " + release + " " + optional;
	}
}
