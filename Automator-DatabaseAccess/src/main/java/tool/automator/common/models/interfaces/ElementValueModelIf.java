package tool.automator.common.models.interfaces;

public interface ElementValueModelIf {
	public int getId();

	public int getElementId();

	public String getScriptValue();

	public String getActualValue();

	public boolean getTurnsPage();

	public boolean isHidden();
}
