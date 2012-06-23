package tool.automator.executor.gui.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import tool.automator.common.models.ElementModelIf;
import tool.automator.common.models.ElementValueModelIf;
import tool.automator.common.models.ProjectModelIf;
import tool.automator.common.models.UIPageModelIf;
import tool.automator.executor.constants.ApplicationConst;
import tool.automator.executor.constants.BrowserChoiceConst;
import tool.automator.executor.datamanager.ElementDataManager;
import tool.automator.executor.datamanager.ElementValueDataManager;
import tool.automator.executor.datamanager.PageDataManager;
import tool.automator.executor.datamanager.ProjectDataManager;
import tool.automator.executor.gui.control.ExecutorThread;
import tool.automator.executor.xml.read.XMLDataLoader;
import tools.automator.executor.gui.constants.ImagePathConst;

import com.explodingpixels.macwidgets.BottomBar;
import com.explodingpixels.macwidgets.BottomBarSize;
import com.explodingpixels.macwidgets.DBottomBar;
import com.explodingpixels.macwidgets.MacButtonFactory;
import com.explodingpixels.macwidgets.MacUtils;
import com.explodingpixels.macwidgets.MacWidgetFactory;
import com.explodingpixels.macwidgets.SourceList;
import com.explodingpixels.macwidgets.UnifiedToolBar;
import com.explodingpixels.widgets.WindowUtils;

public class AutomatorUIMain {
	private static JFrame mainFrame = new JFrame();
	private UnifiedToolBar mainUnifiedToolBar;
	private SourceList mainSourceList;
	private BottomBar mainBottomBar;
	// static TestScriptExecutor threadTest;
	final JTextArea textArea = new JTextArea();

	private ProjectDataManager projectDAO;
	private PageDataManager pageDAO;
	private ElementDataManager elementDAO;
	private ElementValueDataManager elementValueDAO;

	public AutomatorUIMain() throws Exception {
		// load data
		XMLDataLoader dataLoader = new XMLDataLoader();
		List<? extends ProjectModelIf> projectList = dataLoader.loadProjectData();
		projectDAO = new ProjectDataManager(projectList);
		List<? extends UIPageModelIf> pageList = dataLoader.loadPageData();
		pageDAO = new PageDataManager(pageList);
		List<? extends ElementModelIf> elementList = dataLoader.loadElementData();
		elementDAO = new ElementDataManager(elementList);
		List<? extends ElementValueModelIf> elementValueList = dataLoader.loadElementValueData();
		elementValueDAO = new ElementValueDataManager(elementValueList);

		// create GUI
		mainFrame.setUndecorated(true);

		// initialize MacWidgets
		MacUtils.makeWindowLeopardStyle(mainFrame.getRootPane());
		WindowUtils.createAndInstallRepaintWindowFocusListener(mainFrame);

		// build UI
		mainUnifiedToolBar = createMenuBar();
		JSplitPane splitPane = createSourceListAndMainArea();
		mainBottomBar = createBottomBar();

		mainFrame.add(mainUnifiedToolBar.getComponent(), BorderLayout.NORTH);
		mainFrame.add(splitPane, BorderLayout.CENTER);
		mainFrame.add(mainBottomBar.getComponent(), BorderLayout.SOUTH);

		// initialize mainFrame
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		mainFrame.setSize(screenSize.width / 2, screenSize.height - 150);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainFrame.setVisible(true);
	}

	public JTextArea getTextArea() {
		return textArea;
	}

	public void setTextArea(String text) {
		this.textArea.setText(text);
	}

	private UnifiedToolBar createMenuBar() {
		// main buttons
		Icon addTCIcon = new ImageIcon(AutomatorUIMain.class.getResource(ImagePathConst.ADDTC));
		Icon openTCIcon = new ImageIcon(AutomatorUIMain.class.getResource(ImagePathConst.OPENTC));
		Icon activeTCIcon = new ImageIcon(AutomatorUIMain.class.getResource(ImagePathConst.ACTIVETC));
		Icon preferences = new ImageIcon(AutomatorUIMain.class.getResource(ImagePathConst.PREFERENCES));
		// close button
		JButton closeButton = new JButton(new ImageIcon(DBottomBar.class.getResource(ImagePathConst.CLOSE)));
		closeButton.setMargin(new Insets(0, 0, 0, 0));
		closeButton.putClientProperty("JButton.segmentPosition", "only");
		closeButton.setFocusable(false);
		closeButton.putClientProperty("JComponent.sizeVariant", "small");

		closeButton.addMouseListener(new SimpleMouseClickListener() {
			public void mouseClicked(MouseEvent e) {
				// clean up actions for close
				WindowEvent clickEvent = new WindowEvent(mainFrame, WindowEvent.WINDOW_CLOSING);
				Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(clickEvent);
			}
		});

		ButtonGroup group = new ButtonGroup();
		group.add(closeButton);

		// add buttons to menu bar
		UnifiedToolBar toolBar = new UnifiedToolBar();
		toolBar.addComponentToLeft(MacButtonFactory.makeUnifiedToolBarButton(new JButton("New", addTCIcon)));
		toolBar.addComponentToLeft(MacButtonFactory.makeUnifiedToolBarButton(new JButton("Open", openTCIcon)));
		toolBar.addComponentToLeft(MacButtonFactory.makeUnifiedToolBarButton(new JButton("Process", activeTCIcon)));
		toolBar.addComponentToLeft(MacButtonFactory.makeUnifiedToolBarButton(new JButton("Preferences", preferences)));
		toolBar.addComponentToRight(closeButton);

		toolBar.installWindowDraggerOnWindow(mainFrame);

		return toolBar;
	}

	private JSplitPane createSourceListAndMainArea() {
		mainSourceList = new SourceList();

		// JPanel dataBaseMainPanel = createDataBaseMainPanel();
		JPanel testCaseMainPanel = createTestCaseMainPanel();

		// panel.add(testpanel);

		JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.putClientProperty("JComponent.sizeVariant", "small");
		tabbedPane.setFont(new Font("Lucida Grande", Font.PLAIN, 11));

		// JSplitPane splitPane = MacWidgetFactory.createSplitPaneForSourceList(mainSourceList, tabbedPane);
		JSplitPane splitPane = MacWidgetFactory.createSplitPaneForSourceList(mainSourceList, testCaseMainPanel);

		// splitPane.setDividerLocation(300);
		splitPane.setDividerLocation(0);

		return splitPane;
	}

	private JPanel createTestCaseMainPanel() {
		Font font = new Font("monospace", Font.PLAIN, 13);
		textArea.setFont(font);
		JScrollPane scrollPane = new JScrollPane(textArea);

		// Buttons
		JPanel controlsPanel = new JPanel();
		controlsPanel.setLayout(new FlowLayout());

		final JButton saveButton = new JButton(new ImageIcon(DBottomBar.class.getResource(ImagePathConst.SAVE)));
		saveButton.setMargin(new Insets(0, 0, 0, 0));
		// saveButton.putClientProperty("JButton.segmentPosition", "only");
		saveButton.setEnabled(false);

		saveButton.addMouseListener(new SimpleMouseClickListener() {
			public void mouseClicked(MouseEvent e) {
				// save test-script to file
			}
		});

		final Object nextStepMonitor = new Object();
		final Object nextLineMonitor = new Object();

		final JButton stopButton = new JButton(new ImageIcon(DBottomBar.class.getResource(ImagePathConst.STOP)));
		stopButton.setMargin(new Insets(0, 0, 0, 0));
		stopButton.putClientProperty("JButton.segmentPosition", "only");
		stopButton.setEnabled(false);

		final JButton pauseButton = new JButton(new ImageIcon(DBottomBar.class.getResource(ImagePathConst.PAUSE)));
		pauseButton.setMargin(new Insets(0, 0, 0, 0));
		pauseButton.putClientProperty("JButton.segmentPosition", "only");
		pauseButton.setEnabled(false);

		final JButton nextStepButton = new JButton(new ImageIcon(DBottomBar.class.getResource(ImagePathConst.NEXTSTEP)));
		nextStepButton.setMargin(new Insets(0, 0, 0, 0));
		nextStepButton.putClientProperty("JButton.segmentPosition", "only");
		nextStepButton.setEnabled(false);

		nextStepButton.addMouseListener(new SimpleMouseClickListener() {
			public void mouseClicked(MouseEvent e) {
				synchronized (nextStepMonitor) {
					nextStepMonitor.notifyAll();
				}
			}
		});

		final JButton nextLineButton = new JButton(new ImageIcon(DBottomBar.class.getResource(ImagePathConst.NEXTLINE)));
		nextLineButton.setMargin(new Insets(0, 0, 0, 0));
		nextLineButton.putClientProperty("JButton.segmentPosition", "only");
		nextLineButton.setEnabled(false);

		nextLineButton.addMouseListener(new SimpleMouseClickListener() {
			public void mouseClicked(MouseEvent e) {
				synchronized (nextLineMonitor) {
					nextLineMonitor.notifyAll();
				}
			}
		});

		final JComboBox runModeComboBox = new JComboBox(new String[] { "Auto", "Step", "Line" });
		final JComboBox browerComboBox = new JComboBox(new String[] { BrowserChoiceConst.FIREFOX, BrowserChoiceConst.EXPLORER, BrowserChoiceConst.CHROME, BrowserChoiceConst.NOBROWSER });

		final JButton playButton = new JButton(new ImageIcon(DBottomBar.class.getResource(ImagePathConst.PLAY)));
		playButton.setMargin(new Insets(0, 0, 0, 0));
		playButton.putClientProperty("JButton.segmentPosition", "only");

		playButton.addMouseListener(new SimpleMouseClickListener() {
			public void mouseClicked(MouseEvent e) {
				String script = textArea.getText();
				// String script = "STEP\nCASE\nq=the answer to life the universe and everything\nbtnG=click\nEND_CASE\nEND_STEP";
				String[] scriptLines = script.split("\n");
				System.out.println("length: " + scriptLines.length);
				// threadTest = new ThreadTest(scriptLines, nextLineMonitor);

				ApplicationConst runMode = ApplicationConst.AUTO;
				int selection = runModeComboBox.getSelectedIndex();
				if (selection == 0)
					runMode = ApplicationConst.AUTO;
				else if (selection == 1)
					runMode = ApplicationConst.STEPLEVEL;
				else if (selection == 2)
					runMode = ApplicationConst.LINELEVEL;

				if (runMode == ApplicationConst.STEPLEVEL)
					nextStepButton.setEnabled(true);
				else if (runMode == ApplicationConst.LINELEVEL)
					nextLineButton.setEnabled(true);

				String browser = BrowserChoiceConst.FIREFOX;
				selection = browerComboBox.getSelectedIndex();
				if (selection == 0)
					browser = BrowserChoiceConst.FIREFOX;
				else if (selection == 1)
					browser = BrowserChoiceConst.EXPLORER;
				else if (selection == 2)
					browser = BrowserChoiceConst.CHROME;
				else if (selection == 3)
					browser = BrowserChoiceConst.NOBROWSER;

				Thread controllerThread = new Thread(new ExecutorThread(scriptLines, nextLineMonitor, runMode, browser, textArea, projectDAO, pageDAO, elementDAO, elementValueDAO));
				controllerThread.start();
			}
		});

		controlsPanel.add(saveButton);
		controlsPanel.add(stopButton);
		controlsPanel.add(pauseButton);
		controlsPanel.add(playButton);
		controlsPanel.add(nextStepButton);
		controlsPanel.add(nextLineButton);
		controlsPanel.add(new JLabel(" Mode:"));
		controlsPanel.add(runModeComboBox);
		controlsPanel.add(new JLabel("Browser:"));
		controlsPanel.add(browerComboBox);

		JPanel createTestScriptMenuPanel = new JPanel();
		createTestScriptMenuPanel.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.SOUTH;
		c.weightx = 1.0;
		c.weighty = 0.0;
		c.gridx = 0;
		c.gridy = 0;
		createTestScriptMenuPanel.add(controlsPanel, c);
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1.0;
		c.weighty = 1.0;
		c.gridx = 0;
		c.gridy = 1;
		createTestScriptMenuPanel.add(scrollPane, c);

		return createTestScriptMenuPanel;
	}

	private BottomBar createBottomBar() {
		BottomBar bottomBar = new BottomBar(BottomBarSize.EXTRA_SMALL);
		bottomBar.installWindowDraggerOnWindow(mainFrame);

		return bottomBar;
	}
}
