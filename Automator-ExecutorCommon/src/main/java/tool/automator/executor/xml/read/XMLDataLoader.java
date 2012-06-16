package tool.automator.executor.xml.read;

import java.io.File;
import java.util.List;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import tool.automator.database.constants.PathConstants;
import tool.automator.database.table.element.ElementModelIf;
import tool.automator.database.table.elementvalue.ElementValueModelIf;
import tool.automator.database.table.project.ProjectModelIf;
import tool.automator.database.table.uipage.UIPageModelIf;
import tool.automator.database.xml.models.ElementFileXMLBind;
import tool.automator.database.xml.models.ElementValueFileXMLBind;
import tool.automator.database.xml.models.PageFileXMLBind;
import tool.automator.database.xml.models.ProjectFileXMLBind;

public class XMLDataLoader {
	private Serializer serializer = new Persister();

	public List<? extends ProjectModelIf> loadProjectData() throws Exception {
		ProjectFileXMLBind projectFileXMLBind = serializer.read(ProjectFileXMLBind.class, new File(PathConstants.PROJECT_XML_FILE_NAME));
		return projectFileXMLBind.getProjectList();
	}

	public List<? extends UIPageModelIf> loadPageData() throws Exception {
		PageFileXMLBind pageFileXMLBind = serializer.read(PageFileXMLBind.class, new File(PathConstants.PAGE_XML_FILE_NAME));
		return pageFileXMLBind.getPageList();
	}

	public List<? extends ElementModelIf> loadElementData() throws Exception {
		ElementFileXMLBind elementFileXMLBind = serializer.read(ElementFileXMLBind.class, new File(PathConstants.ELEMENT_XML_FILE_NAME));
		return elementFileXMLBind.getElementList();
	}

	public List<? extends ElementValueModelIf> loadElementValueData() throws Exception {
		ElementValueFileXMLBind elementValueFileXMLBind = serializer.read(ElementValueFileXMLBind.class, new File(PathConstants.ELEMENT_VALUE_XML_FILE_NAME));
		return elementValueFileXMLBind.getElementValueList();
	}
}
