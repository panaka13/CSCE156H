package logFile;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;

public class LogFile {
	
	private static Logger logger = LogManager.getLogger(LogFile.class);
	
	/**
	 * import message to log file
	 * @param message
	 */
	static public void importToLog(String message) {
		logger.info(message);
	}
	
	/**
	 * import error to log file
	 * @param e
	 */
	static public void importToLog(Exception e) {
		logger.info(e.getMessage(), e);
	}
	
	/**
	 * import error to log file
	 * @param message
	 * @param e
	 */
	static public void importToLog(String message, Exception e) {
		logger.info(message, e);
	}
	
	public LogFile() {
	}
	
}
