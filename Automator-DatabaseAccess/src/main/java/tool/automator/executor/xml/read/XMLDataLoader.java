package tool.automator.executor.xml.read;

import java.io.File;
import java.util.List;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import tool.automator.common.models.interfaces.ElementModelIf;
import tool.automator.common.models.interfaces.ElementValueModelIf;
import tool.automator.common.models.interfaces.ProjectModelIf;
import tool.automator.common.models.interfaces.UIPageModelIf;
import tool.automator.executor.constants.DBSettingsPathConst;
import tool.automator.executor.xml.models.ElementFileXMLBind;
import tool.automator.executor.xml.models.ElementValueFileXMLBind;
import tool.automator.executor.xml.models.PageFileXMLBind;
import tool.automator.executor.xml.models.ProjectFileXMLBind;

public class XMLDataLoader {
	private Serializer serializer = new Persister();

	public List<? extends ProjectModelIf> loadProjectData() throws Exception {
		ProjectFileXMLBind projectFileXMLBind = serializer.read(ProjectFileXMLBind.class, new File(DBSettingsPathConst.PROJECT_XML_FILE_NAME));
		return projectFileXMLBind.getProjectList();
	}

	public List<? extends UIPageModelIf> loadPageData() throws Exception {
		PageFileXMLBind pageFileXMLBind = serializer.read(PageFileXMLBind.class, new File(DBSettingsPathConst.PAGE_XML_FILE_NAME));
		return pageFileXMLBind.getPageList();
	}

	public List<? extends ElementModelIf> loadElementData() throws Exception {
		ElementFileXMLBind elementFileXMLBind = serializer.read(ElementFileXMLBind.class, new File(DBSettingsPathConst.ELEMENT_XML_FILE_NAME));
		return elementFileXMLBind.getElementList();
	}

	public List<? extends ElementValueModelIf> loadElementValueData() throws Exception {
		ElementValueFileXMLBind elementValueFileXMLBind = serializer.read(ElementValueFileXMLBind.class, new File(DBSettingsPathConst.ELEMENT_VALUE_XML_FILE_NAME));
		return elementValueFileXMLBind.getElementValueList();
	}
}
