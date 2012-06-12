package tool.automator.database.xml.models;

import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root
public class PageFileXMLBind {
	@ElementList
	private List<PageModelXMLBind> pageList;

	public PageFileXMLBind() {
		
	}
	
	public PageFileXMLBind(List<PageModelXMLBind> pageList) {
		this.pageList = pageList;
	}
	
	public List<PageModelXMLBind> getPageList() {
		return pageList;
	}

	public void setPageList(List<PageModelXMLBind> pageList) {
		this.pageList = pageList;
	}
}
