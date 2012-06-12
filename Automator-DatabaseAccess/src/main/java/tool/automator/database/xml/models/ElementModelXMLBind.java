package tool.automator.database.xml.models;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import tool.automator.database.table.element.ElementDTO;
import tool.automator.database.table.element.ElementModelIf;

@Root
public class ElementModelXMLBind implements ElementModelIf {
	@Element
	private Long id;

	@Element
	private String scriptName;

	@Element
	private Long pageId;

	@Element(required = false)
	private String UIIdentifier;

	@Element(required = false)
	private String UIIdentifierGetType;

	@Element
	private String UIElementType;

	@Element
	private int relativeOrder;

	@Element(required = false)
	private int defaultElementValueId;

	@Element
	private int release;

	@Element
	private boolean optional;

	@Element
	private boolean hidden;

	// constructor
	public ElementModelXMLBind() {

	}

	public ElementModelXMLBind(Long id, String scriptName, Long pageId, String UIIdentifier, String UIIdentifierGetType, String UIElementType, int relativeOrder,
			int defaultElementValueId, int release, boolean optional) {
		super();
		this.id = id;
		this.scriptName = scriptName;
		this.pageId = pageId;
		this.UIIdentifier = UIIdentifier;
		this.UIIdentifierGetType = UIIdentifierGetType;
		this.UIElementType = UIElementType;
		this.relativeOrder = relativeOrder;
		this.defaultElementValueId = defaultElementValueId;
		this.release = release;
		this.optional = optional;
		this.hidden = false;
	}

	public ElementModelXMLBind(ElementDTO element) {
		this.id = element.getId();
		this.scriptName = element.getScriptName();
		this.pageId = element.getPageId();
		this.UIIdentifier = element.getUIIdentifier();
		this.UIIdentifierGetType = element.getUIIdentifierGetType();
		this.UIElementType = element.getUIElementType();
		this.relativeOrder = element.getRelativeOrder();
		// this.defaultElementValueId = element.getDefaultElementValueId();
		this.defaultElementValueId = 0;
		this.release = element.getRelease();
		this.optional = element.isOptional();
		this.hidden = false;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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
		return UIIdentifier;
	}

	public void setUIIdentifier(String UIIdentifier) {
		this.UIIdentifier = UIIdentifier;
	}

	public String getUIIdentifierGetType() {
		return UIIdentifierGetType;
	}

	public void setUIIdentifierGetType(String UIIdentifierGetType) {
		this.UIIdentifierGetType = UIIdentifierGetType;
	}

	public String getUIElementType() {
		return UIElementType;
	}

	public void setUIElementType(String UIElementType) {
		this.UIElementType = UIElementType;
	}

	public int getRelativeOrder() {
		return relativeOrder;
	}

	public void setRelativeOrder(int relativeOrder) {
		this.relativeOrder = relativeOrder;
	}

	public int getDefaultElementValueId() {
		return defaultElementValueId;
	}

	public void setDefaultElementValueId(int defaultElementValueId) {
		this.defaultElementValueId = defaultElementValueId;
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

	public boolean isHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}
}