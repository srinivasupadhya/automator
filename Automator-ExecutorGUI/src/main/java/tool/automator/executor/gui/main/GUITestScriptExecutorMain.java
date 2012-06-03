package tool.automator.executor.gui.main;

import javax.swing.SwingUtilities;

import tool.automator.executor.gui.ui.AutomatorUIMain;

public class GUITestScriptExecutorMain {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					new AutomatorUIMain();
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
