package tool.automator.common.models;

public interface ElementModelIf {
	public Long getId();

	public String getScriptName();

	public Long getPageId();

	public String getUIIdentifier();

	public String getUIIdentifierGetType();

	public String getUIElementType();

	public int getRelativeOrder();

	public Long getDefaultElementValueId();

	public int getRelease();

	public boolean isOptional();
}
