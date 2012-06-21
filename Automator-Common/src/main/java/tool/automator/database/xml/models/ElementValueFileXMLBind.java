package tool.automator.database.xml.models;

import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root
public class ElementValueFileXMLBind {
	@ElementList
	private List<ElementValueModelXMLBind> elementValueList;

	public ElementValueFileXMLBind() {
		
	}
	
	public ElementValueFileXMLBind(List<ElementValueModelXMLBind> elementValueList) {
		this.elementValueList = elementValueList;
	}
	
	public List<ElementValueModelXMLBind> getElementValueList() {
		return elementValueList;
	}

	public void setElementValueList(List<ElementValueModelXMLBind> elementValueList) {
		this.elementValueList = elementValueList;
	}
}
