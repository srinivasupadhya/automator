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
@Table(name = "element_values")
public class ElementValueModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private int id;

	@Column(name = "element_id", nullable = false)
	private int elementId;

	@Column(name = "scriptValue", nullable = false)
	private String scriptValue;

	@Column(name = "actualValue", nullable = false)
	private String actualValue;

	@Column(name = "turnsPage", nullable = false)
	private boolean turnsPage;

	@Column(name = "hidden", nullable = false)
	private boolean hidden;

	@Column(name = "created", nullable = false)
	private Date created;

	@Column(name = "modified", nullable = false)
	private Date modified;

	// constructor
	public ElementValueModel() {
		super();
	}

	public ElementValueModel(String scriptValue) {
		super();
		this.id = -1;
		this.elementId = -1;
		this.scriptValue = scriptValue;
	}

	public ElementValueModel(int elementId, String scriptValue, String actualValue, boolean turnsPage,
			boolean hidden) {
		super();
		this.elementId = elementId;
		this.scriptValue = scriptValue;
		this.actualValue = actualValue;
		this.turnsPage = turnsPage;
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

	public int getElementId() {
		return elementId;
	}

	public void setElementId(int elementId) {
		this.elementId = elementId;
	}

	public String getScriptValue() {
		return scriptValue;
	}

	public void setScriptValue(String scriptValue) {
		this.scriptValue = scriptValue;
	}

	public String getActualValue() {
		return actualValue;
	}

	public void setActualValue(String actualValue) {
		this.actualValue = actualValue;
	}

	public void setTurnsPage(boolean turnsPage) {
		this.turnsPage = turnsPage;
	}

	public boolean getTurnsPage() {
		return turnsPage;
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
			return id + " " + scriptValue;
		else
			return id + " " + elementId + " " + scriptValue + " " + actualValue + " " + turnsPage + " " + hidden;
	}
}
