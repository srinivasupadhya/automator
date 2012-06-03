package tool.automator.common.models.interfaces;

public interface ElementModelIf {
	public int getId();

	public String getScriptName();

	public int getPageId();

	public String getUIIdentifier();

	public String getUIIdentifierGetType();

	public String getUIElementType();

	public int getRelativeOrder();

	public int getDefaultElementValueId();

	public int getRelease();

	public boolean isOptional();
}
