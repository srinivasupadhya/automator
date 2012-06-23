package tool.automator.executor.gui.control;

import java.awt.Color;

import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

import tool.automator.executor.constants.ApplicationConst;
import tool.automator.executor.datamanager.ElementDataManager;
import tool.automator.executor.datamanager.ElementValueDataManager;
import tool.automator.executor.datamanager.PageDataManager;
import tool.automator.executor.datamanager.ProjectDataManager;

public class ExecutorThread implements Runnable {
	private String[] testScriptLines;
	private Object monitor;
	private ApplicationConst runMode;
	private volatile boolean stopExecution = false;

	private JTextArea textArea;
	private Highlighter hilighter;
	private Highlighter.HighlightPainter painter;

	private TestScriptInterpreter executor;
	
	private ProjectDataManager projectDAO;
	private PageDataManager pageDAO;
	private ElementDataManager elementDAO;
	private ElementValueDataManager elementValueDAO;

	public ExecutorThread(String[] testScriptLines, Object monitor, ApplicationConst runMode, String browser, JTextArea textArea, ProjectDataManager projectDAO, PageDataManager pageDAO, ElementDataManager elementDAO, ElementValueDataManager elementValueDAO) {
		this.testScriptLines = testScriptLines;
		this.monitor = monitor;
		this.runMode = runMode;

		this.textArea = textArea;
		this.hilighter = new DefaultHighlighter();
		this.painter = new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW);
		this.textArea.setHighlighter(this.hilighter);
		
		this.projectDAO = projectDAO;
		this.pageDAO = pageDAO;
		this.elementDAO = elementDAO;
		this.elementValueDAO = elementValueDAO;

		this.executor = new TestScriptInterpreter(this.projectDAO, this.pageDAO, this.elementDAO, this.elementValueDAO);
	}

	public void setStopExecution(boolean stopExecution) {
		this.stopExecution = stopExecution;
	}

	public void run() {
		System.out.println("TESTSCRIPT EXECUTION START");
		
		int index = 0;
		for (int i = 0; i < testScriptLines.length && !stopExecution; i++) {
			System.out.println((i + 1) + "=] " + testScriptLines.length + ": " + testScriptLines[i]);

			executor.interpretLine(testScriptLines[i]);

			highlightText(i, index);

			// wait if runMode is "line level"
			if (i < testScriptLines.length - 1 && runMode == ApplicationConst.LINELEVEL) {
				//System.out.println("WAIT START");
				synchronized (monitor) {
					try {
						monitor.wait();
					}
					catch (InterruptedException e) {
						System.err.println("WAIT INTERRUPTED");
						e.printStackTrace();
					}
				}
				//System.out.println("WAIT END");
			}
			// wait if runMode is "step level"
			else if(i < testScriptLines.length - 1 && runMode == ApplicationConst.STEPLEVEL && executor.stepComplete()) {
				//System.out.println("WAIT START");
				synchronized (monitor) {
					try {
						monitor.wait();
					}
					catch (InterruptedException e) {
						System.err.println("WAIT INTERRUPTED");
						e.printStackTrace();
					}
				}
				//System.out.println("WAIT END");
			}
			
			index = index + testScriptLines[i].length() + 1;
		}

		System.out.println("TESTSCRIPT EXECUTION END");
		
		executor.close();
		clearHighlights();
	}

	private void highlightText(int i, int index) {
		// remove any previous highlights
		clearHighlights();
		
		try {
			System.out.println("highlight: " + index + " - " + (index + testScriptLines[i].length()));
			
			// highlight currentLine on GUI
			hilighter.addHighlight(index, index + testScriptLines[i].length() + 1, painter);
		}
		catch (BadLocationException e) {
			e.printStackTrace();
		}
	}

	private void clearHighlights() {
		textArea.getHighlighter().removeAllHighlights();
	}
}
