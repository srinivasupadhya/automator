package tool.automator.executor.xml.models;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import tool.automator.common.db.models.ElementValueModel;
import tool.automator.common.models.interfaces.ElementValueModelIf;

@Root
public class ElementValueModelXMLBind implements ElementValueModelIf {
	@Element
	private int id;

	@Element
	private int elementId;

	@Element
	private String scriptValue;

	@Element(required=false)
	private String actualValue;

	@Element
	private boolean turnsPage;

	@Element
	private boolean hidden;

	public ElementValueModelXMLBind() {
		
	}
	
	public ElementValueModelXMLBind(int id, int elementId, String scriptValue, String actualValue, boolean turnsPage,
			boolean hidden) {
		super();
		this.id = id;
		this.elementId = elementId;
		this.scriptValue = scriptValue;
		this.actualValue = actualValue;
		this.turnsPage = turnsPage;
		this.hidden = hidden;
	}

	public ElementValueModelXMLBind(ElementValueModel elementValue) {
		this.id = elementValue.getId();
		this.elementId = elementValue.getElementId();
		this.scriptValue = elementValue.getScriptValue();
		this.actualValue = elementValue.getActualValue();
		this.turnsPage = elementValue.getTurnsPage();
		this.hidden = elementValue.isHidden();
	}

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

	public boolean getTurnsPage() {
		return turnsPage;
	}

	public void setTurnsPage(boolean turnsPage) {
		this.turnsPage = turnsPage;
	}

	public boolean isHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}
}
