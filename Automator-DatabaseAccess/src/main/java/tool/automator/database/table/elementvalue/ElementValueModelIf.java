package tool.automator.database.table.elementvalue;

public interface ElementValueModelIf {
	public Long getId();

	public Long getElementId();

	public String getScriptValue();

	public String getActualValue();

	public boolean getTurnsPage();

	public boolean isHidden();
}
