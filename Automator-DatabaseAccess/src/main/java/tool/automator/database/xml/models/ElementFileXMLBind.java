package tool.automator.database.xml.models;

import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root
public class ElementFileXMLBind {
	@ElementList
	private List<ElementModelXMLBind> elementList;

	public ElementFileXMLBind() {
		
	}
	
	public ElementFileXMLBind(List<ElementModelXMLBind> elementList) {
		this.elementList = elementList;
	}
	
	public List<ElementModelXMLBind> getElementList() {
		return elementList;
	}

	public void setElementList(List<ElementModelXMLBind> elementList) {
		this.elementList = elementList;
	}
}
