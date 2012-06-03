package tool.automator.executor.parser;

import tool.automator.executor.constants.*;

public class TestScriptParser {
	private String currentLine;
	private int lineNumber;
	private int stepNumber;
	private LineTypeConst currentLineType;
	private String directiveText;
	private String elementScriptName, elementValue, functionName;
	private ParserErrorConst errorCode;

	/**
	 * constructor
	 */
	public TestScriptParser() {
		this.lineNumber = 0;
		this.stepNumber = 0;

		initialize();
	}

	/**
	 * re-initialize variables
	 */
	private void initialize() {
		this.currentLine = null;
		this.currentLineType = LineTypeConst.UNKNOWN;
		this.directiveText = null;
		this.elementScriptName = null;
		this.elementValue = null;
		this.functionName = null;
		this.errorCode = ParserErrorConst.NO_ERRORS;
	}

	/**
	 * parse current test-script line
	 */
	public void parseLine(String currentLineParam) {
		initialize();

		if (currentLineParam == null)
			currentLine = null;
		else {
			// remove comment if present in line
			if (!currentLineParam.startsWith("#") && currentLineParam.contains("#")) {
				int index = currentLineParam.indexOf('#');
				currentLineParam = currentLineParam.substring(0, index);
			}

			// line to be parsed
			currentLine = currentLineParam.trim();
		}

		lineNumber++;

		currentLineType = getLineType();

		doParse();
	}

	/**
	 * find current test-script line's type
	 */
	private LineTypeConst getLineType() {
		LineTypeConst lineType = LineTypeConst.UNKNOWN;

		// System.out.println(currentLine);

		if (currentLine == null || currentLine.isEmpty() || currentLine.startsWith("EMPTY"))
			lineType = LineTypeConst.EMPTY;
		else if (currentLine.startsWith("#"))
			lineType = LineTypeConst.COMMENT;
		else if (currentLine.startsWith(ScriptElementConst.START_MODULE))
			lineType = LineTypeConst.START_MODULE;
		else if (currentLine.startsWith(ScriptElementConst.END_MODULE))
			lineType = LineTypeConst.END_MODULE;
		else if (currentLine.startsWith(ScriptElementConst.START_STEP))
			lineType = LineTypeConst.START_STEP;
		else if (currentLine.startsWith(ScriptElementConst.END_STEP))
			lineType = LineTypeConst.END_STEP;
		else if (currentLine.startsWith(ScriptElementConst.START_GLOBAL))
			lineType = LineTypeConst.START_GLOBAL;
		else if (currentLine.startsWith(ScriptElementConst.END_GLOBAL))
			lineType = LineTypeConst.END_GLOBAL;
		else if (currentLine.startsWith(ScriptElementConst.START_CASE))
			lineType = LineTypeConst.START_CASE;
		else if (currentLine.startsWith(ScriptElementConst.END_CASE))
			lineType = LineTypeConst.END_CASE;
		else if (currentLine.startsWith("@"))
			lineType = LineTypeConst.FUNCTION_CALL;
		else if ((currentLine.startsWith("$")) && currentLine.contains("=="))
			lineType = LineTypeConst.ELEMENT_VALIDATION;
		else if (currentLine.startsWith("$") && currentLine.contains("="))
			lineType = LineTypeConst.EXECUTABLE_COMMAND;

		if (lineType == LineTypeConst.UNKNOWN)
			setErrorCode(ParserErrorConst.INVALID_COMMAND_TYPE);

		return lineType;
	}

	/**
	 * parse line
	 */
	private void doParse() {
		switch (currentLineType) {
		case START_MODULE:
		case END_MODULE:
		case START_STEP:
		case END_STEP:
		case START_GLOBAL:
		case END_GLOBAL:
		case START_CASE:
		case END_CASE:
			parseDirectiveStatement();
			break;

		case ELEMENT_VALIDATION:
			parseValidationCommandStatement();
			break;

		case EXECUTABLE_COMMAND:
			parseCommandStatement();
			break;

		case FUNCTION_CALL:
			parseFunctionCall();
			break;

		case COMMENT:
		case EMPTY:
			break;

		default:
			setErrorCode(ParserErrorConst.INVALID_COMMAND_TYPE);
		}
	}

	/**
	 * work on directive statement
	 */
	private void parseDirectiveStatement() {
		String stringArray[] = currentLine.split(" ", 2);

		directiveText = stringArray[0].trim().substring(1);
	}

	/**
	 * work on validation statement
	 */
	private void parseValidationCommandStatement() {
		String stringArray[] = currentLine.split("==", 2);

		elementScriptName = stringArray[0].trim().substring(1);

		if (stringArray.length > 1)
			elementValue = stringArray[1].trim();
		else
			elementValue = null;

		// System.out.println(elementScriptName + " " + elementValue);
	}

	/**
	 * work on command statement
	 */
	private void parseCommandStatement() {
		String stringArray[] = currentLine.split("=", 2);

		elementScriptName = stringArray[0].trim().substring(1);

		if (stringArray.length > 1) {
			elementValue = stringArray[1].trim();

			if (elementValue.startsWith("\"")) {
				elementValue = elementValue.substring(1, elementValue.length() - 1);
			}
		}
		else
			elementValue = null;

		// System.out.println(elementScriptName + " " + elementValue);
	}

	/**
	 * work on function call
	 */
	private void parseFunctionCall() {
		String stringArray[] = currentLine.split("=", 2);

		functionName = stringArray[0].trim().substring(1);

		if (stringArray.length > 1) {
			elementValue = stringArray[1].trim();

			if (elementValue.startsWith("\"")) {
				elementValue = elementValue.substring(1, elementValue.length() - 1);
			}
		}
		else
			elementValue = null;

		// System.out.println(functionName + " " + elementValue);
	}

	/**
	 * getters & setters
	 */
	public int getLineNumber() {
		return lineNumber;
	}

	public int getStepNumber() {
		return stepNumber;
	}

	public String getCurrentLine() {
		return currentLine;
	}

	public LineTypeConst getCurrentLineType() {
		return currentLineType;
	}

	public String getDirectiveText() {
		return directiveText;
	}

	public String getElementScriptName() {
		return elementScriptName;
	}

	public String getElementValue() {
		return elementValue;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setErrorCode(ParserErrorConst errorCode) {
		System.out.println(errorCode);
		this.errorCode = errorCode;
	}

	public ParserErrorConst getErrorCode() {
		return errorCode;
	}
}
