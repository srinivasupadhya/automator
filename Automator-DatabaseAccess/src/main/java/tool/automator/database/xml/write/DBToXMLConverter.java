package tool.automator.database.xml.write;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import tool.automator.database.constants.DBSettingsPathConst;
import tool.automator.database.factory.DAOFactory;
import tool.automator.database.table.element.ElementDTO;
import tool.automator.database.table.element.ElementService;
import tool.automator.database.table.elementvalue.ElementValueDTO;
import tool.automator.database.table.elementvalue.ElementValueService;
import tool.automator.database.table.project.ProjectDTO;
import tool.automator.database.table.project.ProjectService;
import tool.automator.database.table.uipage.UIPageDTO;
import tool.automator.database.table.uipage.UIPageService;
import tool.automator.database.xml.models.ElementFileXMLBind;
import tool.automator.database.xml.models.ElementModelXMLBind;
import tool.automator.database.xml.models.ElementValueFileXMLBind;
import tool.automator.database.xml.models.ElementValueModelXMLBind;
import tool.automator.database.xml.models.PageFileXMLBind;
import tool.automator.database.xml.models.PageModelXMLBind;
import tool.automator.database.xml.models.ProjectFileXMLBind;
import tool.automator.database.xml.models.ProjectModelXMLBind;

public class DBToXMLConverter {
	public static void main(String[] args) throws Exception {
		System.out.println("Writing settings into: " + DBSettingsPathConst.BASE_XML_PATH);

		Serializer serializer = new Persister();

		ProjectService projectService = DAOFactory.getInstance().getProjectService();
		List<ProjectDTO> projects = projectService.getAllProjects();
		List<ProjectModelXMLBind> projectXMLBinds = new ArrayList<ProjectModelXMLBind>();
		for (ProjectDTO currentProject : projects) {
			projectXMLBinds.add(new ProjectModelXMLBind(currentProject));
		}

		ProjectFileXMLBind projectFileXMLBind = new ProjectFileXMLBind(projectXMLBinds);
		serializer.write(projectFileXMLBind, new File(DBSettingsPathConst.PROJECT_XML_FILE_NAME));

		System.out.println("Finished writing Project settings into: " + DBSettingsPathConst.PROJECT_XML_FILE_NAME);

		UIPageService uiPageService = DAOFactory.getInstance().getUIPageService();
		List<UIPageDTO> uiPages = uiPageService.getAllPages();
		List<PageModelXMLBind> pageXMLBinds = new ArrayList<PageModelXMLBind>();
		for (UIPageDTO currentPage : uiPages) {
			pageXMLBinds.add(new PageModelXMLBind(currentPage));
		}

		PageFileXMLBind pageFileXMLService = new PageFileXMLBind(pageXMLBinds);
		serializer.write(pageFileXMLService, new File(DBSettingsPathConst.PAGE_XML_FILE_NAME));

		System.out.println("Finished writing Page settings into: " + DBSettingsPathConst.PAGE_XML_FILE_NAME);

		ElementService elementDAO = DAOFactory.getInstance().getElementService();
		List<ElementDTO> elements = elementDAO.getAllElements();
		List<ElementModelXMLBind> elementXMLBinds = new ArrayList<ElementModelXMLBind>();
		for (ElementDTO currentElement : elements) {
			elementXMLBinds.add(new ElementModelXMLBind(currentElement));
		}

		ElementFileXMLBind elementFileXMLService = new ElementFileXMLBind(elementXMLBinds);
		serializer.write(elementFileXMLService, new File(DBSettingsPathConst.ELEMENT_XML_FILE_NAME));

		System.out.println("Finished writing Element settings into: " + DBSettingsPathConst.ELEMENT_XML_FILE_NAME);

		ElementValueService elementValueDAO = DAOFactory.getInstance().getElementValueService();
		List<ElementValueDTO> elementValues = elementValueDAO.getAllElementValues();
		List<ElementValueModelXMLBind> elementValueXMLBinds = new ArrayList<ElementValueModelXMLBind>();
		for (ElementValueDTO currentElementValue : elementValues) {
			elementValueXMLBinds.add(new ElementValueModelXMLBind(currentElementValue));
		}

		ElementValueFileXMLBind elementValueFileXMLBind = new ElementValueFileXMLBind(elementValueXMLBinds);
		serializer.write(elementValueFileXMLBind, new File(DBSettingsPathConst.ELEMENT_VALUE_XML_FILE_NAME));

		System.out.println("Finished writing Element-Value settings into: " + DBSettingsPathConst.ELEMENT_VALUE_XML_FILE_NAME);

		System.out.println("Finished writing settings into: " + DBSettingsPathConst.BASE_XML_PATH);
		
		System.exit(0);
	}
}
