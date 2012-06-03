package tool.automator.executor.standalone.fileio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileConsoleAccess {
	private BufferedReader stdinBufferedReader = new BufferedReader(new InputStreamReader(System.in));
	private boolean isStandardInput = false;

	private File inputFile;
	private FileReader inputFileReader;
	private BufferedReader inputBufferedReader;

	public FileConsoleAccess(String fileName) throws Exception {
		init(fileName);
	}

	// read test-script
	public String[] getTestScript() throws Exception {
		String readLine = null;
		StringBuffer script = new StringBuffer();

		while ((readLine = getNextLine()) != null) {
			script.append(readLine + "\n");
		}

		return script.toString().split("\n");
	}

	public void init(String fileName) throws IOException {
		isSTDIN(false);
		setInput(fileName);
	}

	public void isSTDIN(boolean standard) {
		isStandardInput = standard;
	}

	public void setInput(String filename) throws IOException {
		inputFile = new File(filename);
		inputFileReader = new FileReader(inputFile);
		inputBufferedReader = new BufferedReader(inputFileReader);
	}

	public String getNextLine() throws IOException {
		return isStandardInput ? stdinBufferedReader.readLine() : inputBufferedReader.readLine();
	}

	public void close() throws IOException {
		inputBufferedReader.close();
	}
}
