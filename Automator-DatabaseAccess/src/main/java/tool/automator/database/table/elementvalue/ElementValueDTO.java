package tool.automator.database.table.elementvalue;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

import tool.automator.database.xml.models.ElementValueModelXMLBind;

@Entity
@Table(name = "element_values")
public class ElementValueDTO extends AbstractPersistable<Long> implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "element_id", nullable = false)
	private Long elementId;

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
	public ElementValueDTO() {
		super();
	}

	public ElementValueDTO(String scriptValue) {
		super();
		setId(-1L);
		this.elementId = -1L;
		this.scriptValue = scriptValue;
	}

	public ElementValueDTO(Long elementId, String scriptValue, String actualValue, boolean turnsPage, boolean hidden) {
		super();
		update(elementId, scriptValue, actualValue, turnsPage, hidden);
		Date timestamp = new Date();
		this.created = timestamp;
		this.modified = timestamp;
	}

	public void update(Long elementId, String scriptValue, String actualValue, boolean turnsPage, boolean hidden) {
		this.elementId = elementId;
		this.scriptValue = scriptValue;
		this.actualValue = actualValue;
		this.turnsPage = turnsPage;
		this.hidden = hidden;
		this.modified = new Date();
	}

	public ElementValueModelXMLBind getElementValueModelXMLBind() {
		return new ElementValueModelXMLBind(getId(), elementId, scriptValue, actualValue, turnsPage, hidden);
	}

	// getters & setters
	public Long getElementId() {
		return elementId;
	}

	public void setElementId(Long elementId) {
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
		if (getId() == -1)
			return getId() + " " + scriptValue;
		else
			return getId() + " " + elementId + " " + scriptValue + " " + actualValue + " " + turnsPage + " " + hidden;
	}
}
