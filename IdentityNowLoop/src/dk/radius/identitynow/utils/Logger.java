package dk.radius.identitynow.utils;

import com.sap.tc.logging.Location;
import com.sap.tc.logging.Severity;
import com.sap.tc.logging.SimpleLogger;

public class Logger {
	
	public static void writeLogEntry(String methodSignature, Location location, String text) {
		SimpleLogger.trace(Severity.DEBUG, location, methodSignature + ": " + text);
	}
}
