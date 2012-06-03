package tool.automator.common.xml.write;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import tool.automator.common.db.dao.factory.DAOFactory;
import tool.automator.common.db.daoif.ElementDAOIf;
import tool.automator.common.db.daoif.ElementValueDAOIf;
import tool.automator.common.db.daoif.ProjectDAOIf;
import tool.automator.common.db.daoif.UIPageDAOIf;
import tool.automator.common.db.models.ElementModel;
import tool.automator.common.db.models.ElementValueModel;
import tool.automator.common.db.models.ProjectModel;
import tool.automator.common.db.models.UIPageModel;
import tool.automator.executor.constants.DBSettingsPathConst;
import tool.automator.executor.xml.models.ElementFileXMLBind;
import tool.automator.executor.xml.models.ElementModelXMLBind;
import tool.automator.executor.xml.models.ElementValueFileXMLBind;
import tool.automator.executor.xml.models.ElementValueModelXMLBind;
import tool.automator.executor.xml.models.PageFileXMLBind;
import tool.automator.executor.xml.models.PageModelXMLBind;
import tool.automator.executor.xml.models.ProjectFileXMLBind;
import tool.automator.executor.xml.models.ProjectModelXMLBind;

public class DBToXMLConverter {
	public static void main(String[] args) throws Exception {
		System.out.println("Writing settings into: " + DBSettingsPathConst.BASE_XML_PATH);

		Serializer serializer = new Persister();

		ProjectDAOIf projectDAO = DAOFactory.getInstance().getProjectDAO();
		List<ProjectModel> projects = projectDAO.getAllProjects();
		List<ProjectModelXMLBind> projectXMLBinds = new ArrayList<ProjectModelXMLBind>();
		for (ProjectModel currentProject : projects) {
			projectXMLBinds.add(new ProjectModelXMLBind(currentProject));
		}

		ProjectFileXMLBind projectFileXMLBind = new ProjectFileXMLBind(projectXMLBinds);
		serializer.write(projectFileXMLBind, new File(DBSettingsPathConst.PROJECT_XML_FILE_NAME));

		System.out.println("Finished writing Project settings into: " + DBSettingsPathConst.PROJECT_XML_FILE_NAME);

		UIPageDAOIf uiPageDAO = DAOFactory.getInstance().getUIPageDAO();
		List<UIPageModel> uiPages = uiPageDAO.getAllPages();
		List<PageModelXMLBind> pageXMLBinds = new ArrayList<PageModelXMLBind>();
		for (UIPageModel currentPage : uiPages) {
			pageXMLBinds.add(new PageModelXMLBind(currentPage));
		}

		PageFileXMLBind pageFileXMLBind = new PageFileXMLBind(pageXMLBinds);
		serializer.write(pageFileXMLBind, new File(DBSettingsPathConst.PAGE_XML_FILE_NAME));

		System.out.println("Finished writing Page settings into: " + DBSettingsPathConst.PAGE_XML_FILE_NAME);

		ElementDAOIf elementDAO = DAOFactory.getInstance().getElementDAO();
		List<ElementModel> elements = elementDAO.getAllElements();
		List<ElementModelXMLBind> elementXMLBinds = new ArrayList<ElementModelXMLBind>();
		for (ElementModel currentElement : elements) {
			elementXMLBinds.add(new ElementModelXMLBind(currentElement));
		}

		ElementFileXMLBind elementFileXMLBind = new ElementFileXMLBind(elementXMLBinds);
		serializer.write(elementFileXMLBind, new File(DBSettingsPathConst.ELEMENT_XML_FILE_NAME));

		System.out.println("Finished writing Element settings into: " + DBSettingsPathConst.ELEMENT_XML_FILE_NAME);

		ElementValueDAOIf elementValueDAO = DAOFactory.getInstance().getElementValueDAO();
		List<ElementValueModel> elementValues = elementValueDAO.getAllElementValues();
		List<ElementValueModelXMLBind> elementValueXMLBinds = new ArrayList<ElementValueModelXMLBind>();
		for (ElementValueModel currentElementValue : elementValues) {
			elementValueXMLBinds.add(new ElementValueModelXMLBind(currentElementValue));
		}

		ElementValueFileXMLBind elementValueFileXMLBind = new ElementValueFileXMLBind(elementValueXMLBinds);
		serializer.write(elementValueFileXMLBind, new File(DBSettingsPathConst.ELEMENT_VALUE_XML_FILE_NAME));

		System.out.println("Finished writing Element-Value settings into: " + DBSettingsPathConst.ELEMENT_VALUE_XML_FILE_NAME);

		System.out.println("Finished writing settings into: " + DBSettingsPathConst.BASE_XML_PATH);
	}
}
